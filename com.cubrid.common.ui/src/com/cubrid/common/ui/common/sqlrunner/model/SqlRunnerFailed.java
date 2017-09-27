package com.cubrid.common.ui.common.sqlrunner.model;

public class SqlRunnerFailed {
	private long lineIndex;
	private String sql;
	private String errorMessage;

	public SqlRunnerFailed(long lineIndex, String sql, String errorMessage) {
		this.lineIndex = lineIndex;
		this.sql = sql;
		this.errorMessage = errorMessage;
	}

	public long getLineIndex() {
		return lineIndex;
	}

	public void setLineIndex(long lineIndex) {
		this.lineIndex = lineIndex;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
