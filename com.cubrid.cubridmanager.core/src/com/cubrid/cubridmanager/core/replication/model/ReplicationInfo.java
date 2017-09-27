package com.cubrid.cubridmanager.core.replication.model;

import java.util.ArrayList;
import java.util.List;

public class ReplicationInfo {

	private List<MasterInfo> masterList = new ArrayList<MasterInfo>();
	private List<SlaveInfo> slaveList = new ArrayList<SlaveInfo>();
	private DistributorInfo distInfo = null;

	public List<MasterInfo> getMasterList() {
		return masterList;
	}

	public void setMasterList(List<MasterInfo> masterList) {
		this.masterList = masterList;
	}

	/**
	 * 
	 * Add master database information
	 * 
	 * @param masterInfo the MasterInfo obj
	 */
	public void addMasterInfo(MasterInfo masterInfo) {
		if (masterList == null) {
			masterList = new ArrayList<MasterInfo>();
		}
		masterList.add(masterInfo);
	}

	public List<SlaveInfo> getSlaveList() {
		return slaveList;
	}

	public void setSlaveList(List<SlaveInfo> slaveList) {
		this.slaveList = slaveList;
	}

	/**
	 * 
	 * Add slave database information
	 * 
	 * @param slaveInfo the SlaveInfo obj
	 */
	public void addSlaveInfo(SlaveInfo slaveInfo) {
		if (slaveList == null) {
			slaveList = new ArrayList<SlaveInfo>();
		}
		slaveList.add(slaveInfo);
	}

	public DistributorInfo getDistInfo() {
		return distInfo;
	}

	public void setDistInfo(DistributorInfo distInfo) {
		this.distInfo = distInfo;
	}

}
