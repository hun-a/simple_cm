package com.cubrid.cubridmanager.core.broker.task;

import com.cubrid.cubridmanager.core.common.model.IModel;
import com.cubrid.cubridmanager.core.common.model.ServerInfo;
import com.cubrid.cubridmanager.core.common.socket.SocketTask;
import com.cubrid.cubridmanager.core.common.socket.TreeNode;

public class GetBrokerStatusInfosTask<T extends IModel> extends SocketTask {

	private final T result;

	public GetBrokerStatusInfosTask(ServerInfo serverInfo, String[] sendMSGItems, T bean) {
		super(bean.getTaskName(), serverInfo, sendMSGItems);
		this.result = bean;
	}

	/**
	 * Show the result of the database space information
	 * 
	 * @return T
	 */
	public T getResultModel() {
		TreeNode node = (TreeNode) getResponse();
		setFieldValue(node, result);
		return result;
	}

	/**
	 * set brokerName to retrieve broker status informations
	 * 
	 * @param brokerName String
	 */
	public void setBrokerName(String brokerName) {
		super.setMsgItem("bname", brokerName);
	}
}
