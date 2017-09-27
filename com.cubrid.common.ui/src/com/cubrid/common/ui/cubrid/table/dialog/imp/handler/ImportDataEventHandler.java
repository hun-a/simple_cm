package com.cubrid.common.ui.cubrid.table.dialog.imp.handler;

import com.cubrid.common.ui.cubrid.table.dialog.imp.event.ImportDataEvent;

public interface ImportDataEventHandler {
	
	/**
	 * Add event to handle list.
	 * 
	 * @param event ExportDataEvent
	 */
	public void handleEvent(final ImportDataEvent event);

	/**
	 * Dispose and release resources
	 */
	public void dispose();
}
