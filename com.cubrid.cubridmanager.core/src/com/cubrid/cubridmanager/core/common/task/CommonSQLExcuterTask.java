package com.cubrid.cubridmanager.core.common.task;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cubrid.cubridmanager.core.Messages;
import com.cubrid.cubridmanager.core.common.jdbc.JDBCTask;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;

public class CommonSQLExcuterTask extends
JDBCTask {

	private List<String> sqls = null;
	private List<String> callSqls = null;

	public CommonSQLExcuterTask(DatabaseInfo dbInfo) {
		super("ExecuteSQL", dbInfo, true);
	}

	/**
	 * Execute the tasks
	 */
	public void execute() {
		String currentDDL = null;
		try {
			if (errorMsg != null && errorMsg.trim().length() > 0) {
				return;
			}
			if (connection == null || connection.isClosed()) {
				errorMsg = Messages.error_getConnection;
				return;
			}
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			if (sqls != null) {
				for (String sql : sqls) {
					currentDDL = sql;
					stmt.executeUpdate(sql);
				}
			}
			stmt.close();
			if (callSqls != null) {
				for (String sql : callSqls) {
					currentDDL = sql;
					CallableStatement cs = connection.prepareCall(sql);
					cs.execute();
					cs.close();
				}
			}
			connection.commit();
		} catch (SQLException e) {
			errorMsg = currentDDL + ";\nError code: " + e.getErrorCode()
					+ "\n            " + e.getMessage();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				errorMsg = errorMsg + "\n" + e1.getMessage();
			}
		} finally {
			finish();
		}
	}

	/**
	 * 
	 * Add a sql statement
	 * 
	 * @param sql String The given sql statement
	 */
	public void addSqls(String sql) {
		if (sqls == null) {
			sqls = new ArrayList<String>();
		}
		this.sqls.add(sql);
	}

	/**
	 * Add a call sql statement
	 * 
	 * @param sql String The given sql statement
	 */
	public void addCallSqls(String sql) {
		if (callSqls == null) {
			callSqls = new ArrayList<String>();
		}
		this.callSqls.add(sql);
	}

}
