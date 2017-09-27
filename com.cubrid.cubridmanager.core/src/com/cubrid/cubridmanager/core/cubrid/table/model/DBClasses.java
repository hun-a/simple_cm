package com.cubrid.cubridmanager.core.cubrid.table.model;

import com.cubrid.cubridmanager.core.common.model.IModel;

public class DBClasses implements
IModel {
	private String dbname;
	private ClassList systemClassList = null;
	private ClassList userClassList = null;

	public DBClasses() {
		systemClassList = new ClassList();
		userClassList = new ClassList();
	}

	/* (non-Javadoc)
	 * @see com.cubrid.cubridmanager.core.common.model.IModel#getTaskName()
	 */
	public String getTaskName() {
		return "classinfo";
	}

	/**
	 * Set the key "dbname" in request message
	 * 
	 * @param dbname String the database name
	 */
	public void setDbName(String dbname) {
		this.dbname = dbname;
	}

	/**
	 * Return all system defined classes
	 * 
	 * @return ClassList a list includes the object of SystemClass
	 */
	public ClassList getSystemClassList() {
		if (null == systemClassList) {
			systemClassList = new ClassList();
		}
		return systemClassList;

	}

	/**
	 * Return all user defined classes
	 * 
	 * @return ClassList the list includes the object of ClassList
	 */
	public ClassList getUserClassList() {
		if (null == userClassList) {
			userClassList = new ClassList();
		}
		return userClassList;
	}

	/**
	 * add a class item to system class list
	 * 
	 * @param classList ClassList The given list includes the object of
	 *        ClassList
	 */
	public void addSystemClass(ClassList classList) {
		this.systemClassList = classList;
	}

	/**
	 * add a class item to user class list
	 * 
	 * @param classList ClassList The given list includes the object of
	 *        ClassList
	 */
	public void addUserClass(ClassList classList) {
		synchronized (this) {
			this.userClassList = classList;
		}
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

}
