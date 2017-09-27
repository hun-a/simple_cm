package com.cubrid.cubridmanager.core.mondashboard.model;

import java.util.ArrayList;
import java.util.List;

public class HAHostStatusInfo {

	private String ip;
	private String hostName;
	private String priority;
	private HostStatusType statusType = HostStatusType.UNKNOWN;
	private int cpuUsage;
	private int memUsage;
	private int ioWait;
	private HAHostStatusInfo masterHostStatusInfo;
	private List<HAHostStatusInfo> slaveHostStatusInfoList = new ArrayList<HAHostStatusInfo>();
	private List<HADatabaseStatusInfo> dbStatusList = new ArrayList<HADatabaseStatusInfo>();

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public HostStatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(HostStatusType statusType) {
		this.statusType = statusType;
	}

	public List<HADatabaseStatusInfo> getDbStatusList() {
		return dbStatusList;
	}

	public void setDbStatusList(List<HADatabaseStatusInfo> dbStatusList) {
		this.dbStatusList = dbStatusList;
	}

	/**
	 * 
	 * Add HADatabaseStatus
	 * 
	 * @param haDbStatus The HADatabaseStatus
	 */
	public void addHADatabaseStatus(HADatabaseStatusInfo haDbStatus) {
		dbStatusList.add(haDbStatus);
	}

	/**
	 * get host's cpu usage.
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
	 * get host memory usage.
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

	/**
	 * get host io wait
	 * 
	 * @return the ioWait
	 */
	public int getIoWait() {
		return ioWait;
	}

	/**
	 * @param ioWait the ioWait to set
	 */
	public void setIoWait(int ioWait) {
		this.ioWait = ioWait;
	}

	public HAHostStatusInfo getMasterHostStatusInfo() {
		return masterHostStatusInfo;
	}

	public void setMasterHostStatusInfo(HAHostStatusInfo masterHostStatusInfo) {
		this.masterHostStatusInfo = masterHostStatusInfo;
	}

	public List<HAHostStatusInfo> getSlaveHostStatusInfoList() {
		return slaveHostStatusInfoList;
	}

	public void setSlaveHostStatusInfoList(
			List<HAHostStatusInfo> slaveHostStatusInfoList) {
		this.slaveHostStatusInfoList = slaveHostStatusInfoList;
	}

}
