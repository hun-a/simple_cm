package com.cubrid.cubridmanager.core.cubrid.user.task;

import java.sql.SQLException;

import com.cubrid.common.core.util.StringUtil;
import com.cubrid.cubridmanager.core.Messages;
import com.cubrid.cubridmanager.core.common.jdbc.JDBCTask;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;

public class ChangeDbUserCommentTask extends JDBCTask {
	private String dbUserName;
	private String newDescription;

	/**
	 * The constructor
	 * 
	 * @param dbInfo
	 */
	public ChangeDbUserCommentTask(DatabaseInfo dbInfo) {
		super("ChangeDbUserComment", dbInfo);
	}

	/**
	 * Set database user name
	 * 
	 * @param userName the database user name
	 */
	public void setDbUserName(String userName) {
		dbUserName = userName;
	}

	/**
	 * Set new description for user
	 *
	 * @param newDescription the new description for user
	 */
	public void setNewDescription(String newDescription) {
		this.newDescription = newDescription;
	}

	/**
	 * Get the SQL for changing comment
	 *
	 * @return SQL
	 */
	private String getSQL() {
		return "ALTER USER " + dbUserName + " COMMENT " +
				StringUtil.escapeQuotes(String.format("'%s'", newDescription));
	}

	/**
	 * Change database user comment
	 */
	public void execute() {
		try {
			if (errorMsg != null && errorMsg.trim().length() > 0) {
				return;
			}

			if (connection == null || connection.isClosed()) {
				errorMsg = Messages.error_getConnection;
				return;
			}

			String sql = getSQL();

			// [TOOLS-2425]Support shard broker
			sql = DatabaseInfo.wrapShardQuery(databaseInfo, sql);

			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			errorMsg = e.getMessage();
		} finally {
			finish();
		}
	}
}
