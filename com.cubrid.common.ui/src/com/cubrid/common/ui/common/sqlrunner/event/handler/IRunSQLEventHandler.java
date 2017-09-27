package com.cubrid.common.ui.common.sqlrunner.event.handler;

import com.cubrid.common.ui.common.sqlrunner.event.RunSQLEvent;

public interface IRunSQLEventHandler {
	/**
	 * Add event to handle list.
	 * 
	 * @param event MigrationEvent
	 */
	public void handleEvent(final RunSQLEvent event);

	/**
	 * Dispose and release resources
	 */
	public void dispose();
	
	/**
	 * write excel and close it
	 */
	public void writeExcel();
	
	/**
	 * has error SQL data
	 */
	public boolean hasErrData();
	
	/**
	 * set handler stop
	 * @param stop
	 */
	public void setStop(boolean stop);
}
