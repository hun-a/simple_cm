package com.cubrid.cubridmanager.core.mondashboard.model;

public enum DBStatusType {
	UNKNOWN("unknown"), STOPPED("stopped"), STOPPED_HA("stopped/HA"), CS_Mode(
			"CS-mode"), ACTIVE("active"), STANDBY("standby"), TO_BE_ACTIVE(
			"to-be-active"), TO_BE_STANDBY("to-be-standby"), MAINTENANCE(
			"maintenance");
	String text = null;

	DBStatusType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	/**
	 * 
	 * Get showed text in UI
	 * 
	 * @param type DBStatusType
	 * @return The String
	 */
	public static String getShowText(DBStatusType type) {
		String ha = "/HA";
		if (ACTIVE == type || STANDBY == type || TO_BE_ACTIVE == type
				|| TO_BE_STANDBY == type || MAINTENANCE == type) {
			return type.getText() + ha;
		}
		return type.getText();
	}

	/**
	 * 
	 * Convert the text to DBStatusType
	 * 
	 * @param text The String
	 * @param isHAMode The boolean
	 * @return DBStatusType
	 */
	public static DBStatusType getType(String text, boolean isHAMode) {
		if (STOPPED.getText().equals(text) && isHAMode) {
			return STOPPED_HA;
		}
		DBStatusType[] typeArr = DBStatusType.values();
		for (DBStatusType type : typeArr) {
			if (type.getText().equals(text)) {
				return type;
			}
		}
		return UNKNOWN;
	}

	/**
	 * 
	 * Return the database is started
	 * 
	 * @param statusType DBStatusType
	 * @return boolean
	 */
	public static boolean isDbStarted(DBStatusType statusType) {
		return statusType == DBStatusType.CS_Mode
				|| statusType == DBStatusType.ACTIVE
				|| statusType == DBStatusType.STANDBY
				|| statusType == DBStatusType.MAINTENANCE
				|| statusType == DBStatusType.TO_BE_ACTIVE
				|| statusType == DBStatusType.TO_BE_STANDBY;
	}
}
