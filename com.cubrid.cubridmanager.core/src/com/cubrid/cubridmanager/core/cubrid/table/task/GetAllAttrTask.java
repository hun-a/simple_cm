package com.cubrid.cubridmanager.core.cubrid.table.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.cubrid.common.core.common.model.DBAttribute;
import com.cubrid.common.core.util.CompatibleUtil;
import com.cubrid.cubridmanager.core.Messages;
import com.cubrid.cubridmanager.core.common.jdbc.JDBCTask;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;
import com.cubrid.cubridmanager.core.cubrid.table.model.DataType;

public class GetAllAttrTask extends
JDBCTask {
	private String className = null;
	private List<DBAttribute> allAttrList = null;

	public GetAllAttrTask(DatabaseInfo dbInfo) {
		super("GetAllAttr", dbInfo);
	}

	public GetAllAttrTask(DatabaseInfo dbInfo, Connection connection) {
		super("GetAllAttr", dbInfo, connection);
	}

	/**
	 * Get all attribute name list
	 * 
	 * @param className String
	 * @return List<String>
	 */
	public List<String> getAttrNameList(String className) {
		List<String> allAttrList = new ArrayList<String>();
		try {
			if (errorMsg != null && errorMsg.trim().length() > 0) {
				return allAttrList;
			}

			if (connection == null || connection.isClosed()) {
				errorMsg = Messages.error_getConnection;
				return allAttrList;
			}

			String sql = "SELECT attr_name, def_order FROM db_attribute WHERE class_name=?"
					+ " ORDER BY def_order";

			// [TOOLS-2425]Support shard broker
			sql = databaseInfo.wrapShardQuery(sql);

			stmt = connection.prepareStatement(sql);
			((PreparedStatement) stmt).setString(1,
					className.toLowerCase(Locale.getDefault()));
			rs = ((PreparedStatement) stmt).executeQuery();
			while (rs.next()) {
				String attrName = rs.getString("attr_name");
				allAttrList.add(attrName);
			}
		} catch (SQLException e) {
			errorMsg = e.getMessage();
		} finally {
			finish();
		}
		return allAttrList;
	}

	/**
	 * Get table or view all the attribute list
	 * 
	 */
	public void getAttrList() {
		allAttrList = new ArrayList<DBAttribute>();
		try {
			if (errorMsg != null && errorMsg.trim().length() > 0) {
				return;
			}

			if (connection == null || connection.isClosed()) {
				errorMsg = Messages.error_getConnection;
				return;
			}

			String sql = "SELECT * FROM db_attribute WHERE class_name=?"
					+ " ORDER BY def_order";

			// [TOOLS-2425]Support shard broker
			sql = databaseInfo.wrapShardQuery(sql);

			stmt = connection.prepareStatement(sql);
			((PreparedStatement) stmt).setString(1,
					className.toLowerCase(Locale.getDefault()));
			rs = ((PreparedStatement) stmt).executeQuery();
			while (rs.next()) {
				DBAttribute dbAttribute = new DBAttribute();
				dbAttribute.setName(rs.getString("attr_name"));
				String attrType = rs.getString("attr_type");
				String dataType = rs.getString("data_type");
				int prec = rs.getInt("prec");
				int scale = rs.getInt("scale");
				String defaultValue = rs.getString("default_value");
				dbAttribute.setDomainClassName(rs.getString("domain_class_name"));
				if (attrType.equalsIgnoreCase("SHARED")) {
					dbAttribute.setShared(true);
				} else {
					dbAttribute.setShared(false);
				}
				if (attrType.equalsIgnoreCase("CLASS")) {
					dbAttribute.setClassAttribute(true);
				} else {
					dbAttribute.setClassAttribute(false);
				}
				String isNull = rs.getString("is_nullable");
				if (isNull.equalsIgnoreCase("YES")) {
					dbAttribute.setNotNull(false);
				} else {
					dbAttribute.setNotNull(true);
				}
				dataType = DataType.convertAttrTypeString(dataType, String.valueOf(prec), String.valueOf(scale));
				dbAttribute.setType(dataType);
				if (CompatibleUtil.isCommentSupports(databaseInfo)) {
					dbAttribute.setDescription(rs.getString("comment"));
				}
				//Fix bug TOOLS-3093
				defaultValue = DataType.convertDefaultValue(dataType, defaultValue, databaseInfo);
				dbAttribute.setDefault(defaultValue);
				allAttrList.add(dbAttribute);
			}
		} catch (SQLException e) {
			errorMsg = e.getMessage();
		} finally {
			finish();
		}
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<DBAttribute> getAllAttrList() {
		return allAttrList;
	}
}
