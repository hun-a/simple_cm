package com.cubrid.cubridmanager.core.cubrid.table.task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cubrid.common.core.util.ConstantsUtil;
import com.cubrid.cubridmanager.core.Messages;
import com.cubrid.cubridmanager.core.common.jdbc.JDBCTask;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;

public class GetTablesTask extends
JDBCTask {

	public GetTablesTask(DatabaseInfo dbInfo) {
		super("GetAllTables", dbInfo);
	}

	/**
	 * Get all tables and views
	 * 
	 * @return List<String>
	 */
	public List<String> getAllTableAndViews() {
		String sql = "SELECT class_name FROM db_class ORDER BY class_name ASC";

		// [TOOLS-2425]Support shard broker
		sql = databaseInfo.wrapShardQuery(sql);

		return getTables(sql);
	}

	/**
	 * Get all tables
	 * 
	 * @return List<String>
	 */
	public List<String> getAllTables() {
		String sql = "SELECT class_name FROM db_class WHERE "
				+ "class_type='CLASS' ORDER BY class_name ASC";

		// [TOOLS-2425]Support shard broker
		sql = databaseInfo.wrapShardQuery(sql);

		return getTables(sql);
	}

	/**
	 * Get the system tables
	 * 
	 * @return List<String>
	 */
	public List<String> getSystemTables() {
		String sql = "SELECT class_name FROM db_class WHERE is_system_class='YES'"
				+ " AND class_type='CLASS' ORDER BY class_name ASC";

		// [TOOLS-2425]Support shard broker
		sql = databaseInfo.wrapShardQuery(sql);

		return getTables(sql);
	}

	/**
	 * Get the user tables
	 * 
	 * @return List<String>
	 */
	public List<String> getUserTables() {
		String sql = "SELECT class_name FROM db_class WHERE is_system_class='NO'"
				+ " AND class_type='CLASS' ORDER BY class_name ASC";

		// [TOOLS-2425]Support shard broker
		sql = databaseInfo.wrapShardQuery(sql);

		List<String> tableList = getTables(sql);
		tableList.remove(ConstantsUtil.SCHEMA_DESCRIPTION_TABLE);
		tableList.remove(ConstantsUtil.CUNITOR_HA_TABLE);
		return tableList;
	}

	/**
	 * Get the user tables not contain sub-Partition table
	 * 
	 * @return List<String>
	 */
	public List<String> getUserTablesNotContainSubPartitionTable() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c.class_name, c.class_type ");
		sb.append("FROM db_class c, db_attribute a ");
		sb.append("WHERE c.class_name=a.class_name AND c.is_system_class='NO' ");
		sb.append("AND a.from_class_name IS NULL ");
		sb.append("AND c.class_type = 'CLASS' ");
		sb.append("GROUP BY c.class_name, c.class_type ");
		sb.append("ORDER BY c.class_type, c.class_name");
		String sql = sb.toString();
		// [TOOLS-2425]Support shard broker
		sql = databaseInfo.wrapShardQuery(sql);

		List<String> tableList = getTables(sql);
		tableList.remove(ConstantsUtil.SCHEMA_DESCRIPTION_TABLE);
		tableList.remove(ConstantsUtil.CUNITOR_HA_TABLE);

		return tableList;
	}

	/**
	 * Get the tables
	 * 
	 * @param sql String
	 * @return List<String>
	 */
	public List<String> getTables(String sql) {
		List<String> tlist = new ArrayList<String>();
		try {
			if (errorMsg != null && errorMsg.trim().length() > 0) {
				return tlist;
			}
			if (connection == null || connection.isClosed()) {
				errorMsg = Messages.error_getConnection;
				return tlist;
			}
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				tlist.add(rs.getString("class_name"));
			}
		} catch (SQLException e) {
			errorMsg = e.getMessage();
		} finally {
			finish();
		}
		return tlist;
	}
}
