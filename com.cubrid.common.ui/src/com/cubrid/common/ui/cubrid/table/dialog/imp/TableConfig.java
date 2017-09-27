package com.cubrid.common.ui.cubrid.table.dialog.imp;

import java.util.ArrayList;
import java.util.List;

import com.cubrid.common.ui.cubrid.table.dialog.PstmtParameter;

public class TableConfig implements Cloneable {

	public static final String TYPE_SCHEMA = "Schema";
	public static final String TYPE_DATA = "Data";
	public static final String TYPE_INDEX = "Index";
	public static final int LINT_COUNT_UNKNOW = -1;
	private String name;
	private String filePath;
	private String fileType = TYPE_DATA;
	private String createDDL;
	private String insertDML;
	private boolean isFirstRowAsColumn;
	private List<PstmtParameter> pstmList = new ArrayList<PstmtParameter>();
	/*Not persist*/
	private int lineCount;
	private boolean isMapped = false;

	public TableConfig() {
	}

	public TableConfig(String tableName) {
		this.name = tableName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<PstmtParameter> getPstmList() {
		return pstmList;
	}

	public void setPstmList(List<PstmtParameter> pstmList) {
		this.pstmList = pstmList;
	}

	public String getCreateDDL() {
		return createDDL;
	}

	public void setCreateDDL(String createDDL) {
		this.createDDL = createDDL;
	}

	public String getInsertDML() {
		return insertDML;
	}

	public void setInsertDML(String insertDML) {
		this.insertDML = insertDML;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	public boolean isFirstRowAsColumn() {
		return isFirstRowAsColumn;
	}

	public void setFirstRowAsColumn(boolean isFirstRowAsColumn) {
		this.isFirstRowAsColumn = isFirstRowAsColumn;
	}

	public boolean isMapped() {
		return isMapped;
	}

	public void setMapped(boolean isMapped) {
		this.isMapped = isMapped;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * Clone a object
	 */
	public TableConfig clone() {
		TableConfig tableConfig = null;
		try {
			tableConfig = (TableConfig) super.clone();
		} catch (CloneNotSupportedException e) {
		}

		List<PstmtParameter> pstmList = new ArrayList<PstmtParameter>();
		for (PstmtParameter pstm : tableConfig.getPstmList()) {
			pstmList.add(pstm.clone());
		}
		tableConfig.setPstmList(pstmList);

		return tableConfig;
	}

}
