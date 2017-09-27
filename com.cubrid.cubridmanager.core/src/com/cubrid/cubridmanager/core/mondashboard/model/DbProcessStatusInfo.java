package com.cubrid.cubridmanager.core.mondashboard.model;

public class DbProcessStatusInfo {

	private String hostName; // if this is standby node, the host name belong to active node
	private String dbName; // if this is standby node, the database name belong to active node
	private String pid;
	private ProcessStatusType processStatus;
	private String logPath;
	private SyncModeType mode;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public ProcessStatusType getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(ProcessStatusType processStatus) {
		this.processStatus = processStatus;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public SyncModeType getMode() {
		return mode;
	}

	public void setMode(SyncModeType mode) {
		this.mode = mode;
	}

}
