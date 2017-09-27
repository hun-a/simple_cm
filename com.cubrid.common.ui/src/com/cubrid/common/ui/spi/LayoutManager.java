package com.cubrid.common.ui.spi;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

import com.cubrid.common.ui.spi.contribution.StatusLineContrItem;
import com.cubrid.common.ui.spi.contribution.TitleLineContrItem;
import com.cubrid.common.ui.spi.contribution.WorkbenchContrItem;
import com.cubrid.common.ui.spi.event.CubridNodeChangedEvent;
import com.cubrid.common.ui.spi.event.ICubridNodeChangedListener;
import com.cubrid.common.ui.spi.model.ICubridNode;

public final class LayoutManager implements
ISelectionChangedListener,
IDoubleClickListener,
ICubridNodeChangedListener {
	private static LayoutManager manager = new LayoutManager();
	private ISelectionProvider provider = null;
	private WorkbenchContrItem workbenchContrItem = new WorkbenchContrItem();
	private StatusLineContrItem statusLineContrItem = new StatusLineContrItem();
	private TitleLineContrItem titleLineContrItem = new TitleLineContrItem();
	private boolean isUseClickOnce = false;

	/**
	 * The constructor
	 */
	private LayoutManager() {
	}

	/**
	 * 
	 * Get the only LayoutManager instance
	 * 
	 * @return the LayoutManager instance
	 */
	public static LayoutManager getInstance() {
		return manager;
	}

	/**
	 * 
	 * Set current selected node
	 * 
	 * @param node the ICubridNode object
	 */
	public void setCurrentSelectedNode(ICubridNode node) {
		if (this.provider != null) {
			this.provider.setSelection(new StructuredSelection(node));
		}
	}

	/**
	 * 
	 * Get current selected CUBRID node
	 * 
	 * @return the selected ICubridNode object
	 */
	public ICubridNode getCurrentSelectedNode() {
		if (this.provider == null) {
			return null;
		}
		ISelection selection = this.provider.getSelection();
		if (selection == null || selection.isEmpty()) {
			return null;
		}
		Object obj = ((IStructuredSelection) selection).getFirstElement();
		if (!(obj instanceof ICubridNode)) {
			return null;
		}
		return (ICubridNode) obj;
	}

	/**
	 * 
	 * Get SelectionProvider
	 * 
	 * @return the ISelectionProvider object
	 */
	public ISelectionProvider getSelectionProvider() {
		return this.provider;
	}

	/**
	 * 
	 * Change selection provider
	 * 
	 * @param provider the ISelectionProvider object
	 */
	public void changeSelectionProvider(ISelectionProvider provider) {
		if (provider != null) {
			if (this.provider != null) {
				this.provider.removeSelectionChangedListener(this);
			}
			this.provider = provider;
			this.provider.addSelectionChangedListener(this);
			statusLineContrItem.changeStuatusLineForNavigator(this.provider.getSelection());
			titleLineContrItem.changeTitleForNavigator(this.provider.getSelection());
		}
	}

	/**
	 * 
	 * Fire selection change event
	 * 
	 * @param selection the ISelection object
	 */
	public void fireSelectionChanged(ISelection selection) {
		SelectionChangedEvent event = new SelectionChangedEvent(
				getSelectionProvider(), selection);
		selectionChanged(event);
	}

	/**
	 * Notifies that the selection has changed.
	 * 
	 * @param event the SelectionChangedEvent object
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		if (this.provider == null) {
			return;
		}
		titleLineContrItem.changeTitleForNavigator(event.getSelection());
		statusLineContrItem.changeStuatusLineForNavigator(event.getSelection());
		workbenchContrItem.processSelectionChanged(event);
	}

	/**
	 * Notifies of a double click.
	 * 
	 * @param event event object describing the double-click
	 */
	public void doubleClick(DoubleClickEvent event) {
		workbenchContrItem.processDoubleClickNavigatorEvent(event);
	}

	/**
	 * When refresh container node,check the viewpart and the editorPart of it's
	 * children,if the children are deleted,close it,or refresh it
	 * 
	 * @param event the CubridNodeChangedEvent object
	 */
	public void nodeChanged(CubridNodeChangedEvent event) {
		workbenchContrItem.processCubridNodeChangeEvent(event);
	}

	public WorkbenchContrItem getWorkbenchContrItem() {
		return workbenchContrItem;
	}

	public void setWorkbenchContrItem(WorkbenchContrItem workbenchContrItem) {
		this.workbenchContrItem = workbenchContrItem;
	}

	public StatusLineContrItem getStatusLineContrItem() {
		return statusLineContrItem;
	}

	public void setStatusLineContrItem(StatusLineContrItem statusLineContrItem) {
		this.statusLineContrItem = statusLineContrItem;
	}

	public TitleLineContrItem getTitleLineContrItem() {
		return titleLineContrItem;
	}

	public void setTitleLineContrItem(TitleLineContrItem titleLineContrItem) {
		this.titleLineContrItem = titleLineContrItem;
	}

	/**
	 * 
	 * Get whether use click once operaiton
	 * 
	 * @return <code>true</code> if use click once operation;<code>false</code>
	 *         otherwise
	 */
	public boolean isUseClickOnce() {
		return false;
	}
}
