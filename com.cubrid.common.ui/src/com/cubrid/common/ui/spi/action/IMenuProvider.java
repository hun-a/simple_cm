package com.cubrid.common.ui.spi.action;

import org.eclipse.jface.action.IMenuManager;

import com.cubrid.common.ui.query.control.DatabaseNavigatorMenu;
import com.cubrid.common.ui.spi.model.ICubridNode;

public interface IMenuProvider {
	/**
	 * Build the context menu and menubar menu according to the selected cubrid
	 * node
	 * 
	 * @param manager the parent menu manager
	 * @param node the ICubridNode object
	 */
	public void buildMenu(IMenuManager manager, ICubridNode node);
	
	public DatabaseNavigatorMenu getDatabaseNavigatorMenu();
}
