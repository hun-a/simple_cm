package com.cubrid.common.ui.cubrid.table.dialog.imp.model;

import java.util.ArrayList;
import java.util.List;

import com.cubrid.common.ui.common.sqlrunner.model.SqlRunnerFailed;

public class ImportMonitor {

	public static final int STATUS_WAITING = 0;
	public static final int STATUS_RUNNING = 1;
	public static final int STATUS_FINISHED = 2;
	public static final int STATUS_STOPED = 3;
	public static final int STATUS_FAILED = 4;

	private String tableName;
	private long totalCount;
	private long parseCount;
	private long failedCount;
	private long elapsedTime = 0;
	private long beginTime;
	private int status = STATUS_WAITING;//0 waiting 1 running 2 finished 3 stoped

	private List<SqlRunnerFailed> failList = new ArrayList<SqlRunnerFailed>();

	/**
	 * The constructor
	 *
	 * @param tableName
	 */
	public ImportMonitor(String tableName) {
		this.tableName = tableName;
		this.totalCount = 0;
		this.parseCount = 0;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getParseCount() {
		return parseCount;
	}

	public void setParseCount(long parseCount) {
		this.parseCount = parseCount;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(long failedCount) {
		this.failedCount = failedCount;
	}

	public List<SqlRunnerFailed> getFailList() {
		return failList;
	}

	public void setFailList(List<SqlRunnerFailed> failList) {
		this.failList = failList;
	}

	public void addFailToList(SqlRunnerFailed po) {
		this.failList.add(po);
	}
}
