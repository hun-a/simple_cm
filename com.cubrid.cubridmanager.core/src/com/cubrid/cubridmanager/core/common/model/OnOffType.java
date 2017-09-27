package com.cubrid.cubridmanager.core.common.model;

public enum OnOffType {
	ON("ON"), OFF("OFF");

	String text = null;

	OnOffType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
