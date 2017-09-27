package com.cubrid.cubridmanager.core.cubrid.dbspace.model;

public class DbSpaceInfo {

	private String spacename = null;

	/*
	 * GENERIC,DATA,TEMP,INDEX,Active_log,Archive_log
	 */
	private String type = null;
	private String location = null;
	private int totalpage = 0;
	private int freepage = 0;
	private int usedpage = 0;
	private int volid;
	private String date = null;
	private String purpose;
	private int volumeCount = 0;
	private String totalPageStr = null;
	private String totalSizeStr = null;

	public String getTotalPageStr() {
		return totalPageStr;
	}

	public void setTotalPageStr(String totalPageStr) {
		this.totalPageStr = totalPageStr;
	}

	public String getTotalSizeStr() {
		return totalSizeStr;
	}

	public void setTotalSizeStr(String totalSizeStr) {
		this.totalSizeStr = totalSizeStr;
	}

	public String getSpacename() {
		return spacename;
	}

	public void setSpacename(String spacename) {
		this.spacename = spacename;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getFreepage() {
		return freepage;
	}

	public void setFreepage(int freepage) {
		this.freepage = freepage;
	}
	
	public int getUsedpage() {
		return usedpage;
	}
	
	public void setUsedpage(int usedpage){
		this.usedpage = usedpage;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public int getVolid() {
		return volid;
	}
	
	public void setVolid(int volid) {
		this.volid = volid;
	}

	public int getVolumeCount() {
		return volumeCount;
	}
	
	public String getPurpose() {
		return purpose;
	}
	
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public String getShortVolumeName(){
		int lastIndex;
		if ((lastIndex = spacename.lastIndexOf('/')) >= 0){
			return spacename.substring(lastIndex+1);
		} else {
			return spacename;
		}
	}

	/**
	 * Get the next volume count value
	 */
	public void plusVolumeCount() {
		this.volumeCount++;
	}

}
