package com.cubrid.cubridmanager.core.cubrid.database.model;

import java.util.HashMap;
import java.util.Map;

public class ParamDumpInfo {

	private String dbName;
	private Map<String, String> clientData;
	private Map<String, String> serverData;

	/**
	 * get task name.
	 * 
	 * @return String
	 */
	public String getTaskName() {
		return "paramdump";
	}

	/**
	 * get the dbName.
	 * 
	 * @return String
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * set the dbName.
	 * 
	 * @param dbName String
	 */

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * Get the client data.
	 * 
	 * @return Map<String, String> The map of client data
	 */
	public Map<String, String> getClientData() {
		if (clientData == null) {
			return new HashMap<String, String>();
		}
		return clientData;
	}

	/**
	 * Add a pair of key and value to client data
	 * 
	 * @param key String
	 * @param value String
	 */
	public void addClientData(String key, String value) {
		if (clientData == null) {
			clientData = new HashMap<String, String>();
		}
		this.clientData.put(key, value);
	}

	/**
	 * Get the server data
	 * 
	 * @return Map<String, String> The map of server data
	 */
	public Map<String, String> getServerData() {
		if (serverData == null) {
			return new HashMap<String, String>();
		}
		return serverData;
	}

	/**
	 * Add a pair of key and value to server data
	 * 
	 * @param key String
	 * @param value String
	 */
	public void addServerData(String key, String value) {
		if (serverData == null) {
			serverData = new HashMap<String, String>();
		}
		this.serverData.put(key, value);
	}

	/**
	 * set the client data
	 * 
	 * @param clientData the clientData to set
	 */
	public void setClientData(Map<String, String> clientData) {
		this.clientData = clientData;
	}

	/**
	 * set the server data
	 * 
	 * @param serverData the serverData to set
	 */
	public void setServerData(Map<String, String> serverData) {
		this.serverData = serverData;
	}
}
