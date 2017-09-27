package com.cubrid.cubridmanager.core.shard.model;

public interface IShardModel {

	/**
	 * Parse response data to IShardModel
	 * 
	 * @param confData
	 */
	void parse(String[] confData);

	/**
	 * Get shard configuration file name.
	 * 
	 * @return
	 */
	String getFileName();

}
