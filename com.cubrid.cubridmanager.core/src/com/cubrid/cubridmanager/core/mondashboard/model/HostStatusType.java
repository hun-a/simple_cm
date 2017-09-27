package com.cubrid.cubridmanager.core.mondashboard.model;

public enum HostStatusType {
	UNKNOWN("unknown"), NORMAL("normal"), MASTER("master"), SLAVE("slave"), REPLICA(
			"replica");
	String text = null;

	HostStatusType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	/**
	 * 
	 * Get showed text in UI
	 * 
	 * @param type HostStatusType
	 * @return The String
	 */
	public static String getShowText(HostStatusType type) {
		String ha = "/HA";
		if (MASTER == type || SLAVE == type || REPLICA == type) {
			return type.getText() + ha;
		}
		return type.getText();
	}

	/**
	 * 
	 * Convert the text to HostStatusType
	 * 
	 * @param text The String
	 * @return HostStatusType
	 */
	public static HostStatusType getType(String text) {
		HostStatusType[] typeArr = HostStatusType.values();
		for (HostStatusType type : typeArr) {
			if (type.getText().equals(text)) {
				return type;
			}
		}
		return UNKNOWN;
	}
}
