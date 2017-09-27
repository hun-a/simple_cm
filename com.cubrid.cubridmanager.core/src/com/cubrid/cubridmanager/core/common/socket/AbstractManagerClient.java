package com.cubrid.cubridmanager.core.common.socket;

public abstract class AbstractManagerClient implements IManagerClient {

	/**
	 * return result
	 */
	protected TreeNode response;
	protected String responsedMsg;
	protected String errorMsg;
	protected String warningMsg;
	protected int statusCode;

	protected String hostAddress;
	protected int port;
	protected String userName;

	protected String requestCharsetName = "UTF-8";
	protected String responseCharsetName = "UTF-8";
	
	protected boolean canConnect = true;

	/**
	 * 
	 * Return parsed response node
	 * 
	 * @return TreeNode
	 */
	public TreeNode getResponse() {
		return response;
	}

	/**
	 * Return the original response message
	 * 
	 * @return String
	 */
	public String getResponsedMsg() {
		return responsedMsg;
	}

	/**
	 * 
	 * Get error message
	 * 
	 * @return String The error message
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * 
	 * Get warning message
	 * 
	 * @return String The warning message
	 */
	public String getWarningMsg() {
		return warningMsg;
	}

	/**
	 * Get the host address of this socket
	 * 
	 * @return String The host address
	 */
	public String getHostAddress() {
		return hostAddress;
	}

	/**
	 * Set the host address of this socket
	 * 
	 * @param hostAddress
	 *            String The host address
	 */
	public void setHostAddress(String hostAddress) {
		tearDownConnection();
		this.hostAddress = hostAddress;
	}

	/**
	 * Set the port of this socket
	 * 
	 * @param port
	 *            int The prot of this socket
	 */
	public void setPort(int port) {
		tearDownConnection();
		this.port = port;
	}

	/**
	 * Get the port of this socket
	 * 
	 * @return int The port of this socket
	 */
	public int getPort() {
		return port;
	}

	public void stopRead() {
		// do thing
	}

	public void setHeartbeat(int time) {
		// do thing
	}

	public void setUsingSpecialDelimiter(boolean usingSpecialDelimiter) {
		// do thing
	}

	public Thread getHeartbeatThread() {
		// do thing
		return null;
	}

	public void stopHeartbeatThread() {
		// do thing
	}

	public int getStatusCode() {
		return statusCode;
	}

	public boolean canConnect() {
		return canConnect;
	}
}
