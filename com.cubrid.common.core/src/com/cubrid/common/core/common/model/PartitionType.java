package com.cubrid.common.core.common.model;

public enum PartitionType {
	HASH("HASH"), LIST("LIST"), RANGE("RANGE");

	String text = null;

	PartitionType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
