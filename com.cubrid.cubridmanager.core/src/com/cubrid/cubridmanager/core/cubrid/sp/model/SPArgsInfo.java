package com.cubrid.cubridmanager.core.cubrid.sp.model;

public class SPArgsInfo {

	private String spName;
	private int index;
	private String argName;
	private String dataType;
	private String description;
	private SPArgsType spArgsType;

	//Constructor
	public SPArgsInfo() {
		// empty
	}

	public SPArgsInfo(String spName, String argName, int index, String dataType,
			SPArgsType spArgsType, String description) {
		this.spName = spName;
		this.argName = argName;
		this.index = index;
		this.dataType = dataType;
		this.spArgsType = spArgsType;
		this.description = description;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getArgName() {
		return argName;
	}

	public void setArgName(String argName) {
		this.argName = argName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SPArgsType getSpArgsType() {
		return spArgsType;
	}

	public void setSpArgsType(SPArgsType spArgsType) {
		this.spArgsType = spArgsType;
	}

}
