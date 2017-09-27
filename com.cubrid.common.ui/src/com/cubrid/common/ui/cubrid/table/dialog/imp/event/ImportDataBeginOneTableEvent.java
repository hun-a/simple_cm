package com.cubrid.common.ui.cubrid.table.dialog.imp.event;

public class ImportDataBeginOneTableEvent extends ImportDataEvent{

	public ImportDataBeginOneTableEvent(String tableName) {
		this.tableName = tableName;
	}
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
}
