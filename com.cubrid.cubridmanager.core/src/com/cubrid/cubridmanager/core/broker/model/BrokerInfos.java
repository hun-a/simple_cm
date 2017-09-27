package com.cubrid.cubridmanager.core.broker.model;

import com.cubrid.cubridmanager.core.common.model.IModel;

public class BrokerInfos implements
		IModel {
	public static final String TASK_NAME = "getbrokersinfo";
	private String brokerstatus = null;
	private BrokerInfoList borkerInfoList = null;
	private String bname = "";

	public BrokerInfos() {
		borkerInfoList = new BrokerInfoList();
	}

	public String getTaskName() {
		return "getbrokersinfo";
	}

	public String getBrokerstatus() {
		return brokerstatus;
	}

	public void setBrokerstatus(String brokerstatus) {
		this.brokerstatus = brokerstatus;
	}

	public BrokerInfoList getBorkerInfoList() {
		return borkerInfoList;
	}

	/**
	 *Set a brokerInfoList
	 * 
	 * @param borkerInfoList BrokerInfoList
	 */
	public void addBrokersInfo(BrokerInfoList borkerInfoList) {
		this.borkerInfoList = borkerInfoList;
	}

	/**
	 * Get the broker name.
	 * 
	 * @return the bname
	 */
	public String getBname() {
		return bname;
	}

	/**
	 * Set the broker name.
	 * 
	 * @param bname the bname to set
	 */
	public void setBname(String bname) {
		this.bname = bname;
	}

}
