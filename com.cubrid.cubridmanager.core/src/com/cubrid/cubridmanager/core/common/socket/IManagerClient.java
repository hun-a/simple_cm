package com.cubrid.cubridmanager.core.common.socket;

public interface IManagerClient {

	/**
	 * Send request to CUBRID Manager server
	 * 
	 * @param message
	 *            String
	 */
	void sendRequest(String message);

	/**
	 * Stop reading response message.
	 */
	@Deprecated
	void stopRead();

	/**
	 * Set the heart beat time in milliseconds, this method takes effect only at
	 * the first time. this method is to monitor whether the socket connection
	 * status is OK.
	 * 
	 * @param time
	 *            The heart time in milliseconds
	 */
	@Deprecated
	void setHeartbeat(final int time);

	/**
	 * Tear down the socket connection
	 */
	void tearDownConnection();

	/**
	 * Return parsed response node
	 * 
	 * @return TreeNode
	 */
	TreeNode getResponse();

	/**
	 * Return the original response message
	 * 
	 * @return String
	 */
	String getResponsedMsg();

	/**
	 * Set whether using special delimiter.
	 * 
	 * @param usingSpecialDelimiter
	 *            Whether using the special delimiter
	 */
	@Deprecated
	void setUsingSpecialDelimiter(boolean usingSpecialDelimiter);

	/**
	 * Get error message
	 * 
	 * @return The error message
	 */
	String getErrorMsg();

	/**
	 * Get warning message
	 * 
	 * @return The warning message
	 */
	String getWarningMsg();

	/**
	 * Get the host address of this socket
	 * 
	 * @return The host address
	 */
	String getHostAddress();

	/**
	 * Set the host address of this socket
	 * 
	 * @param hostAddress
	 *            The host address
	 */
	void setHostAddress(String hostAddress);

	/**
	 * Get the port of this socket
	 * 
	 * @return The port of this socket
	 */
	int getPort();

	/**
	 * Set the port of this socket
	 * 
	 * @param port
	 *            The prot of this socket
	 */
	void setPort(int port);

	/**
	 * Get heart beat thread.
	 * 
	 * @return The heart beat thread
	 */
	@Deprecated
	Thread getHeartbeatThread();

	/**
	 * Stop heart beat thread.
	 * 
	 */
	@Deprecated
	void stopHeartbeatThread();

	/**
	 * Set time out
	 * 
	 * @param timeout
	 *            int
	 */
	void setTimeout(int timeout);

	/**
	 * get the response status code
	 * 
	 * @return response status code
	 */
	int getStatusCode();

	/**
	 * get whether the host:port can be connected.
	 * 
	 * @return
	 */
	boolean canConnect();
}
