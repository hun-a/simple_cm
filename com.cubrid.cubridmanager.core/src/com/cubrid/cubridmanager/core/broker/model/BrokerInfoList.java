package com.cubrid.cubridmanager.core.broker.model;

import java.util.ArrayList;
import java.util.List;

public class BrokerInfoList {

	private final List<BrokerInfo> brokerInfoList;

	public BrokerInfoList() {
		brokerInfoList = new ArrayList<BrokerInfo>();
	}

	public List<BrokerInfo> getBrokerInfoList() {
		return brokerInfoList;
	}

	/**
	 * add a brokerInfo
	 * 
	 * @param brokerInfo BrokerInfo
	 */
	public void addBroker(BrokerInfo brokerInfo) {
		if (!brokerInfoList.contains(brokerInfo)) {
			brokerInfoList.add(brokerInfo);
		}
	}
}
