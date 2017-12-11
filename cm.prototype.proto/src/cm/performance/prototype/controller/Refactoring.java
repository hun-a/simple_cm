package cm.performance.prototype.controller;

import java.io.IOException;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import cm.performance.prototype.ConnectionManager;
import cm.performance.prototype.event.renew.RecordInfo;
import cm.performance.prototype.event.renew.RecordManager;
import cm.performance.prototype.event.renew.RecordReaderEvent;
import cm.performance.prototype.event.renew.RecordReaderListener;
import cm.performance.prototype.event.renew.Timer;

import com.cubrid.common.core.util.Closer;
import com.cubrid.common.core.util.StringUtil;
import com.cubrid.common.ui.query.control.ColumnInfo;
import com.cubrid.common.ui.query.control.QueryInfo;
import com.cubrid.common.ui.spi.table.CellValue;
import com.cubrid.common.ui.spi.util.CommonUITool;
import com.cubrid.common.ui.spi.util.FieldHandlerUtils;
import com.cubrid.cubridmanager.core.cubrid.table.model.DBAttrTypeFormatter;
import com.cubrid.cubridmanager.core.cubrid.table.model.DataType;

public class Refactoring {
	private static final String FORMAT_DOUBLE = "0.000000000000000E000";
	private static final String FORMAT_FLOAT = "0.000000E000";
	private List<Map<String, CellValue>> allDataList = null;
	private List<ColumnInfo> allColumnList = null;
	private DecimalFormat formater4Double, formater4Float;
	private int loadSize = 0;
	public int cntRecord = 0;
	private final int recordLimit;
	private String multiQuerySql = null;
	private QueryInfo queryInfo = null;
	
	private final String QUERY_EDITOR_ID = "New Query 1";
	private final RecordManager<List<Map<String, CellValue>>> manager;
	
	public Refactoring(int limit) {
		recordLimit = limit;
		allDataList = new ArrayList<Map<String, CellValue>>();
		allColumnList = new ArrayList<ColumnInfo>();
		formater4Double = new DecimalFormat();
		formater4Double.applyPattern(FORMAT_DOUBLE);
		formater4Float = new DecimalFormat();
		formater4Float.applyPattern(FORMAT_FLOAT);
		
		manager = new RecordManager<>();
		manager.addRecordReaderListener(new RecordReaderListener() {
			
			@Override
			public void readEvent(RecordReaderEvent event) {
				// print
				List<Map<String, CellValue>> list = manager.readSpecificRecords(QUERY_EDITOR_ID, event.getCurrentPage());
				columnPrint();
				recordPrint(list);
				
				Timer.end();
				Timer.printElapsedTime();
				System.out.format("\ncurrentPage: %d\n", event.getCurrentPage());
				manager.removeRecordReaderListener(this);
				
				while (true) {
					Scanner s = new Scanner(System.in);
					String input = s.next();
					
					if (input.equals("<")) {
						if (event.getCurrentPage() > 0) {
							recordPrint(manager.readSpecificRecords(
									QUERY_EDITOR_ID, event.previous()));
						}
					} else if(input.equals(">")) {
						if (event.getCurrentPage() < manager.size(QUERY_EDITOR_ID)) {
							recordPrint(manager.readSpecificRecords(
									QUERY_EDITOR_ID, event.next()));
						}
					} else if(input.equals("q")) {
						System.out.println("Good bye~");
						break;
					}
					System.out.format("\ncurrentPage: %d\n", event.getCurrentPage());
				}
				manager.remove(QUERY_EDITOR_ID);
				System.exit(0);
			}
		});
	}

