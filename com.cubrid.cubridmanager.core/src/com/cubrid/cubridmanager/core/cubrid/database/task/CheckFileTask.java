package com.cubrid.cubridmanager.core.cubrid.database.task;

import com.cubrid.cubridmanager.core.common.model.ServerInfo;
import com.cubrid.cubridmanager.core.common.socket.SocketTask;

public class CheckFileTask extends
		SocketTask {

	private static final String[] SEND_MSG_ITEMS = new String[]{"task",
		"token", "file" };

	/**
	 * The constructor
	 * 
	 * @param serverInfo ServerInfo the instance of ServerInfo
	 */
	public CheckFileTask(ServerInfo serverInfo) {
		super("checkfile", serverInfo, SEND_MSG_ITEMS);
	}

	/**
	 * Set the file of checking
	 * 
	 * @param files String[] the array that includes some names of files
	 */
	public void setFile(String[] files) {
		super.setMsgItem("file", files);
	}

	/**
	 * Get the result of exit files
	 * 
	 * 
	 * @return String[] null when directories does not exists
	 */
	public String[] getExistFiles() {
		return isSuccess() ? getResponse().getValues("existfile") : null;
	}

}
