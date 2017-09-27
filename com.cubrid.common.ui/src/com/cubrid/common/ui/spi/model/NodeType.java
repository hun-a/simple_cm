package com.cubrid.common.ui.spi.model;

public class NodeType {
	
	public static final String GROUP = "GROUP";
	public static final String SERVER = "SERVER";
	public static final String DATABASE_FOLDER = "DATABASE_FOLDER";
	public static final String DATABASE = "DATABASE";
	public static final String TABLE_FOLDER = "TABLE_FOLDER";
	public static final String VIEW_FOLDER = "VIEW_FOLDER";
	public static final String SYSTEM_TABLE_FOLDER = "SYSTEM_TABLE_FOLDER";
	public static final String SYSTEM_TABLE = "SYSTEM_TABLE";
	public static final String SYSTEM_VIEW_FOLDER = "SYSTEM_VIEW_FOLDER";
	public static final String SYSTEM_VIEW = "SYSTEM_VIEW";
	public static final String USER_TABLE = "USER_TABLE";
	public static final String USER_VIEW = "USER_VIEW";
	public static final String USER_PARTITIONED_TABLE_FOLDER = "USER_PARTITIONED_TABLE_FOLDER";
	public static final String USER_PARTITIONED_TABLE = "USER_PARTITIONED_TABLE";
	public static final String STORED_PROCEDURE_FOLDER = "STORED_PROCEDURE_FOLDER";
	public static final String STORED_PROCEDURE_FUNCTION_FOLDER = "STORED_PROCEDURE_FUNCTION_FOLDER";
	public static final String STORED_PROCEDURE_PROCEDURE_FOLDER = "STORED_PROCEDURE_PROCEDURE_FOLDER";
	public static final String STORED_PROCEDURE_FUNCTION = "STORED_PROCEDURE_FUNCTION";
	public static final String STORED_PROCEDURE_PROCEDURE = "STORED_PROCEDURE_PROCEDURE";
	public static final String TRIGGER_FOLDER = "TRIGGER_FOLDER";
	public static final String TRIGGER = "TRIGGER";
	public static final String SERIAL_FOLDER = "SERIAL_FOLDER";
	public static final String SERIAL = "SERIAL";
	public static final String TABLE_COLUMN_FOLDER = "TABLE_COLUMN_FOLDER";
	public static final String TABLE_INDEX_FOLDER = "TABLE_INDEX_FOLDER";
	public static final String TABLE_COLUMN = "TABLE_COLUMN";
	public static final String TABLE_INDEX = "TABLE_INDEX";
	public static final String USER_FOLDER = "USER_FOLDER";
	public static final String USER = "USER";
	public static final String MONITOR_DASHBOARD = "MONITOR_DASHBOARD";
	public static final String MONITOR_STATISTIC_PAGE = "MONITOR_STATISTIC_PAGE";

	public static final boolean contains(String nodeTypeToFind, String[] nodes) {
		if (nodes == null || nodes.length == 0 || nodeTypeToFind == null || nodeTypeToFind.length() == 9) {
			return false;
		}

		for (String node : nodes) {
			if (node == null) {
				continue;
			}
			if (node.equals(nodeTypeToFind)) {
				return true;
			}
		}

		return false;
	}
}
