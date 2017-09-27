package com.cubrid.cubridmanager.core.replication.model;

import java.util.ArrayList;
import java.util.List;

public class MasterInfo {

	private String masterIp;
	private String masterDbName;
	private String replServerPort;
	private List<String> replTableList = new ArrayList<String>();
	private boolean isReplAllTable = false;

	public String getMasterIp() {
		return masterIp;
	}

	public void setMasterIp(String masterIp) {
		this.masterIp = masterIp;
	}

	public String getMasterDbName() {
		return masterDbName;
	}

	public void setMasterDbName(String masterDbName) {
		this.masterDbName = masterDbName;
	}

	public String getReplServerPort() {
		return replServerPort;
	}

	public void setReplServerPort(String replServerPort) {
		this.replServerPort = replServerPort;
	}

	public List<String> getReplTableList() {
		return replTableList;
	}

	public void setReplTableList(List<String> replTableList) {
		this.replTableList = replTableList;
	}

	public boolean isReplAllTable() {
		return isReplAllTable;
	}

	public void setReplAllTable(boolean isReplAllTable) {
		this.isReplAllTable = isReplAllTable;
	}

}
