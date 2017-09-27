package com.cubrid.common.core.task;

public interface ITask {

	/**
	 * Send request to Server
	 */
	public void execute();

	public String getErrorMsg();
	public void setErrorMsg(String errorMsg);

	public String getWarningMsg();
	public void setWarningMsg(String waringMsg);

	public String getTaskname();
	public void setTaskname(String taskName);

	/**
	 * Cancel this operation
	 */
	public void cancel();

	/**
	 * End this task and free resource
	 */
	public void finish();

	/**
	 * Get the flag of is cancel of this task
	 * 
	 * @return boolean
	 */
	public boolean isCancel();

	/**
	 * Whether this task is success
	 * 
	 * @return boolean
	 */
	public boolean isSuccess();

	/**
	 * Whether this task done
	 * 
	 * @return boolean
	 */
	public boolean isDone();

	/**
	 * Store a lot of values
	 * 
	 * @param key Object
	 * @param value Object
	 */
	public void putData(Object key, Object value);

	/**
	 * Get stored value by key
	 * 
	 * @param key Object
	 * @return Object
	 */
	public Object getData(Object key);

	/**
	 * get the response status code
	 * 
	 * @return response status code
	 */
	int getStatusCode();
}
