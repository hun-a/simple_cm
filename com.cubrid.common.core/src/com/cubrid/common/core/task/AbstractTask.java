package com.cubrid.common.core.task;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTask implements ITask, Runnable {

	protected String taskName = "";
	protected String errorMsg = null;
	protected String warningMsg = null;
	protected boolean isDone = false;

	// data map which store a lot of data values
	protected Map<Object, Object> dataMap = new HashMap<Object, Object>();

	/**
	 * Get error message after this task execute.if it is null, this task is ok,
	 * or it has error
	 * 
	 * @return String
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * Get warning message after this task execute
	 * 
	 * @return String
	 */
	public String getWarningMsg() {
		return warningMsg;
	}

	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

	public String getTaskname() {
		return taskName;
	}

	public void setTaskname(String taskname) {
		this.taskName = taskname;
	}

	/**
	 * Store a lot of values
	 * 
	 * @param key String
	 * @param value String
	 */
	public void putData(Object key, Object value) {
		dataMap.put(key, value);
	}

	/**
	 * Get stored value by key
	 * 
	 * @param key Object
	 * @return Object
	 */
	public Object getData(Object key) {
		return dataMap.get(key);
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		execute();
		isDone = true;
	}

	/**
	 * @see ITask#isDone()
	 * @return boolean
	 */
	public boolean isDone() {
		return this.isDone;
	}

	/**
	 * get the response status code
	 * 
	 * @return response status code
	 */
	public int getStatusCode() {
		return 0;
	}
}
