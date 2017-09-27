package com.cubrid.cubridmanager.core.common.task;

import com.cubrid.common.core.util.StringUtil;
import com.cubrid.cubridmanager.core.common.model.CertStatus;
import com.cubrid.cubridmanager.core.common.model.ServerInfo;
import com.cubrid.cubridmanager.core.common.socket.SocketTask;
import com.cubrid.cubridmanager.core.common.socket.TreeNode;

public class GetCmsEnvTask extends
		SocketTask {
	/**
	 * The constructor
	 * 
	 * @param serverInfo
	 */
	public GetCmsEnvTask(ServerInfo serverInfo) {
		super("getcmsenv", serverInfo, null);
		setUsingMonPort(true);
		setNeedServerConnected(false);
	}

	/**
	 * 
	 * Get CUBRID Manager server version
	 * 
	 * @return String
	 */
	public String getVersion() {
		TreeNode node = getResponse();
		if (node == null
				|| (getErrorMsg() != null && getErrorMsg().trim().length() > 0)) {
			return null;
		}
		return node.getValue("CUBRIDVER");
	}

	public CertStatus getCertStatus() {
		TreeNode node = getResponse();
		if (node == null || (getErrorMsg() != null && getErrorMsg().trim().length() > 0)) {
			return CertStatus.UNKNOWN;
		}

		String status = node.getValue("is_default_cert");
		if (StringUtil.isEmpty(status)) {
			return CertStatus.UNKNOWN;
		} else if (StringUtil.booleanValueWithYN(status)) {
			return CertStatus.DEFAULT;
		} else {
			return CertStatus.CUSTOMIZED;
		}
	}

	public boolean canConnect() {
		return clientService.canConnect();
	}
}