	public void run(String sql) {
		try (Connection conn = ConnectionManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			
			RecordInfo.getInstance().setNewInfo(QUERY_EDITOR_ID, 5000);
			
			fillColumnData(rs);
			fillTableItemData(rs);
			
			if (manager.isEmpty(QUERY_EDITOR_ID)) {
				columnPrint();
				recordPrint(allDataList);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	private void columnPrint() {
		StringBuilder result = new StringBuilder();

		for (ColumnInfo info: allColumnList) {
			result.append(String.format("%s(%s)\t", info.getName(), info.getType()));
		}
		result.append("\n");
		System.out.println(result.toString());
	}

	public void recordPrint(List<Map<String, CellValue>> records) {
		StringBuilder result = new StringBuilder();

		for (Map<String, CellValue> record: records) {
			Iterator<String> keys = record.keySet().iterator();
			while (keys.hasNext()) {
				CellValue data = record.get(keys.next());
				result.append(data.getShowValue() + "\t");
			}
			result.append("\n");
		}
		System.out.println(result.append("\n").toString());
	}

	private void fillColumnData(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmt = rs.getMetaData();
		int cntColumn = rsmt.getColumnCount();
		if (null == allColumnList) {
			return;
		}

		for (int i = 1; i <= cntColumn; i++) {
			String columnName = rsmt.getColumnName(i);
			String typeName = StringUtil.nvl(rsmt.getColumnTypeName(i));
			int scale = rsmt.getScale(i);
			int precision = rsmt.getPrecision(i);
			String elementTypeName = StringUtil.nvl(rsmt.getTableName(i));
			if (typeName.length() == 0) {
				int typeIndex = rsmt.getColumnType(i);
				switch (typeIndex) {
				case Types.BLOB:
					typeName = DataType.DATATYPE_BLOB;
					break;
				case Types.CLOB:
					typeName = DataType.DATATYPE_CLOB;
					break;
				default:
					typeName = "";
				}
			}
			String columnType = typeName.toUpperCase(Locale.getDefault());
			String elementType = elementTypeName.toUpperCase(Locale.getDefault());
			ColumnInfo colInfo = new ColumnInfo(String.valueOf(i), columnName,
					columnType, elementType, precision, scale);
			allColumnList.add(colInfo);
		}
	}

	@SuppressWarnings("unchecked")
	private void fillTableItemData(ResultSet rs) throws SQLException {
		cntRecord = 0;
		int index = 0;
		while (rs.next()) {
			cntRecord++;
			//add item data to the end of list
			addTableItemData(rs, -1);	// handling the just one row
			if (recordLimit > 0 && cntRecord >= recordLimit && multiQuerySql == null) {
				// save the records to file by shallow copy
				List<Map<String, CellValue>> list = (List<Map<String, CellValue>>)((ArrayList<Map<String, CellValue>>) allDataList).clone();
				allDataList = null;
				allDataList = new ArrayList<Map<String, CellValue>>();
				
				manager.write(list, QUERY_EDITOR_ID, index++);
				
				cntRecord = 0;
			}
		}
		if (multiQuerySql == null) {
			queryInfo = new QueryInfo(cntRecord, recordLimit);
			queryInfo.setCurrentPage(1);
		}
	}

	public Map<String, CellValue> addTableItemData(ResultSet rs, int idxInDataList) throws SQLException {
		Map<String, CellValue> map = new HashMap<String, CellValue>();
		int columnPos = 0, columnCount = allColumnList == null ? 0 : allColumnList.size();

		if (allColumnList != null) {
			for (int j = 1; j <= columnCount; j++) {
				ColumnInfo columnInfo = (ColumnInfo) allColumnList.get(columnPos);
				String columnType = columnInfo.getType();
				String showValue = null;
				Object value = rs.getObject(j);
				CellValue cellValue = new CellValue();
				if (value != null) {
					if (DataType.DATATYPE_SET.equals(columnType)
							|| DataType.DATATYPE_MULTISET.equals(columnType)
							|| DataType.DATATYPE_SEQUENCE.equals(columnType)) {
						StringBuffer data = new StringBuffer();
						showValue = data.toString();
						cellValue.setShowValue(showValue);
						cellValue.setValue(showValue);
					} else if (DataType.DATATYPE_DATETIME.equalsIgnoreCase(columnType)) {
						showValue = CommonUITool.formatDate(
								rs.getTimestamp(j),
								FieldHandlerUtils.FORMAT_DATETIME);
						cellValue.setValue(rs.getTimestamp(j));
						cellValue.setShowValue(showValue);
					} else if (DataType.DATATYPE_BIT_VARYING.equalsIgnoreCase(columnType)
							|| DataType.DATATYPE_BIT.equalsIgnoreCase(columnType)) {
						byte[] dataTmp = rs.getBytes(j);
						if (dataTmp.length > FieldHandlerUtils.BIT_TYPE_MUCH_VALUE_LENGTH) {
							showValue = DataType.BIT_EXPORT_FORMAT;
						} else {
							showValue = "X'" + DBAttrTypeFormatter.getHexString(dataTmp,
									columnInfo.getPrecision()) + "'";
						}
						cellValue.setValue(dataTmp);
						cellValue.setShowValue(showValue);
					} else if (DataType.DATATYPE_FLOAT.equalsIgnoreCase(columnType)) {
						float floatValue = rs.getFloat(j);
						showValue = formater4Float.format(floatValue);
						cellValue.setValue(floatValue);
						cellValue.setShowValue(showValue);
					} else if (DataType.DATATYPE_DOUBLE.equalsIgnoreCase(columnType)) {
						double doubleValue = rs.getDouble(j);
						showValue = formater4Double.format(doubleValue);
						cellValue.setValue(doubleValue);
						cellValue.setShowValue(showValue);
					} else if (DataType.DATATYPE_BLOB.equalsIgnoreCase(columnType)
							|| value instanceof Blob) {
						columnInfo.setType(DataType.DATATYPE_BLOB);
						loadBlobData(rs, j, cellValue);
					} else if (DataType.DATATYPE_CLOB.equalsIgnoreCase(columnType)
							|| value instanceof Clob) {
						columnInfo.setType(DataType.DATATYPE_CLOB);
						loadClobData(rs, j, cellValue);
					} else if (DataType.DATATYPE_NCHAR.equalsIgnoreCase(columnType)) {
						columnInfo.setType(DataType.DATATYPE_NCHAR);
						String strValue = rs.getString(j);
						showValue = "N'" + strValue + "'";
						cellValue.setValue(strValue);
						cellValue.setShowValue(showValue);

					} else if (DataType.DATATYPE_NCHAR_VARYING.equalsIgnoreCase(columnType)) {
						columnInfo.setType(DataType.DATATYPE_NCHAR_VARYING);
						String strValue = rs.getString(j);
						showValue = "N'" + strValue + "'";
						cellValue.setValue(strValue);
						cellValue.setShowValue(showValue);
					} else {
						showValue = rs.getString(j);
						cellValue.setValue(value);
						cellValue.setShowValue(showValue);
					}
					columnType = null;
					showValue = null;
				}

				map.put(columnInfo.getIndex(), cellValue);
				columnPos++;
			}
		}

		if (allDataList != null) {
			if (idxInDataList < 0 || idxInDataList >= allDataList.size() - 1) {
				allDataList.add(map);
			} else {
				allDataList.add(idxInDataList, map);
			}
		}
		return map;
	}

	private void loadBlobData(ResultSet rs, int columnIndex, CellValue cellValue) throws SQLException {
		Blob blob = rs.getBlob(columnIndex);
		if (blob == null) {
			cellValue.setValue(null);
			cellValue.setShowValue(DataType.NULL_EXPORT_FORMAT);
			cellValue.setHasLoadAll(true);
			return;
		}
		long bLength = blob.length();
		if (loadSize > 0) {
			if (loadSize >= bLength) {
				cellValue.setValue(blob.getBytes(1, new Long(bLength).intValue()));
				cellValue.setShowValue(DataType.BLOB_EXPORT_FORMAT);
				cellValue.setHasLoadAll(true);
			} else {
				cellValue.setValue(blob.getBytes(1, loadSize));
				cellValue.setShowValue(DataType.BLOB_EXPORT_FORMAT);
				cellValue.setHasLoadAll(false);
			}
		} else {
			cellValue.setValue(blob.getBytes(1, new Long(bLength).intValue()));
			cellValue.setShowValue(DataType.BLOB_EXPORT_FORMAT);
			cellValue.setHasLoadAll(true);
		}
	}

	private void loadClobData(ResultSet rs, int columnIndex, CellValue cellValue) throws SQLException {
		Reader reader = rs.getCharacterStream(columnIndex);
		if (reader == null) {
			cellValue.setValue(null);
			cellValue.setShowValue(DataType.NULL_EXPORT_FORMAT);
			return;
		}

		try {
			StringBuffer buffer = new StringBuffer();
			if (loadSize > 0) {
				char[] buf = new char[loadSize];
				int len = reader.read(buf);
				if (len != -1) {
					buffer.append(buf, 0, len);
				}
				if (len >= loadSize && reader.read() != -1) {
					cellValue.setHasLoadAll(false);
				} else {
					cellValue.setHasLoadAll(true);
				}
			} else {
				char[] buf = new char[1024];
				int len = reader.read(buf);
				while (len != -1) {
					buffer.append(buf, 0, len);
					len = reader.read(buf);
				}
			}
			cellValue.setValue(buffer.toString());
			if (buffer.toString().length() > FieldHandlerUtils.MAX_DISPLAY_CLOB_LENGTH) {
				String showValue = buffer.substring(0, FieldHandlerUtils.MAX_DISPLAY_CLOB_LENGTH) + "...";
				cellValue.setShowValue(showValue);
			} else {
				cellValue.setShowValue(buffer.toString());
			}
		} catch (IOException ex) {
			throw new SQLException(ex);
		} finally {
			Closer.close(reader);
		}
	}
}
