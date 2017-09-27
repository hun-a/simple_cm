package com.cubrid.common.ui.query.result;

import java.util.ArrayList;
import java.util.List;

import com.cubrid.common.ui.query.control.ColumnInfo;

public class QueryResultFilterSetting {
	public enum MatchType {
		MATCH_FROM_START, MATCH_EXACTLY, MATCH_ANYWHERE
	}

	private List<ColumnInfo> filterColumnInfoList = new ArrayList<ColumnInfo>();
	private boolean isSearchAllColumn = true;
	private boolean isCaseSensitive = false;
	private boolean isInCaseSensitive = true;
	private boolean isUsingRegex = false;
	private boolean isUsingWildCard = false;
	private MatchType matchType = MatchType.MATCH_ANYWHERE;
	private String content;

	public List<ColumnInfo> getFilterColumnInfoList() {
		return filterColumnInfoList;
	}

	public void setFilterColumnInfoList(List<ColumnInfo> filterColumnInfoList) {
		this.filterColumnInfoList = filterColumnInfoList;
	}

	public boolean isCaseSensitive() {
		return isCaseSensitive;
	}

	public void setCaseSensitive(boolean isCaseSensitive) {
		this.isCaseSensitive = isCaseSensitive;
	}

	public boolean isUsingRegex() {
		return isUsingRegex;
	}

	public void setUsingRegex(boolean isUsingRegex) {
		this.isUsingRegex = isUsingRegex;
	}

	public boolean isInCaseSensitive() {
		return isInCaseSensitive;
	}

	public void setInCaseSensitive(boolean isInCaseSensitive) {
		this.isInCaseSensitive = isInCaseSensitive;
	}

	public boolean isUsingWildCard() {
		return isUsingWildCard;
	}

	public void setUsingWildCard(boolean isUsingWildCard) {
		this.isUsingWildCard = isUsingWildCard;
	}

	public MatchType getMatchType() {
		return matchType;
	}

	public void setMatchType(MatchType matchType) {
		this.matchType = matchType;
	}

	public boolean isSearchAllColumn() {
		return isSearchAllColumn;
	}

	public void setSearchAllColumn(boolean isSearchAllColumn) {
		this.isSearchAllColumn = isSearchAllColumn;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
