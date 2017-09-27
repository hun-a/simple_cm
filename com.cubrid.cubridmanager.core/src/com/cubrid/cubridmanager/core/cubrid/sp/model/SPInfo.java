package com.cubrid.cubridmanager.core.cubrid.sp.model;

import java.util.ArrayList;
import java.util.List;

public class SPInfo {

	private String spName;
	private SPType spType;
	private String returnType;
	private String language;
	private String owner;
	private String target;
	private String description;
	private List<SPArgsInfo> argsInfoList = new ArrayList<SPArgsInfo>();

	public SPInfo(String spName) {
		this.spName = spName;
	}

	public SPInfo(String spName, SPType spType, String returnType, String language, String owner,
			String target, String description) {
		super();
		this.spName = spName;
		this.spType = spType;
		this.returnType = returnType;
		this.language = language;
		this.owner = owner;
		this.target = target;
		this.description = description;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public SPType getSpType() {
		return spType;
	}

	public void setSpType(SPType spType) {
		this.spType = spType;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<SPArgsInfo> getArgsInfoList() {
		return argsInfoList;
	}

	public void setArgsInfoList(List<SPArgsInfo> argsInfoList) {
		this.argsInfoList = argsInfoList;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * Add an instance of SPArgsInfo into argsInfoList
	 *
	 * @param spArgsInfo SPArgsInfo An instance of SPArgsInfo
	 */
	public void addSPArgsInfo(SPArgsInfo spArgsInfo) {
		if (this.argsInfoList == null) {
			argsInfoList = new ArrayList<SPArgsInfo>();
		}
		if (!argsInfoList.contains(spArgsInfo)) {
			argsInfoList.add(spArgsInfo);
		}
	}

	/**
	 * Remove the given instance of SPArgsInfo from argsInfoList
	 *
	 * @param spArgsInfo SPArgsInfo The given instance fo SPArgsInfo
	 */
	public void removeSPArgsInfo(SPArgsInfo spArgsInfo) {
		if (this.argsInfoList != null) {
			argsInfoList.remove(spArgsInfo);
		}
	}

}
