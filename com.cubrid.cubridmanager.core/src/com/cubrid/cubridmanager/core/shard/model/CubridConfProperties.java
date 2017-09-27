package com.cubrid.cubridmanager.core.shard.model;

import java.util.HashMap;
import java.util.Map;

public class CubridConfProperties {
	private Map<String, String> properties = new HashMap<String, String>();

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public String getValue(String key) {
		String value = this.properties.get(key);
		return value == null ? "" : value;
	}

	public void setValue(String key, String value) {
		this.properties.put(key, value);
	}

	protected String getLine(String key) {
		StringBuilder sb = new StringBuilder();
		int blankNumber = 40;
		blankNumber -= key.length();
		blankNumber = blankNumber < 0 ? 8 : blankNumber;
		sb.append(key);
		for (int i = 0; i < blankNumber; i++) {
			sb.append(" ");
		}
		sb.append("=").append(this.getValue(key));
		sb.append("\r\n");
		return sb.toString();
	}

}
