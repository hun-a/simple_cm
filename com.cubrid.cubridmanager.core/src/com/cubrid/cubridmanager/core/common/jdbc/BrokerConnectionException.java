package com.cubrid.cubridmanager.core.common.jdbc;

import java.sql.SQLException;

public class BrokerConnectionException extends SQLException {

	private static final long serialVersionUID = 6139503276372120332L;

	public BrokerConnectionException(String reason, SQLException ex) {
		super(reason, ex.getSQLState(), ex.getErrorCode());
	}
}
