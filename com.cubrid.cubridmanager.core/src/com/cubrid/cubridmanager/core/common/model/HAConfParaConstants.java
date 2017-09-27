package com.cubrid.cubridmanager.core.common.model;

public interface HAConfParaConstants { //NOPMD

	public final static String HA_MODE = "ha_mode";
	public final static String HA_NODE_LIST = "ha_node_list";
	public final static String HA_PORT_ID = "ha_port_id";
	public final static String HA_REPLICA_LIST = "ha_replica_list";
	public final static String HA_PING_HOSTS = "ha_ping_hosts";
	public final static String HA_DB_LIST = "ha_db_list";
	public final static String HA_COPY_LOG_BASE = "ha_copy_log_base";
	public final static String HA_APPLY_MAX_MEM_SIZE = "ha_apply_max_mem_size";
	public final static String HA_COPY_SYNC_MODE = "ha_copy_sync_mode";
	public final static String LOG_MAX_ARCHIVES = "log_max_archives";
	
	/*8.4.3*/
	public final static String HA_APPLYLOGDB_IGNORE_ERROR_LIS = "ha_applylogdb_ignore_error_lis";
	public final static String HA_APPLYLOGDB_RETRY_ERROR_LIST ="ha_applylogdb_retry_error_list";
	
	

	public final static String[][] ALL_HA_CONF_PARAS = {
			{HAConfParaConstants.HA_MODE, "string(on|off|yes|no|replica)",
					"off", CubridConfParaConstants.PARAMETER_TYPE_SERVER,
					"version>=8.4.0", "false", "" },

			{HAConfParaConstants.HA_NODE_LIST, "string", "",
					CubridConfParaConstants.PARAMETER_TYPE_SERVER,
					"version>=8.4.0", "false", "" },

			{HAConfParaConstants.HA_PORT_ID, "int(v>=1024&&v<=65535)", "59901",
					CubridConfParaConstants.PARAMETER_TYPE_SERVER,
					"version>=8.4.0", "false", "" },

			{HA_REPLICA_LIST, "string", "",
					CubridConfParaConstants.PARAMETER_TYPE_SERVER,
					"version>=8.4.0", "false", "" },

			{HA_PING_HOSTS, "string", "",
					CubridConfParaConstants.PARAMETER_TYPE_SERVER,
					"version>=8.4.0", "false", "" },

			{HA_COPY_LOG_BASE, "string", "",
					CubridConfParaConstants.PARAMETER_TYPE_SERVER,
					"version>=8.4.0", "false", "" },

			{HA_DB_LIST, "string", "",
					CubridConfParaConstants.PARAMETER_TYPE_SERVER,
					"version>=8.4.0", "false", "" },

			{HA_APPLY_MAX_MEM_SIZE, "int", "0",
					CubridConfParaConstants.PARAMETER_TYPE_SERVER,
					"version>=8.4.0", "false", "" },

			{HA_COPY_SYNC_MODE, "string", "",
					CubridConfParaConstants.PARAMETER_TYPE_SERVER,
					"version>=8.4.0", "false", "" },

			{LOG_MAX_ARCHIVES, "int", "0",
					CubridConfParaConstants.PARAMETER_TYPE_SERVER,
					"version>=8.4.0", "false", "" } };
}
