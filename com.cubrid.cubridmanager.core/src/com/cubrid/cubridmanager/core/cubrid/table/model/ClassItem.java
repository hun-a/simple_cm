package com.cubrid.cubridmanager.core.cubrid.table.model;

import java.util.ArrayList;
import java.util.List;

import com.cubrid.cubridmanager.core.utils.ModelUtil.ClassType;

public class ClassItem {
	private String classname = null;
	/* this class's owner DBA,PUBLIC... */
	private String owner = null;
	/*
	 * a normal class(table) or a virtual class(view),it's value is view or
	 * normal
	 */
	private String virtual = null;

	private List<String> superclassList;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getVirtual() {
		return virtual;
	}

	public void setVirtual(String classType) {
		this.virtual = classType;
	}

	public boolean isVirtual() {
		return virtual != null && virtual.equals(ClassType.VIEW.getText());
	}

	/**
	 * Get supper class list
	 * 
	 * @return List<String> The supercalssList
	 */
	public List<String> getSuperclassList() {
		if (superclassList == null) {
			superclassList = new ArrayList<String>();
		}
		return superclassList;
	}

	/**
	 * Add a supper class to superclassList
	 * 
	 * @param superClass String A string that includes the info super class
	 */
	public void addSuperclass(String superClass) {
		if (superclassList == null) {
			superclassList = new ArrayList<String>();
		}
		this.superclassList.add(superClass);
	}
}
