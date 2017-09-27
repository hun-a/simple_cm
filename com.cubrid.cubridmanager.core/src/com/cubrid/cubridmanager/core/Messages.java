package com.cubrid.cubridmanager.core;

import org.eclipse.osgi.util.NLS;

public class Messages extends
		NLS {

	static {
		NLS.initializeMessages(CubridManagerCorePlugin.PLUGIN_ID + ".Messages",
				Messages.class);
	}

	// network related
	public static String error_unknownHost;
	public static String error_connectfailed;
	public static String error_disconnected;
	public static String error_noconnectSite;
	public static String error_noInitSocket;
	public static String error_invalidToken;
	public static String error_invalidRequest;
	// message related
	public static String error_messageFormat;
	// other
	public static String error_unsupportedJRE;
	public static String msg_warning;
	public static String msg_error;
	//JDBC related
	public static String error_getConnection;
	public static String error_invalidInput;
	//database user
	public static String error_invalidUser;
	// replication parameter tip message
	public static String tip_perf_poll_interval;
	public static String tip_size_of_log_buffer;
	public static String tip_size_of_cache_buffer;
	public static String tip_size_of_copylog;
	public static String tip_index_replication;
	public static String tip_for_recovery;
	public static String tip_log_apply_interval;
	public static String tip_restart_interval;

	//BrokerDiagEnum
	public static String rps;
	public static String tps;
	public static String active_session;
	public static String qps;
	public static String long_q;
	public static String long_t;
	public static String err_q;
	public static String session;
	public static String active;

	//format the data
	public static String fileTooLongMsg;

	public static String errCannotConnectToCmServer;

	//Data Type
	public static String msgDataDb;
	public static String msgDataDbVol;
	public static String msgDataBroker;
	public static String msgDataOs;

	// metric message
	public static String msgDbCpuKernel;
	public static String msgDbCpuUser;
	public static String msgDbMemPhy;
	public static String msgDbMemVir;
	public static String msgDbQps;
	public static String msgDbTps;
	public static String msgDbHitRatio;
	public static String msgDbFetchPages;
	public static String msgDbDirtyPages;
	public static String msgDbIoRead;
	public static String msgDbIoWrite;
	public static String msgDbHaCopyDelayPage;
	public static String msgDbHaCopyDelayEstimated;
	public static String msgDbHaApplyDelayPage;
	public static String msgDbHaApplyDelayEstimated;
	public static String msgDbFreespace;
	public static String msgVolFreespace;
	public static String msgBrokerTps;
	public static String msgBrokerQps;
	public static String msgBrokerLongT;
	public static String msgBrokerLongQ;
	public static String msgBrokerReq;
	public static String msgBrokerErrQ;
	public static String msgBrokerJq;
	public static String msgOsCpuIdle;
	public static String msgOsCpuIowait;
	public static String msgOsCpuKernel;
	public static String msgOsCpuUser;
	public static String msgOsMemPhyFree;
	public static String msgOsMemSwapFree;
	public static String msgOsDiskFree;
	public static String msgTimeDaily;
	public static String msgTimeWeekly;
	public static String msgTimeMonthly;
	public static String msgTimeYearly;

	// default chart name
	public static String msgDbCpuUsage;
	public static String msgDbMemInfo;
	public static String msgDbAppInfo;
	public static String msgDbIoInfo;
	public static String msgDbPageInfo;
	public static String msgDbHaInfo;
	public static String msgDbFreeSpaceInfo;
	public static String msgDbVolFreeSpaceInfo;
	public static String msgBrokerInfo;
	public static String msgOsCpuUsage;
	public static String msgOsMemInfo;
	public static String msgOsSpaceInfo;
}
