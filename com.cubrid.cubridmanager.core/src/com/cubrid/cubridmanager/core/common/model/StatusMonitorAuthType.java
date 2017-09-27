package com.cubrid.cubridmanager.core.common.model;

public enum StatusMonitorAuthType {
	AUTH_NONE("none"), AUTH_ADMIN("admin"), AUTH_MONITOR("monitor");
	String text = null;

	StatusMonitorAuthType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}