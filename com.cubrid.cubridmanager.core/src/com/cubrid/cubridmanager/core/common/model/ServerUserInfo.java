package com.cubrid.cubridmanager.core.common.model;

import java.util.ArrayList;
import java.util.List;

import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;
import com.cubrid.cubridmanager.core.cubrid.database.model.DbCreateAuthType;

public class ServerUserInfo {
	private String userName = null;
	private String password = null;
	private CasAuthType casAuth = CasAuthType.AUTH_NONE;
	private StatusMonitorAuthType statusMonitorAuth = StatusMonitorAuthType.AUTH_NONE;
	private DbCreateAuthType dbCreateAuthType = DbCreateAuthType.AUTH_NONE;
	// All databases that this user can access
	private List<DatabaseInfo> authDatabaseInfoList = null;

	/**
	 * The constructor
	 */
	public ServerUserInfo() {
		//empty
	}

	/**
	 * The constructor
	 * 
	 * @param userName
	 * @param password
	 */
	public ServerUserInfo(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	/**
	 * @param cmUser
	 * @param cmPassword
	 * @param casAuth
	 * @param isDBAAuth
	 */
	public ServerUserInfo(String userName, String password,
			CasAuthType casAuth, DbCreateAuthType dbCreateAuthType,
			StatusMonitorAuthType statusMonitorAuth) {
		super();
		this.userName = userName;
		this.password = password;
		this.casAuth = casAuth;
		this.dbCreateAuthType = dbCreateAuthType;
		this.statusMonitorAuth = statusMonitorAuth;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CasAuthType getCasAuth() {
		return casAuth;
	}

	public void setCasAuth(CasAuthType casAuth) {
		this.casAuth = casAuth;
	}

	public DbCreateAuthType getDbCreateAuthType() {
		return dbCreateAuthType;
	}

	public void setDbCreateAuthType(DbCreateAuthType dbCreateAuthType) {
		this.dbCreateAuthType = dbCreateAuthType;
	}

	public List<DatabaseInfo> getDatabaseInfoList() {
		return authDatabaseInfoList;
	}

	public void setDatabaseInfoList(List<DatabaseInfo> databaseInfoList) {
		this.authDatabaseInfoList = databaseInfoList;
	}

	/**
	 * Add the database info to the object of AuthDatabaseInfoList
	 * 
	 * @param databaseInfo DatabaseInfo The database info
	 */
	public void addDatabaseInfo(DatabaseInfo databaseInfo) {
		if (authDatabaseInfoList == null) {
			authDatabaseInfoList = new ArrayList<DatabaseInfo>();
		}
		if (!authDatabaseInfoList.contains(databaseInfo)) {
			authDatabaseInfoList.add(databaseInfo);
		}
	}

	/**
	 * Remove the database info from the object of AuthDatabaseInfoList
	 * 
	 * @param databaseInfo DatabaseInfo The database info
	 */
	public void removeDatabaseInfo(DatabaseInfo databaseInfo) {
		if (authDatabaseInfoList != null) {
			authDatabaseInfoList.remove(databaseInfo);
		}
	}

	/**
	 *Remove all the object of DatabaseInfo from AuthDatabaseInfoList
	 * 
	 */
	public void removeAllDatabaseInfo() {
		if (authDatabaseInfoList != null) {
			authDatabaseInfoList.clear();
		}
	}

	/**
	 * Get the object of DatabaseInfo based on database name from the instance
	 * of AuthDatabaseInfoList
	 * 
	 * @param dbName String The database name
	 * @return DatabaseInfo
	 */
	public DatabaseInfo getDatabaseInfo(String dbName) {
		for (int i = 0; authDatabaseInfoList != null
				&& i < authDatabaseInfoList.size(); i++) {
			DatabaseInfo databaseInfo = authDatabaseInfoList.get(i);
			if (databaseInfo.getDbName().equalsIgnoreCase(dbName)) {
				return databaseInfo;
			}
		}
		return null;
	}

	public StatusMonitorAuthType getStatusMonitorAuth() {
		return statusMonitorAuth;
	}

	public void setStatusMonitorAuth(StatusMonitorAuthType statusMonitorAuth) {
		this.statusMonitorAuth = statusMonitorAuth;
	}

	public boolean isAdmin() {
		return userName != null && userName.equals("admin");
	}

}
