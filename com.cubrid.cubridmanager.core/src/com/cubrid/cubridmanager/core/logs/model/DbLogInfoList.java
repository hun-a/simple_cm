package com.cubrid.cubridmanager.core.logs.model;

import java.util.ArrayList;
import java.util.List;

public class DbLogInfoList {

	private List<LogInfo> dbLogInfoList = null;

	/**
	 * The constructor
	 */
	public DbLogInfoList() {
		dbLogInfoList = new ArrayList<LogInfo>();
	}

	/**
	 * add a database log info to a DbLogInfo list.
	 * 
	 * @param dbLogInfo LogInfo
	 */
	public void addLog(LogInfo dbLogInfo) {
		synchronized (this) {
			if (!dbLogInfoList.contains(dbLogInfo)) {
				dbLogInfoList.add(dbLogInfo);
			}
		}
	}

	/**
	 * remove a database log information from dbLogInfoList.
	 * 
	 * @param dbLogInfo LogInfo
	 */
	public void removeLog(LogInfo dbLogInfo) {
		synchronized (this) {
			dbLogInfoList.remove(dbLogInfo);
		}
	}

	/**
	 * clear the dbLogInfoList.
	 * 
	 */
	public void removeAllLog() {
		synchronized (this) {
			dbLogInfoList.clear();
		}
	}

	/**
	 * get the dbLogInfoList.
	 * 
	 * @return List<LogInfo>
	 */
	public List<LogInfo> getDbLogInfoList() {
		return dbLogInfoList;
	}

	/**
	 * get log information of it's path equal to parameter path.
	 * 
	 * @param path String
	 * @return LogInfo
	 */
	public LogInfo getDbLogInfo(String path) {
		synchronized (this) {
			for (LogInfo dbLogInfo : dbLogInfoList) {
				if (dbLogInfo.getPath().equals(path)) {
					return dbLogInfo;
				}
			}
			return null;
		}
	}
}
