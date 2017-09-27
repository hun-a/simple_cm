package com.cubrid.cubridmanager.core.common.task;

import com.cubrid.cubridmanager.core.common.model.ServerInfo;
import com.cubrid.cubridmanager.core.common.socket.SocketTask;

public class GetTaskStatusTask extends SocketTask {

	private static String TASK_NAME = "gettaskstatus";

	public GetTaskStatusTask(ServerInfo serverInfo, String taskKey) {
		super(TASK_NAME, serverInfo, null);
		super.setMsgItem("uuid", taskKey);
	}

	public GetTaskStatusTask(ServerInfo serverInfo) {
		super(TASK_NAME, serverInfo, null);

	}

	public void setTaskKey(String taskKey) {
		super.setMsgItem("uuid", taskKey);
	}
}
