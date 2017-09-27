package com.cubrid.common.ui.spi;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import com.cubrid.common.ui.spi.event.CubridNodeChangedEvent;
import com.cubrid.common.ui.spi.event.CubridNodeChangedEventType;
import com.cubrid.common.ui.spi.event.ICubridNodeChangedListener;
import com.cubrid.common.ui.spi.model.CubridServer;
import com.cubrid.common.ui.spi.model.ICubridNode;
import com.cubrid.cubridmanager.core.common.model.ServerInfo;
import com.cubrid.cubridmanager.core.cubrid.database.task.CheckFileTask;

public class ConnectionKeepAliveHandler implements
ICubridNodeChangedListener {

	private final Map<String, ServerInfo> connectedServers = new ConcurrentHashMap<String, ServerInfo>();
	private static final ConnectionKeepAliveHandler INSTANCE = new ConnectionKeepAliveHandler();

	public static ConnectionKeepAliveHandler getInstance() {
		return INSTANCE;
	}

	private ConnectionKeepAliveHandler() {
		sendHeartBeat();
	}

	private void sendHeartBeat() {
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			public void run() {
				Iterator<String> it = connectedServers.keySet().iterator();
				while (it.hasNext()) {
					ServerInfo serverInfo = connectedServers.get(it.next());
					runHeartBeatTask(serverInfo);
					sleep();
				}
			}
		};
		timer.scheduleAtFixedRate(timerTask, new Date(), 600000); // 10 minute
	}

	private void runHeartBeatTask(ServerInfo serverInfo) {
		CheckFileTask checkFileTask = new CheckFileTask(serverInfo);
		checkFileTask.execute();
	}

	private void sleep() {
		try {
			Thread.sleep(10);
		} catch (Exception ignored) {
		}
	}

	@Override
	public void nodeChanged(CubridNodeChangedEvent event) {
		ICubridNode cubridNode = event.getCubridNode();
		CubridNodeChangedEventType eventType = event.getType();
		if (cubridNode == null || eventType == null) {
			return;
		}

		if (!(cubridNode instanceof CubridServer)) {
			return;
		}

		CubridServer server = (CubridServer) cubridNode;
		ServerInfo serverInfo = server.getServerInfo();
		if (CubridNodeChangedEventType.SERVER_CONNECTED.equals(eventType)) {
			addServer(serverInfo);
		} else if (CubridNodeChangedEventType.SERVER_DISCONNECTED.equals(eventType)) {
			delServer(serverInfo);
		}
	}

	private void addServer(ServerInfo serverInfo) {
		String uid = generateServerId(serverInfo);
		if (!connectedServers.containsKey(uid)) {
			connectedServers.put(uid, serverInfo);
		}
	}

	private void delServer(ServerInfo serverInfo) {
		String uid = generateServerId(serverInfo);
		connectedServers.remove(uid);
	}

	private String generateServerId(ServerInfo serverInfo) {
		StringBuilder sb = new StringBuilder();
		sb.append(serverInfo.getHostAddress());
		sb.append(",");
		sb.append(serverInfo.getHostMonPort());
		sb.append(",");
		sb.append(serverInfo.getHostJSPort());
		return sb.toString();
	}

}
