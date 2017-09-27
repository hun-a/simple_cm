package com.cubrid.cubridmanager.core.common.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.core.util.QueryUtil;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;

public class DBConnection {
	private static final Logger LOGGER = LogUtil.getLogger(DBConnection.class);

	private DatabaseInfo databaseInfo;
	private Connection connection;
	private boolean autoCommit;
	private boolean autoClosable;

	private long connectionLastUsedTimeMillis;
	private static long DEFAULT_CONNECTION_TIMEOUT_MILLIS = 1800000; // 30 minutes
	public static final long KEEPALIVE_CHECK_TIMEOUT = 3000;

	/**
	 * Initialize
	 */
	public DBConnection() {
		this(null);
	}

	/**
	 * Initialize
	 *
	 * @param databaseInfo
	 */
	public DBConnection(DatabaseInfo databaseInfo) {
		this.databaseInfo = databaseInfo;
		this.autoCommit = true;
		this.autoClosable = false;
	}

	/**
	 * Change DatabaseInfo object.
	 * It will be closed automatically a connection of previous DatabaseInfo. 
	 *
	 * @param databaseInfo
	 */
	public void changeDatabaseInfo(DatabaseInfo databaseInfo) {
		this.databaseInfo = databaseInfo;
		if (connection != null) {
			close();
		}
	}

	/**
	 * Return and create a new connection.
	 *
	 * @return
	 */
	private Connection makeConnection() throws SQLException {
		return JDBCConnectionManager.getConnection(databaseInfo, autoCommit);
	}

	/**
	 * Return current connection,
	 * but it will not connect to a database if there is no connection.
	 *
	 * @return
	 */
	public Connection getConnection() throws SQLException {
		return getConnection(false);
	}

	/**
	 * Return current connection,
	 * but it will not connect to a database if there is no connection.
	 *
	 * @return
	 */
	public Connection getConnectionQuietly() {
		return connection;
	}

	/**
	 * It will make a connection if there is no connection.
	 */
	public Connection checkAndConnect() throws SQLException {
		return getConnection(true);
	}

	/**
	 * It will make a connection if there is no connection without exceptions.
	 */
	public Connection checkAndConnectQuietly() {
		try {
			getConnection(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Return current connection,
	 * it will connect to a database if there is no connection.
	 *
	 * @param tryToConnectIfNotExists
	 * @return
	 */
	public Connection getConnection(boolean tryToConnectIfNotExists) throws SQLException {
		if (tryToConnectIfNotExists && connection == null) {
			connection = makeConnection();
		}
		updateConnectionLastUsedTime();
		return connection;
	}

	/**
	 * Change connection by manual way
	 * 
	 * @param connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Close connection.
	 */
	public void close() {
		final Connection connectionClone = connection;
		new Thread(new Runnable() {
			public void run() {
				QueryUtil.freeQuery(connectionClone);
			}
		}).start();
		this.connection = null;
	}

	// TODO will remove later
	public void closeForDebug() {
		final Connection connectionClone = connection;
		new Thread(new Runnable() {
			public void run() {
				long timestamp = System.currentTimeMillis();
				QueryUtil.freeQuery(connectionClone);
				long elapsed = System.currentTimeMillis() - timestamp;
				LOGGER.error("Elapse of disconnection: " + elapsed + " ms");
			}
		}).start();
		this.connection = null;
	}

	/**
	 * Return whether it has a connection.
	 *
	 * @return
	 */
	public boolean hasConnection() {
		boolean hasConnected = false;
		try {
			if (connection != null && !connection.isClosed()) {
				hasConnected = true;
			}
		} catch (SQLException ex) {
			LOGGER.error(ex.getLocalizedMessage());
		}
		return hasConnected;
	}

	public void commit() throws SQLException {
		if (connection != null) {
			connection.commit();
		}
	}

	public void rollback() throws SQLException {
		if (connection != null) {
			connection.rollback();
		}
	}

	public boolean isClosed() {
		boolean closed = false;
		if (connection == null) {
			closed = true;
		} else {
			try {
				closed = connection.isClosed();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage(), e);
				closed = true;
			}
		}
		return closed;
	}

	public boolean isAutoCommit() {
		return autoCommit;
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {
		this.autoCommit = autoCommit;
		if (connection != null) {
			this.connection.setAutoCommit(autoCommit);
		}
	}

	public boolean isAutoClosable() {
		return autoClosable;
	}

	public void setAutoClosable(boolean autoClosable) {
		this.autoClosable = autoClosable;
	}

	/**
	 * Return whether this connection was expired.
	 *
	 * @return boolean
	 */
	public boolean isExpiredConnection() {
		return System.currentTimeMillis() - connectionLastUsedTimeMillis > DEFAULT_CONNECTION_TIMEOUT_MILLIS;
	}

	private void updateConnectionLastUsedTime() {
		this.connectionLastUsedTimeMillis = System.currentTimeMillis();
	}

	public boolean testConnectionAlived() {
		TimerProvidedThread checkerThread = new TimerProvidedThread();
		checkerThread.start();

		try {
			checkerThread.join(KEEPALIVE_CHECK_TIMEOUT);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		return !checkerThread.isRunning() && connection != null;
	}

	class TimerProvidedThread extends Thread {
		private boolean running = true;

		public boolean isRunning() {
			return running;
		}

		public void run() {
			try {
				getConnection().getTransactionIsolation();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			} finally {
				this.running = false;
			}
		}
	}
}
