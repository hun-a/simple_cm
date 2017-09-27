package com.cubrid.cubridmanager.core.cubrid.jobauto.model;

public class BackupPlanInfo {

	private String dbname = null;
	private String backupid = null;
	private String path = null;
	private String period_type = null;
	private String period_date = null;
	private String time = null;
	private String level = null;
	// ON or OFF
	private String archivedel = null;
	// ON or OFF
	private String updatestatus = null;
	// ON or OFF
	private String storeold = null;
	// ON or OFF
	private String onoff = null;
	// y or n
	private String zip = null;
	// y or n
	private String check = null;
	// thream number
	private String mt = null;
	// bknum
	private String bknum = null;

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getBackupid() {
		return backupid;
	}

	public void setBackupid(String backupid) {
		this.backupid = backupid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPeriod_type() {
		return period_type;
	}

	public void setPeriod_type(String periodType) {
		this.period_type = periodType;
	}

	public String getPeriod_date() {
		return period_date;
	}

	public void setPeriod_date(String periodDate) {
		this.period_date = periodDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getArchivedel() {
		return archivedel;
	}

	public void setArchivedel(String archivedel) {
		this.archivedel = archivedel;
	}

	public String getUpdatestatus() {
		return updatestatus;
	}

	public void setUpdatestatus(String updatestatus) {
		this.updatestatus = updatestatus;
	}

	public String getStoreold() {
		return storeold;
	}

	public void setStoreold(String storeold) {
		this.storeold = storeold;
	}

	public String getOnoff() {
		return onoff;
	}

	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getMt() {
		return mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getBknum() {
		return bknum;
	}

	public void setBknum(String bknum) {
		this.bknum = bknum;
	}

}
