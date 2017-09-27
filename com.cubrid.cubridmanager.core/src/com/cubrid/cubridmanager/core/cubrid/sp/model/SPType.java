package com.cubrid.cubridmanager.core.cubrid.sp.model;

public enum SPType {
	FUNCTION("FUNCTION"), PROCEDURE("PROCEDURE");

	String text = null;

	SPType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
