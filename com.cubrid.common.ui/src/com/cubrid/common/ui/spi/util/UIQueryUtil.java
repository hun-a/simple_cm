package com.cubrid.common.ui.spi.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.core.util.QueryUtil;
import com.cubrid.common.ui.query.control.QueryExecuter;
import com.cubrid.cubridmanager.core.common.jdbc.JDBCConnectionManager;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;
import com.cubrid.jdbc.proxy.driver.CUBRIDPreparedStatementProxy;

public class UIQueryUtil {
	private static final Logger LOGGER = LogUtil.getLogger(QueryExecuter.class);

	/**
	 * Get the name for query
	 *
	 * @param databaseInfo
	 * @param query
	 * @return
	 */
	public static String getTableNameFromQuery(DatabaseInfo databaseInfo, String query) {
		String tableName = null;
		Connection connection = null;
		try {
			connection = JDBCConnectionManager.getConnection(databaseInfo, false);
			tableName = getTableNameFromQuery(connection, query);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			QueryUtil.freeQuery(connection);
		}
		return tableName;
	}

	/**
	 * Get the table for query
	 *
	 * @param connection
	 * @param query
	 * @return
	 */
	public static String getTableNameFromQuery(Connection connection, String query) {
		boolean matched = true;
		String tableName = null;
		CUBRIDPreparedStatementProxy pstmt = null;
		ResultSetMetaData rsMetaData = null;
		try {
			pstmt = QueryExecuter.getStatement(connection, query, false, false);
			rsMetaData = pstmt.getMetaData();
			tableName = rsMetaData.getTableName(1);
			for (int i = 2; i <= rsMetaData.getColumnCount(); i++) {
				if (!tableName.equals(rsMetaData.getTableName(i))) {
					matched = false;
					break;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			matched = false;
		} finally {
			QueryUtil.freeQuery(pstmt);
		}
		if (matched) {
			return tableName;
		}
		return null;
	}

	/**
	 * Get the PK list for table
	 *
	 * @param databaseInfo
	 * @param tableName
	 * @return
	 */
	public static List<String> getPkList(DatabaseInfo databaseInfo, String tableName) {
		List<String> pkList = new ArrayList<String>();

		StringBuilder sqlBuf = new StringBuilder();
		sqlBuf.append("SELECT k.key_attr_name \n");
		sqlBuf.append("FROM db_index_key k, db_index i \n");
		sqlBuf.append("WHERE k.class_name = i.class_name \n");
		sqlBuf.append("AND k.index_name = i.index_name\n");
		sqlBuf.append("AND k.class_name = '").append(tableName).append("'\n");
		sqlBuf.append("AND i.is_primary_key = 'YES'");
		String sql = sqlBuf.toString();

		// [TOOLS-2425]Support shard broker
		if (databaseInfo != null) {
			sql = databaseInfo.wrapShardQuery(sql);
		}
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = JDBCConnectionManager.getConnection(databaseInfo, false);
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String val = rs.getString(1);
				val = val == null ? val : val.toLowerCase(Locale.getDefault());
				pkList.add(val);
			}
		} catch (Exception e) {
			LOGGER.error("execute SQL failed sql : " + sql + " error message: " + e);
		} finally {
			QueryUtil.freeQuery(connection, stmt, rs);
		}

		return pkList;
	}

	/**
	 * Return table name list which is related on columns.
	 *
	 * @param stmt PreparedStatement
	 * @return
	 */
	public static List<String> loadColumnTableNameList(PreparedStatement stmt) {
		List<String> tableNames = new ArrayList<String>();
		try {
			ResultSetMetaData rsMetaData = stmt.getMetaData();
			if (rsMetaData != null) {
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					tableNames.add(rsMetaData.getTableName(i));
				}
			}
		} catch (Exception ignored) {
		}
		return tableNames;
	}
}
