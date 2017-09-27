package com.cubrid.common.ui.cubrid.table.dialog.imp.progress;

import com.cubrid.common.ui.cubrid.table.dialog.imp.event.ImportDataEvent;

public interface IImportDataMonitor {

	/**
	 * Called when run sql is finished.
	 * 
	 */
	void finished();

	/**
	 * Add event to handle list
	 * 
	 * @param event MigrationEvent
	 */
	void addEvent(ImportDataEvent event);

	/**
	 * return has error
	 * 
	 * @return
	 */
	boolean hasError();

}
