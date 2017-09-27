package com.cubrid.common.core.util;

public enum ApplicationType {

	NONE(null, null, null), 
	CUBRID_MANAGER("CUBRID Manager", "CUBRIDManager", "CM"), 
	CUBRID_QUERY_BROWSER("CUBRID Query Browser", "CUBRIDQuery", "CQB"), 
	CUBRID_MIGRATION_TOOLKIT("CUBRID Migration Toolkit", "CUBRIDMigration", "CMT");

	private String longName;
	private String shortName;
	private String rssName;

	private ApplicationType(String longName, String shortName, String rssName) {
		this.longName = longName;
		this.shortName = shortName;
		this.rssName = rssName;
	}

	public String getLongName() {
		return this.longName;
	}

	public String getShortName() {
		return this.shortName;
	}

	public String getRssName() {
		return rssName;
	}

}
