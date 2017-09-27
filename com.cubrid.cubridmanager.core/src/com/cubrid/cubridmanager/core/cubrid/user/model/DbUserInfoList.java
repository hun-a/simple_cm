package com.cubrid.cubridmanager.core.cubrid.user.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cubrid.cubridmanager.core.common.model.IModel;

public class DbUserInfoList implements
		IModel {

	private String dbname;
	private List<DbUserInfo> userList;

	private Map<String, DbUserInfo> userMap;

	public String getTaskName() {
		return "userinfo";
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public List<DbUserInfo> getUserList() {
		return userList;
	}

	/**
	 * add user
	 * 
	 * @param user DbUserInfo
	 */
	public void addUser(DbUserInfo user) {
		synchronized (this) {
			if (userList == null) {
				userList = new ArrayList<DbUserInfo>();
			}
			if (!userList.contains(user)) {
				userList.add(user);
			}
		}
	}

	/**
	 * remove user
	 * 
	 * @param user DbUserInfo
	 */
	public void removeUser(DbUserInfo user) {
		synchronized (this) {
			if (userList != null) {
				userList.remove(user);
			}
		}
	}

	/**
	 * Get the user map
	 * 
	 * @return Map<String, DbUserInfo>
	 */
	public Map<String, DbUserInfo> getUserMap() {
		synchronized (this) {
			if (userList == null || userList.isEmpty()) {
				return new HashMap<String, DbUserInfo>();
			}
			if (userMap == null) {
				userMap = new HashMap<String, DbUserInfo>();
			}
			for (DbUserInfo bean : userList) {
				if (!userMap.containsKey(bean.getName())) {
					userMap.put(bean.getName(), bean);
				}
			}
			return userMap;
		}
	}
}
