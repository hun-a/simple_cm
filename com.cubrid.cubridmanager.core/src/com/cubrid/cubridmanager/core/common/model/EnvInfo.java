package com.cubrid.cubridmanager.core.common.model;

public class EnvInfo {

	private String rootDir;
	private String databaseDir;
	private String cmServerDir;
	private ServerVersion serverVersion = null;
	private String brokerVersion;
	private String[] hostMonTabStatus;
	// os(NT,LINUX,UNIX)
	private String osInfo;
	/**
	 * 
	 * Get CUBRID database root dir
	 * 
	 * @return String
	 */
	public String getRootDir() {
		return rootDir;
	}

	/**
	 * 
	 * Set CUBRID database root dir
	 * 
	 * @param rootDir String The root path
	 */
	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	/**
	 * 
	 * Get CUBRID database dir
	 * 
	 * @return String
	 */
	public String getDatabaseDir() {
		return databaseDir;
	}

	/**
	 * Set CUBRID database directory
	 * 
	 * @param databaseDir String The database path
	 */
	public void setDatabaseDir(String databaseDir) {
		this.databaseDir = databaseDir;
	}

	/**
	 * 
	 * Get CUBRID server version
	 * 
	 * @return String
	 */
	public String getServerVersion() {
		return serverVersion.getServerVersion();
	}

	/**
	 * Set CUBRID server version
	 * 
	 * @param serverVersion String The server version
	 */
	public void setServerVersion(String version) {
		if (serverVersion == null){
			serverVersion = new ServerVersion();
		}
		serverVersion.setVersion(version);
	}
	public ServerVersion getServerDetails(){
		return serverVersion;
	}

	/**
	 * 
	 * Get broker version
	 * 
	 * @return String
	 */
	public String getBrokerVersion() {
		return brokerVersion;
	}

	/**
	 * 
	 * Set broker version
	 * 
	 * @param brokerVersion String The broker version
	 */
	public void setBrokerVersion(String brokerVersion) {
		this.brokerVersion = brokerVersion;
	}

	/**
	 * 
	 * Get host monitor status
	 * 
	 * @return String[] The host monitor status
	 */
	public String[] getHostMonTabStatus() {
		return hostMonTabStatus == null ? null
				: (String[]) hostMonTabStatus.clone();
	}

	/**
	 * 
	 * Set host monitor status
	 * 
	 * @param hostMonTabStatus String[]
	 */
	public void setHostMonTabStatus(String[] hostMonTabStatus) {
		this.hostMonTabStatus = hostMonTabStatus == null ? null
				: (String[]) (hostMonTabStatus.clone());
	}

	/**
	 * 
	 * Get CUBRID Server OS information
	 * 
	 * @return String The info of OS
	 */
	public String getOsInfo() {
		return osInfo;
	}

	/**
	 * 
	 * Set CUBRID Server OS information
	 * 
	 * @param osInfo String The info of OS
	 */
	public void setOsInfo(String osInfo) {
		this.osInfo = osInfo;
	}

	/**
	 * 
	 * Get CUBRID Manager server directory
	 * 
	 * @return String
	 */
	public String getCmServerDir() {
		return cmServerDir;
	}

	/**
	 * 
	 * Set CUBRID Manager server directory
	 * 
	 * @param cmServerDir String
	 */
	public void setCmServerDir(String cmServerDir) {
		this.cmServerDir = cmServerDir;
	}

}
