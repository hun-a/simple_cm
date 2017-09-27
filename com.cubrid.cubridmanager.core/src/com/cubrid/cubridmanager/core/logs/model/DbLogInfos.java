package com.cubrid.cubridmanager.core.logs.model;

import com.cubrid.cubridmanager.core.common.model.IModel;

public class DbLogInfos implements
IModel {

	private String dbname = null;
	private DbLogInfoList dbLogInfoList = null;

	/**
	 * The constructor
	 */
	public DbLogInfos() {
		dbLogInfoList = new DbLogInfoList();
	}

	/**
	 * get task name.
	 * 
	 * @return String
	 */
	public String getTaskName() {
		return "getloginfo";
	}

	/**
	 * get the dbname.
	 * 
	 * @return String
	 */
	public String getDbname() {
		return dbname;
	}

	/**
	 * set the dbname.
	 * 
	 * @param dbname String
	 */
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	/**
	 * get the dbLogInfoList.
	 * 
	 * @return DbLogInfoList
	 */
	public DbLogInfoList getDbLogInfoList() {
		if (dbLogInfoList == null) {
			dbLogInfoList = new DbLogInfoList();
		}
		return dbLogInfoList;
	}

	/**
	 * set the dbLogInfoList.
	 * 
	 * @param dbLogInfoList DbLogInfoList
	 */
	public void addLogInfo(DbLogInfoList dbLogInfoList) {
		this.dbLogInfoList = dbLogInfoList;
	}

	/**
	 * get log information of it's path equal to parameter path.
	 * 
	 * @param path String
	 * @return LogInfo
	 */
	public LogInfo getDbLogInfo(String path) {
		if (dbLogInfoList != null) {
			return dbLogInfoList.getDbLogInfo(path);
		}
		return null;
	}

	/**
	 * @param dbLogInfoList the dbLogInfoList to set
	 */
	public void setDbLogInfoList(DbLogInfoList dbLogInfoList) {
		this.dbLogInfoList = dbLogInfoList;
	}
}
