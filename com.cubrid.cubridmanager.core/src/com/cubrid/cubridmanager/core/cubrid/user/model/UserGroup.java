package com.cubrid.cubridmanager.core.cubrid.user.model;

import java.util.ArrayList;
import java.util.List;

public class UserGroup {

	private List<String> group;

	public List<String> getGroup() {
		return group;
	}

	/**
	 * 
	 * Add a group
	 * 
	 * @param group String
	 */
	public void addGroup(String group) {
		synchronized (this) {
			if (this.group == null) {
				this.group = new ArrayList<String>();
			}
			this.group.add(group);
		}
	}

}
