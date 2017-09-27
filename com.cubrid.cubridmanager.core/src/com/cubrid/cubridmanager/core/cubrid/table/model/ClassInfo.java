package com.cubrid.cubridmanager.core.cubrid.table.model;

import com.cubrid.cubridmanager.core.utils.ModelUtil.ClassType;

public class ClassInfo {

	private String className;
	private String ownerName;
	private ClassType classType;
	private boolean isSystemClass;
	private boolean isPartitionedClass;

	public ClassInfo(String className) {
		this.className = className;
	}

	public ClassInfo(String className, String ownerName, ClassType classType,
			boolean isSystemClass, boolean isPartitionedClass) {
		this.className = className;
		this.ownerName = ownerName;
		this.classType = classType;
		this.isSystemClass = isSystemClass;
		this.isPartitionedClass = isPartitionedClass;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public ClassType getClassType() {
		return classType;
	}

	public void setClassType(ClassType classType) {
		this.classType = classType;
	}

	public boolean isSystemClass() {
		return isSystemClass;
	}

	public void setSystemClass(boolean isSystemClass) {
		this.isSystemClass = isSystemClass;
	}

	public boolean isPartitionedClass() {
		return isPartitionedClass;
	}

	public void setPartitionedClass(boolean isPartitionedClass) {
		this.isPartitionedClass = isPartitionedClass;
	}

}
