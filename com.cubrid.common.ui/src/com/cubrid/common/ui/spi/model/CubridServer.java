package com.cubrid.common.ui.spi.model;

import com.cubrid.cubridmanager.core.common.model.ServerInfo;

public class CubridServer extends
		DefaultCubridNode {

	private String connectedIconPath;

	private boolean autoSavePassword;

	/**
	 * Auto save password.
	 * 
	 * @return save or not save.
	 */
	public boolean isAutoSavePassword() {
		return autoSavePassword;
	}

	/**
	 * Set save or not save password.
	 * 
	 * @param autoSavePassword boolean.
	 */
	public void setAutoSavePassword(boolean autoSavePassword) {
		this.autoSavePassword = autoSavePassword;
	}

	/**
	 * The constructor
	 * 
	 * @param id
	 * @param label
	 * @param disconnectedIconPath
	 * @param connectedIconPath
	 */
	public CubridServer(String id, String label, String disconnectedIconPath,
			String connectedIconPath) {
		super(id, label, disconnectedIconPath);
		this.connectedIconPath = connectedIconPath;
		setType(NodeType.SERVER);
		setRoot(true);
		setServer(this);
		setContainer(true);
	}

	/**
	 * 
	 * Get CUBRID Manager server information
	 * 
	 * @return the ServerInfo object
	 */
	public ServerInfo getServerInfo() {
		if (modelObj instanceof ServerInfo) {
			return (ServerInfo) modelObj;
		}
		return null;
	}

	/**
	 * 
	 * Set CUBRID Manager server information
	 * 
	 * @param serverInfo the ServerInfo object
	 */
	public void setServerInfo(ServerInfo serverInfo) {
		this.setModelObj(serverInfo);
	}

	/**
	 * 
	 * Return whether server is connected
	 * 
	 * @return <code>true</code> if it is connected;<code>false</code> otherwise
	 */
	public boolean isConnected() {
		return getServerInfo() == null ? false : getServerInfo().isConnected();
	}

	/**
	 * 
	 * Get logined user name
	 * 
	 * @return the user name
	 */
	public String getUserName() {
		return getServerInfo() == null ? null : getServerInfo().getUserName();
	}

	/**
	 * 
	 * Get logined password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return getServerInfo() == null ? null
				: getServerInfo().getUserPassword();
	}

	/**
	 * 
	 * Get server name
	 * 
	 * @return the server name
	 */
	public String getServerName() {
		return getServerInfo() == null ? null : getServerInfo().getServerName();
	}

	/**
	 * get the JDBC version info(format:CUBRID-JDBC-8.2.0.1147)
	 * 
	 * @return the JDBC driver version(format:CUBRID-JDBC-8.2.0.1147)
	 * @see ServerInfo#getJdbcDriverVersion()
	 */
	public String getJdbcDriverVersion() {
		return getServerInfo() == null ? null
				: getServerInfo().getJdbcDriverVersion();
	}

	/**
	 * 
	 * Get host address
	 * 
	 * @return the host address
	 */
	public String getHostAddress() {
		return getServerInfo() == null ? null
				: getServerInfo().getHostAddress();
	}

	/**
	 * 
	 * Get host monitor port
	 * 
	 * @return the monitor port
	 */
	public String getMonPort() {
		return getServerInfo() == null ? null
				: String.valueOf(getServerInfo().getHostMonPort());
	}

	/**
	 * 
	 * Get host JS port
	 * 
	 * @return the js port
	 */
	public String getJSPort() {
		return getServerInfo() == null ? null
				: String.valueOf(getServerInfo().getHostJSPort());
	}

	/**
	 * 
	 * Get server connected status icon path
	 * 
	 * @return the icon path
	 */
	public String getConnectedIconPath() {
		return connectedIconPath;
	}

	/**
	 * 
	 * Set server connected status icon path
	 * 
	 * @param connectedIconPath the icon path
	 */
	public void setConnectedIconPath(String connectedIconPath) {
		this.connectedIconPath = connectedIconPath;
	}

	/**
	 * 
	 * Get server disconnected status icon path
	 * 
	 * @return the icon path
	 */
	public String getDisConnectedIconPath() {
		return this.getIconPath();
	}

	/**
	 * 
	 * Set server disconnected status icon path
	 * 
	 * @param disConnectedIconPath the icon path
	 */
	public void setDisConnectedIconPath(String disConnectedIconPath) {
		this.setIconPath(disConnectedIconPath);
	}

	/**
	 * Return whether the current object is equal the obj
	 * 
	 * @param obj the object
	 * @return <code>true</code> if they are equal;<code>false</code> otherwise
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof CubridServer)) {
			return false;
		}
		return super.equals(obj);
	}

	/**
	 * Return the hash code value
	 * 
	 * @return the hash code value
	 */
	public int hashCode() {
		return this.getId().hashCode();
	}

	public CubridServer clone() throws CloneNotSupportedException {
		CubridServer obj = (CubridServer) super.clone();;
		return obj;
	}
}
