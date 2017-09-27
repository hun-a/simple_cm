package com.cubrid.cubridmanager.core.common.model;

public interface CubridManagerConfParaConstants { //NOPMD

	public final static String CM_PORT = "cm_port";
	public final static String MONITOR_INTERVAL = "monitor_interval";
	public final static String ALLOW_USER_MULTI_CONNECTION = "allow_user_multi_connection";
	public final static String AUTO_START_BROKER = "auto_start_broker";
	public final static String EXECUTE_DIAG = "execute_diag";
	public final static String SERVER_LONG_QUERY_TIME = "server_long_query_time";
	public final static String CM_TARGET = "cm_target";
	public final static String SUPPORT_MON_STATISTIC = "support_mon_statistic";

	//manager parameter
	public static final String[][] ALL_CM_CONF_PARAS = {

			{CM_PORT, "int(v>=1024&&v<=65535)", "8001", "version>=8.2.0",
					"false", "" },

			{MONITOR_INTERVAL, "int(v>0)", "5", "version>=8.2.0", "false", "" },

			{ALLOW_USER_MULTI_CONNECTION, "string", "YES", "version>=8.2.0",
					"false", "" },

			{AUTO_START_BROKER, "string(YES|NO)", "YES", "version>=8.2.0",
					"false", "" },

			{EXECUTE_DIAG, "string(ON|OFF)", "OFF", "version>=8.2.0", "false",
					"" },

			{SERVER_LONG_QUERY_TIME, "int", "10", "version>=8.2.0", "false", "" },

			{CM_TARGET, "string(broker|server)", "broker,server",
					"version>=8.2.0", "false", "" } };
}
