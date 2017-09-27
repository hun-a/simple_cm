package com.cubrid.common.ui.cubrid.table.event;

public class ExportDataBeginOneTableEvent extends ExportDataEvent{

	private String tableName;

	public ExportDataBeginOneTableEvent(String tableName) {
		this.tableName = tableName;
	}
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
}
