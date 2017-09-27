package com.cubrid.common.ui.common.sqlrunner.event;

public class BeginOneFileEvent extends RunSQLEvent{
	private String fileName;

	public BeginOneFileEvent(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
