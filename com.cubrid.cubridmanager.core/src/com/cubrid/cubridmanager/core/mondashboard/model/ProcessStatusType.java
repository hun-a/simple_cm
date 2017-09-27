package com.cubrid.cubridmanager.core.mondashboard.model;

public enum ProcessStatusType {
	UNKNOWN("unknown"), 
	DEAD("dead"), 
	DEGISTERED("degistered"), 
	REGISTERED("registered"), 
	STARTED("started"), 
	NOT_REGISTERED("not_registered"), 
	REGISTERED_AND_ACTIVE("registered_and_active"), 
	REGISTERED_AND_STANDBY("registered_and_standby"), 
	REGISTERED_AND_TO_BE_STANDBY("registered_and_to_be_standby"),
	REGISTERED_AND_TO_BE_ACTIVE("registered_and_to_be_active");

	String text = null;

	ProcessStatusType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	/**
	 * 
	 * Convert the text to ProcessStatusType
	 * 
	 * @param text The String
	 * @return ProcessStatusType
	 */
	public static ProcessStatusType getType(String text) {
		ProcessStatusType[] typeArr = ProcessStatusType.values();
		for (ProcessStatusType type : typeArr) {
			if (type.getText().equals(text)) {
				return type;
			}
		}
		return UNKNOWN;
	}
}
