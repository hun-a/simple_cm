package com.cubrid.common.ui.common.persist;

public interface IPersisteManager { // FIXME logic code move to core module

	/**
	 * Update the user's password
	 *
	 * @param address
	 * @param monPort
	 * @param dbName
	 * @param userName
	 * @param password
	 */
	public void updateDBPassword(String serverName, String address, String monPort,
			String dbName, String userName, String password, boolean isSavePassword);

	/**
	 * Save all the server's information
	 *
	 */
	public void savaAllServers();

	/**
	 * Save all the group information
	 *
	 */
	public void saveAllGroupNodes();
}
