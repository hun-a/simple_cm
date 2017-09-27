package com.cubrid.cubridmanager.core.cubrid.sp.model;

public enum SPArgsType {

	OUT("OUT"), IN("IN"), INOUT("INOUT");

	String text = null;

	SPArgsType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
