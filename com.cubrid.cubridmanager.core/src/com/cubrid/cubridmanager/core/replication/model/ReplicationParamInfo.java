package com.cubrid.cubridmanager.core.replication.model;

import java.util.HashMap;
import java.util.Map;

public class ReplicationParamInfo {

	private Map<String, String> paramMap = null;

	/**
	 * 
	 * Get the parameter value
	 * 
	 * @param key the key
	 * @return the value
	 */
	public String getParamValue(String key) {
		if (paramMap != null && key != null) {
			return paramMap.get(key);
		}
		return null;
	}

	/**
	 * 
	 * Set param value
	 * 
	 * @param key the key
	 * @param value the value
	 */
	public void setParamValue(String key, String value) {
		if (key == null || key.trim().length() <= 0 || value == null) {
			return;
		}
		if (paramMap == null) {
			paramMap = new HashMap<String, String>();
		}
		paramMap.put(key, value);
	}

	/**
	 * @return the paramMap
	 */
	public Map<String, String> getParamMap() {
		return paramMap;
	}

	/**
	 * @param paramMap the paramMap to set
	 */
	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

}
