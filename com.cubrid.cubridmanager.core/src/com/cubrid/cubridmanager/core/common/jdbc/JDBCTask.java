package com.cubrid.cubridmanager.core.common.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;

import com.cubrid.common.core.task.AbstractTask;
import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.core.util.QueryUtil;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;

public class JDBCTask extends AbstractTask {

	private static final Logger LOGGER = LogUtil.getLogger(JDBCTask.class);

	protected DatabaseInfo databaseInfo = null;
	protected Connection connection = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	protected volatile boolean isCancel = false;
	private boolean isSharedConnection = false;

	public JDBCTask(String taskName, DatabaseInfo dbInfo) {
		this(taskName, dbInfo, true);
	}

	public JDBCTask(String taskName, DatabaseInfo dbInfo, boolean isAutoCommit) {
		this.databaseInfo = dbInfo;
		this.taskName = taskName;
		try {
			connection = JDBCConnectionManager.getConnection(dbInfo, isAutoCommit);
		} catch (Exception e) {
			errorMsg = e.getLocalizedMessage();
		}

		this.isSharedConnection = false;
	}

	public JDBCTask(String taskName, DatabaseInfo dbInfo, Connection connection) {
		this.databaseInfo = dbInfo;
		this.taskName = taskName;
		this.connection = connection;
		this.isSharedConnection = true;
	}

	/**
	 * Send request to Server
	 */
	public void execute() {
	}

	public Connection getConnection() {
		return connection;
	}

	public void cancel() {
		try {
			isCancel = true;
			if (stmt != null) {
				stmt.cancel();
			}
		} catch (SQLException e) {
			LOGGER.error("", e);
		}

		if (isSharedConnection) {
			QueryUtil.freeQuery(stmt, rs);
		} else {
			QueryUtil.freeQuery(connection, stmt, rs);
		}
	}

	/**
	 * Free JDBC connection resource
	 */
	public void finish() {
		if (isSharedConnection) {
			QueryUtil.freeQuery(stmt, rs);
		} else {
			QueryUtil.freeQuery(connection, stmt, rs);
			connection = null;
		}
		stmt = null;
		rs = null;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	public boolean isSuccess() {
		return this.errorMsg == null;
	}
}
