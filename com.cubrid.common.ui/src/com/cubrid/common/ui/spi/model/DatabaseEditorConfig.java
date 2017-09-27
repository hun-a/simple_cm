package com.cubrid.common.ui.spi.model;

import org.eclipse.swt.graphics.RGB;

public class  DatabaseEditorConfig{
	
	/*The editor part background*/
	private RGB backGround;
	/*The database comment*/
	private String databaseComment="";

	public String getDatabaseComment() {
		return databaseComment;
	}

	public void setDatabaseComment(String databaseComment) {
		this.databaseComment = databaseComment;
	}

	public RGB getBackGround() {
		return backGround;
	}

	public void setBackGround(RGB backGround) {
		this.backGround = backGround;
	}

	/**
	 * Clone a new object
	 */
	public DatabaseEditorConfig clone(){
		
		DatabaseEditorConfig clonedObject = new DatabaseEditorConfig();
		RGB bg = null;
		if (backGround != null) {
			bg = new RGB(backGround.red, backGround.green, backGround.blue);
		}
		clonedObject.setBackGround(bg);
		clonedObject.setDatabaseComment(databaseComment);
		
		return clonedObject;
	}
}
