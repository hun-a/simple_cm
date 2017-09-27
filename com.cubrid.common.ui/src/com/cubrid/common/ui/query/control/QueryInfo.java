package com.cubrid.common.ui.query.control;

public class QueryInfo implements Cloneable{
	private int totalRs = 0; // total row
	private int pageSize = 100; // size per page
	private int pages = 0; // total page num
	private int currentPage = 1; // current page

	public QueryInfo() {	
	}
	
	public QueryInfo(int totalRs, int pageSize) {
		this.totalRs = totalRs;
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
		if (totalRs % pageSize == 0) {
			pages = totalRs / pageSize;
		} else {
			pages = totalRs / pageSize + 1;
		}
	}

	public int getTotalRs() {
		return totalRs;
	}

	/**
	 * get pages count from total result count.
	 * 
	 * @param totalRs int
	 */
	public void setTotalRs(int totalRs) {
		this.totalRs = totalRs;
		if (pageSize > 0) {
			if (totalRs % pageSize == 0) {
				pages = totalRs / pageSize;
			} else {
				pages = totalRs / pageSize + 1;
			}
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * get pages count from page size.
	 * 
	 * @param pageSize int
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if (totalRs % pageSize == 0) {
			pages = totalRs / pageSize;
		} else {
			pages = totalRs / pageSize + 1;
		}
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * set the current page
	 * 
	 * @param currentPage int
	 */
	public void setCurrentPage(int currentPage) {
		if (currentPage > 0 && currentPage <= pages) {
			this.currentPage = currentPage;
		}
	}
	
	/**
	 * Clone a object
	 * 
	 * @return QueryInfo
	 */
	public QueryInfo clone() {
		QueryInfo queryInfo = null;
		try {
			queryInfo = (QueryInfo) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return queryInfo;
	}
}
