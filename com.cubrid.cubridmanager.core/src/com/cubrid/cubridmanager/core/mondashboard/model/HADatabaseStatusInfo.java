package com.cubrid.cubridmanager.core.mondashboard.model;

import java.util.ArrayList;
import java.util.List;

public class HADatabaseStatusInfo {

	private String dbName;
	private DBStatusType statusType = DBStatusType.UNKNOWN;
	private String errorInfo;
	private int delay;
	private List<DbProcessStatusInfo> copyLogDbProcessStatusList;
	private List<DbProcessStatusInfo> applyLogDbProcessStatusList;
	private DbProcessStatusInfo dbServerProcessStatus;
	private int insertCounter;
	private int updateCounter;
	private int deleteCounter;
	private int commitCounter;
	private int failCounter;
	private int cpuUsage;
	private int memUsage;
	private HAHostStatusInfo haHostStatusInfo;

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public DBStatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(DBStatusType statusType) {
		this.statusType = statusType;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public List<DbProcessStatusInfo> getCopyLogDbProcessStatusList() {
		return copyLogDbProcessStatusList;
	}

	public void setCopyLogDbProcessStatusList(
			List<DbProcessStatusInfo> copyLogDbProcessStatusList) {
		this.copyLogDbProcessStatusList = copyLogDbProcessStatusList;
	}

	/**
	 * 
	 * Add copy log database process status information
	 * 
	 * @param statusInfo DbProcessStatusInfo
	 */
	public void addCopyLogDbProcessStatus(DbProcessStatusInfo statusInfo) {
		if (copyLogDbProcessStatusList == null) {
			copyLogDbProcessStatusList = new ArrayList<DbProcessStatusInfo>();
		}
		copyLogDbProcessStatusList.add(statusInfo);
	}

	public List<DbProcessStatusInfo> getApplyLogDbProcessStatusList() {
		return applyLogDbProcessStatusList;
	}

	public void setApplyLogDbProcessStatusList(
			List<DbProcessStatusInfo> applyLogDbProcessStatusList) {
		this.applyLogDbProcessStatusList = applyLogDbProcessStatusList;
	}

	/**
	 * 
	 * Add apply log database process status information
	 * 
	 * @param statusInfo DbProcessStatusInfo
	 */
	public void addApplyLogDbProcessStatus(DbProcessStatusInfo statusInfo) {
		if (applyLogDbProcessStatusList == null) {
			applyLogDbProcessStatusList = new ArrayList<DbProcessStatusInfo>();
		}
		applyLogDbProcessStatusList.add(statusInfo);
	}

	public DbProcessStatusInfo getDbServerProcessStatus() {
		return dbServerProcessStatus;
	}

	public void setDbServerProcessStatus(
			DbProcessStatusInfo dbServerProcessStatus) {
		this.dbServerProcessStatus = dbServerProcessStatus;
	}

	public int getInsertCounter() {
		return insertCounter;
	}

	public void setInsertCounter(int insertCounter) {
		this.insertCounter = insertCounter;
	}

	public int getUpdateCounter() {
		return updateCounter;
	}

	public void setUpdateCounter(int updateCounter) {
		this.updateCounter = updateCounter;
	}

	public int getDeleteCounter() {
		return deleteCounter;
	}

	public void setDeleteCounter(int deleteCounter) {
		this.deleteCounter = deleteCounter;
	}

	public int getCommitCounter() {
		return commitCounter;
	}

	public void setCommitCounter(int commitCounter) {
		this.commitCounter = commitCounter;
	}

	public int getFailCounter() {
		return failCounter;
	}

	public void setFailCounter(int failCounter) {
		this.failCounter = failCounter;
	}

	public HAHostStatusInfo getHaHostStatusInfo() {
		return haHostStatusInfo;
	}

	public void setHaHostStatusInfo(HAHostStatusInfo haHostStatusInfo) {
		this.haHostStatusInfo = haHostStatusInfo;
	}

	/**
	 * get database's cpu usage.
	 * 
	 * @return the cpuUsage
	 */
	public int getCpuUsage() {
		return cpuUsage;
	}

	/**
	 * @param cpuUsage the cpuUsage to set
	 */
	public void setCpuUsage(int cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	/**
	 * get database's memory usage.
	 * 
	 * @return the memUsage
	 */
	public int getMemUsage() {
		return memUsage;
	}

	/**
	 * @param memUsage the memUsage to set
	 */
	public void setMemUsage(int memUsage) {
		this.memUsage = memUsage;
	}

}
