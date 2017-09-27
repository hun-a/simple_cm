package com.cubrid.cubridmanager.core.cubrid.jobauto.model;

public class QueryPlanInfo {

	private String dbname = "";
	private String query_id = "";
	private String period = "";
	private String detail = "";
	private String query_string = "";
	private String username = "";
	private String userpass = "unknown";

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getQuery_id() {
		return query_id;
	}

	public void setQuery_id(String queryId) {
		this.query_id = queryId;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getQuery_string() {
		return query_string;
	}

	public void setQuery_string(String queryString) {
		this.query_string = queryString;
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userPass) {
		this.userpass = userPass;
	}

}
