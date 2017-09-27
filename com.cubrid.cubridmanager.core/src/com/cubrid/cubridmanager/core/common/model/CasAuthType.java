package com.cubrid.cubridmanager.core.common.model;

public enum CasAuthType {
	AUTH_NONE("none"), AUTH_ADMIN("admin"), AUTH_MONITOR("monitor");
	String text = null;

	CasAuthType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
