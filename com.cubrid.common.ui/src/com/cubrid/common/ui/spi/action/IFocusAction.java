package com.cubrid.common.ui.spi.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Control;

public interface IFocusAction extends IAction, FocusListener {

	/**
	 * 
	 * Get focus provider
	 * 
	 * @return the focus provider
	 */
	public Control getFocusProvider();

	/**
	 * 
	 * Set focus provider
	 * 
	 * @param focusProvider the focus provider
	 */
	public void setFocusProvider(Control focusProvider);
}
