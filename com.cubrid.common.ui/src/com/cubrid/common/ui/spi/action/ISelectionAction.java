package com.cubrid.common.ui.spi.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;

public interface ISelectionAction extends
ISelectionChangedListener,
IAction {

	/**
	 * 
	 * Return whether this action support to select multi object,if not
	 * support,this action will be disabled
	 * 
	 * @return <code>true</code> if allow multi selection;<code>false</code>
	 *         otherwise
	 */
	public boolean allowMultiSelections();

	/**
	 * 
	 * Return whether this action support this object or object array,if not
	 * support,this action will be disabled
	 * 
	 * @param obj the Object or Object Array
	 * @return <code>true</code> if support this obj;<code>false</code>
	 *         otherwise
	 */
	public boolean isSupported(Object obj);

	/**
	 * 
	 * Get selection provider
	 * 
	 * @return selection provider
	 */
	public ISelectionProvider getSelectionProvider();

	/**
	 * 
	 * Set selection provider
	 * 
	 * @param provider the selection provider
	 */
	public void setSelectionProvider(ISelectionProvider provider);

}
