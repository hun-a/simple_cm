package com.cubrid.cubridmanager.core.replication.model;

public class SlaveInfo {

	private String slaveDbName;
	private String slaveIP;
	private String slaveDbPath;
	private String dbUser;
	private String password;
	private ReplicationParamInfo paramInfo = null;

	public String getSlaveDbName() {
		return slaveDbName;
	}

	public void setSlaveDbName(String slaveDbName) {
		this.slaveDbName = slaveDbName;
	}

	public String getSlaveIP() {
		return slaveIP;
	}

	public void setSlaveIP(String slaveIP) {
		this.slaveIP = slaveIP;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ReplicationParamInfo getParamInfo() {
		return paramInfo;
	}

	public void setParamInfo(ReplicationParamInfo paramInfo) {
		this.paramInfo = paramInfo;
	}

	public String getSlaveDbPath() {
		return slaveDbPath;
	}

	public void setSlaveDbPath(String slaveDbPath) {
		this.slaveDbPath = slaveDbPath;
	}

}
