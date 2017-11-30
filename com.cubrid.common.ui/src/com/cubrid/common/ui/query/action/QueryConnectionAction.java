package com.cubrid.common.ui.query.action;

import java.sql.SQLException;

import com.cubrid.cubridmanager.core.common.jdbc.DBConnection;

public class QueryConnectionAction {
	public enum TYPE {
		COMMIT, ROLLBACK, CLOSE, AUTOCOMMIT
	}
	
	public static void queryAction(DBConnection connection,
			TYPE action, Object... args) throws SQLException {
		if (connection == null) {
			return;
		}
		switch (action) {
		case COMMIT:
			if (!connection.isClosed()) {
				connection.commit();
			}
			break;
		case ROLLBACK:
			if (!connection.isClosed()) {
				connection.rollback();
			}
			break;
		case CLOSE:
			if (!connection.isClosed()) {
				connection.close();
			}
			break;
		case AUTOCOMMIT:
			if (args == null || args.length == 0) {
				return;
			}
			if (!connection.isClosed()) {
				connection.setAutoCommit(Boolean.parseBoolean(args[0].toString()));
			}
			break;
		}
	}
}
