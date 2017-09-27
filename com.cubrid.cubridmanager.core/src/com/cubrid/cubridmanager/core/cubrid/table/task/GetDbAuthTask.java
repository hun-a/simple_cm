package com.cubrid.cubridmanager.core.cubrid.table.task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cubrid.cubridmanager.core.Messages;
import com.cubrid.cubridmanager.core.common.jdbc.JDBCTask;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;
import com.cubrid.cubridmanager.core.cubrid.table.model.DbAuth;

public class GetDbAuthTask extends
JDBCTask {
	public GetDbAuthTask(DatabaseInfo dbInfo) {
		super("GetDbAuthTask", dbInfo);
	}

	/**
	 * Get the tables
	 * 
	 * @param className String
	 * @return List<String>
	 */
	public List<DbAuth> getDbAuths(String className) {
		List<DbAuth> dbAuthList = new ArrayList<DbAuth>();
		try {
			if (errorMsg != null && errorMsg.trim().length() > 0) {
				return dbAuthList;
			}
			if (connection == null || connection.isClosed()) {
				errorMsg = Messages.error_getConnection;
				return dbAuthList;
			}

			String sql = "SELECT * FROM db_auth WHERE class_name=\'" + className + "\'";

			// [TOOLS-2425]Support shard broker
			sql = databaseInfo.wrapShardQuery(sql);

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				DbAuth dbAuth = new DbAuth();
				dbAuth.setGrantorName(rs.getString("grantor_name"));
				dbAuth.setGranteeName(rs.getString("grantee_name"));
				dbAuth.setClassName(rs.getString("class_name"));
				dbAuth.setAuthType(rs.getString("auth_type"));
				dbAuth.setGrantable(rs.getString("is_grantable"));
				dbAuthList.add(dbAuth);
			}
		} catch (SQLException e) {
			errorMsg = e.getMessage();
		} finally {
			finish();
		}
		return dbAuthList;
	}
}