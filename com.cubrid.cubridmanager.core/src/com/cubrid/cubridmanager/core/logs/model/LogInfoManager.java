package com.cubrid.cubridmanager.core.logs.model;

import java.util.HashMap;
import java.util.Map;

public class LogInfoManager {

	private final Map<String, DbLogInfos> dbLogInfosMap = new HashMap<String, DbLogInfos>();

	/**
	 * add dbLogInfos to the dbLogInfosMap.
	 * 
	 * @param dbLogInfos DbLogInfos
	 */
	public void addDbLogInfos(DbLogInfos dbLogInfos) {
		dbLogInfosMap.put(dbLogInfos.getDbname(), dbLogInfos);
	}

	/**
	 * get the log information
	 * 
	 * @param dbName String
	 * @param path String
	 * @return LogInfo
	 */
	public LogInfo getDbLogInfo(String dbName, String path) {
		return dbLogInfosMap.get(dbName).getDbLogInfo(path);
	}
}
