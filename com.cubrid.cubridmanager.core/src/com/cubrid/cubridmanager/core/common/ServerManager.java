package com.cubrid.cubridmanager.core.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.cubrid.cubridmanager.core.common.model.ServerInfo;

public final class ServerManager {

	private static final ServerManager instance = new ServerManager();

	private HashMap<String, ServerInfo> serverInfos = new HashMap<String, ServerInfo>();
	
	public static ServerManager getInstance() {
		return instance;
	}
	
	private ServerManager() {
	}
	
	public ServerInfo getServer(String hostAddress, int port, String userName) {
		return serverInfos.get(hostAddress + ":" + port + ":" + userName);
	}

	/**
	 * Return connected status of server
	 * 
	 * @param hostAddress String host address
	 * @param port int host port
	 * @param userName the String
	 * @return boolean
	 */
	public boolean isConnected(String hostAddress, int port, String userName) {
		ServerInfo serverInfo = getServer(hostAddress, port, userName);
		if (serverInfo == null) {
			return false;
		}
		return serverInfo.isConnected();
	}

	/**
	 * Set connected status of server
	 * 
	 * @param hostAddress String host address
	 * @param port int host port
	 * @param userName the String
	 * @param isConnected boolean whether is connected
	 */
	public void setConnected(String hostAddress, int port, String userName, boolean isConnected) {
		synchronized (this) {
			ServerInfo serverInfo = getServer(hostAddress, port, userName);
			if (serverInfo == null) {
				return;
			}
			serverInfo.setConnected(isConnected);
		}
	}
	
	/**
	 * Remove CUBRID Manager server
	 * 
	 * @param hostAddress String host address
	 * @param port int host port
	 * @param userName the String
	 */
	public void removeServer(String hostAddress, int port, String userName) {
		synchronized (this) {
			setConnected(hostAddress, port, userName, false);
			serverInfos.remove(hostAddress + ":" + port + ":" + userName);
		}
	}

	/**
	 * Add CUBRID Manager server information
	 * 
	 * @param hostAddress String host address
	 * @param port int host port
	 * @param value ServerInfo given serverInfo
	 * @param userName the String
	 * @return ServerInfo
	 */
	public ServerInfo addServer(String hostAddress, int port, String userName, ServerInfo value) {
		synchronized (this) {
			return serverInfos.put(hostAddress + ":" + port + ":" + userName, value);
		}
	}

	public void disConnectAllServer() {
		synchronized (this) {
			if (serverInfos != null) {
				List<ServerInfo> serverInfoList = new ArrayList<ServerInfo>();
				serverInfoList.addAll(serverInfos.values());
				Iterator<ServerInfo> it = serverInfoList.iterator();
				while (it.hasNext()) {
					ServerInfo serverInfo = it.next();
					if (serverInfo.isConnected()) {
						setConnected(serverInfo.getHostAddress(), serverInfo.getHostMonPort(),
								serverInfo.getLoginedUserInfo().getUserName(), false);
					}
				}
			}
		}
	}

	public HashMap<String, ServerInfo> getAllServerInfos(){
		return serverInfos;
	}
}
