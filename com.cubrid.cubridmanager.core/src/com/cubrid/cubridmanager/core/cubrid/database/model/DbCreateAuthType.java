package com.cubrid.cubridmanager.core.cubrid.database.model;

public enum DbCreateAuthType {
	AUTH_NONE("none"), AUTH_ADMIN("admin");
	String text = null;

	DbCreateAuthType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
