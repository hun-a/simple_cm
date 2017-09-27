package com.cubrid.cubridmanager.core.cubrid.serial.task;

import java.sql.SQLException;

import com.cubrid.common.core.util.QuerySyntax;
import com.cubrid.common.core.util.QueryUtil;
import com.cubrid.cubridmanager.core.Messages;
import com.cubrid.cubridmanager.core.common.jdbc.JDBCTask;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;

public class DeleteSerialTask extends JDBCTask {
	/**
	 * The constructor
	 *
	 * @param dbInfo
	 */
	public DeleteSerialTask(DatabaseInfo dbInfo) {
		super("DeleteSerial", dbInfo);
	}

	/**
	 * Delete serial by serial name
	 *
	 * @param serialNames String[] The given serial names
	 */
	public void deleteSerial(String[] serialNames) {
		try {
			if (errorMsg != null && errorMsg.trim().length() > 0) {
				return;
			}

			if (connection == null || connection.isClosed()) {
				errorMsg = Messages.error_getConnection;
				return;
			}

			stmt = connection.createStatement();
			for (int i = 0; i < serialNames.length; i++) {
				String sql = "DROP SERIAL " + QuerySyntax.escapeKeyword(serialNames[i]); // FIXME extract utility class
				sql = DatabaseInfo.wrapShardQuery(databaseInfo, sql);
				if (databaseInfo != null && databaseInfo.isShard()) {
					stmt.executeUpdate(sql);
				} else {
					stmt.addBatch(sql);
				}
			}

			if (databaseInfo == null || !databaseInfo.isShard()) {
				stmt.executeBatch();
			}

			connection.commit();
		} catch (SQLException e) {
			errorMsg = e.getMessage();
			QueryUtil.rollback(connection);
		} finally {
			finish();
		}
	}
}
