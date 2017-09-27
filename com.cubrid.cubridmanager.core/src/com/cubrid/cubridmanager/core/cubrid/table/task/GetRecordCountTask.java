package com.cubrid.cubridmanager.core.cubrid.table.task;

import java.sql.SQLException;

import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.core.util.QuerySyntax;
import com.cubrid.cubridmanager.core.Messages;
import com.cubrid.cubridmanager.core.common.jdbc.JDBCTask;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;
import com.cubrid.jdbc.proxy.driver.CUBRIDResultSetProxy;

public class GetRecordCountTask extends JDBCTask {
	private static final Logger LOGGER = LogUtil.getLogger(GetRecordCountTask.class);

	public GetRecordCountTask(DatabaseInfo dbInfo) {
		super("GetAllAttr", dbInfo);
	}

	/**
	 * 
	 * Get the record count
	 * 
	 * @param table String the given table name
	 * @param whereCondition String
	 * @return int
	 */
	public int getRecordCount(String table, String whereCondition) {
		String sql = "SELECT COUNT(*) FROM " + QuerySyntax.escapeKeyword(table);
		if (null != whereCondition) {
			sql = sql + " " + whereCondition;
		}

		// [TOOLS-2425]Support shard broker
		sql = DatabaseInfo.wrapShardQuery(databaseInfo, sql);

		return getRecordCount(sql);
	}

	/**
	 * 
	 * Get the record count
	 * 
	 * @param table String the given table name
	 * @param column String the given column name
	 * @param whereCondition String
	 * @return int
	 */
	public int getRecordCount(String table, String column, String whereCondition) {
		String sql = "SELECT COUNT(" + QuerySyntax.escapeKeyword(column) + ") FROM " + QuerySyntax.escapeKeyword(table);
		if (null != whereCondition) {
			sql = sql + " " + whereCondition;
		}

		sql = DatabaseInfo.wrapShardQuery(databaseInfo, sql);
		return getRecordCount(sql);
	}

	/**
	 * Get record count
	 * 
	 * @param sql String
	 * @return int
	 */
	public int getRecordCount(String sql) {
		try {
			if (errorMsg != null && errorMsg.trim().length() > 0) {
				return -1;
			}

			if (connection == null || connection.isClosed()) {
				errorMsg = Messages.error_getConnection;
				return -1;
			}

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();

			return rs.getInt(1);
		} catch (SQLException e) {
			errorMsg = e.getMessage();
		} finally {
			finish();
		}

		return -1;
	}

	/**
	 * Get the record count,but not close connection,statement,result set.
	 * 
	 * @param table String
	 * @param whereCondition String
	 * @return int
	 */
	public int getRecordCountNoClose(String table, String whereCondition) {
		String sql = "SELECT COUNT(*) FROM " + QuerySyntax.escapeKeyword(table);
		if (null != whereCondition) {
			sql = sql + " " + whereCondition;
		}
		sql = DatabaseInfo.wrapShardQuery(databaseInfo, sql);
		return getRecordCountNoClose(sql);
	}

	/**
	 * Get the record count,but not close connection,statement,result set.
	 * 
	 * @param sql String
	 * @return int
	 */
	public int getRecordCountNoClose(String sql) {
		try {
			if (errorMsg != null && errorMsg.trim().length() > 0) {
				return -1;
			}

			if (connection == null || connection.isClosed()) {
				errorMsg = Messages.error_getConnection;
				return -1;
			}

			stmt = connection.createStatement();
			rs = (CUBRIDResultSetProxy) stmt.executeQuery(sql);
			rs.next();

			return rs.getInt(1);
		} catch (SQLException e) {
			LOGGER.error("", e);
			errorMsg = e.getMessage();
		} finally {
			finish();
		}

		return -1;
	}
}
