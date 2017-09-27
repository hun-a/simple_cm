package com.cubrid.cubridmanager.core.mondashboard.model;

public enum SyncModeType {
	SYNC("sync"), ASYNC("async"), SEMISYNC("semisync");
	String text = null;

	SyncModeType(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	/**
	 * 
	 * Get showed text in UI
	 * 
	 * @param type SyncModeType
	 * @return The String
	 */
	public static String getShowText(SyncModeType type) {
		if (type == null) {
			return "";
		}
		if (type == SEMISYNC) {
			return "semi-sync";
		}
		return type.getText();
	}

	/**
	 * 
	 * Convert the text to SyncModeType
	 * 
	 * @param text The String
	 * @return SyncModeType
	 */
	public static SyncModeType getType(String text) {
		SyncModeType[] typeArr = SyncModeType.values();
		for (SyncModeType type : typeArr) {
			if (type.getText().equals(text)) {
				return type;
			}
		}
		return null;
	}
}
