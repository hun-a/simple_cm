package com.cubrid.common.ui.spi.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public abstract class SelectionAction extends
Action implements
ISelectionAction,
IShellProvider {

	protected ISelectionProvider provider;
	protected Shell shell;

	/**
	 * The constructor
	 * 
	 * @param shell
	 * @param provider
	 * @param text
	 * @param icon
	 */
	protected SelectionAction(Shell shell, ISelectionProvider provider,
			String text, ImageDescriptor icon) {
		super(text);
		if (icon != null) {
			this.setImageDescriptor(icon);
		}
		setEnabled(false);
		this.shell = shell;
		this.provider = provider;
		if (this.provider != null) {
			this.provider.addSelectionChangedListener(this);
		}
	}

	/**
	 * The constructor
	 * 
	 * @param shell
	 * @param provider
	 * @param text
	 * @param style
	 * @param icon
	 */
	protected SelectionAction(Shell shell, ISelectionProvider provider,
			String text, int style, ImageDescriptor icon) {
		super(text, style);
		if (icon != null) {
			this.setImageDescriptor(icon);
		}
		setEnabled(false);
		this.shell = shell;
		this.provider = provider;
		if (this.provider != null) {
			this.provider.addSelectionChangedListener(this);
		}
	}

	/**
	 * Return the current shell (or null if none). This return value may change
	 * over time, and should not be cached.
	 * 
	 * @return the current shell or null if none
	 */
	public Shell getShell() {
		if (shell == null) {
			shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		}
		return shell;
	}

	/**
	 * 
	 * Return the selected object array from selection provider
	 * 
	 * @return selected object array
	 */
	protected Object[] getSelectedObj() {
		if (provider != null
				&& provider.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) provider.getSelection();
			return selection.toArray();
		}
		return new Object[]{};
	}

	/**
	 * 
	 * Return selection
	 * 
	 * @return the ISelection object
	 */
	protected ISelection getSelection() {
		if (provider != null) {
			return provider.getSelection();
		}
		return null;
	}

	/**
	 * Notifies that the selection has changed.
	 * 
	 * @param event event object describing the change
	 */
	public final void selectionChanged(SelectionChangedEvent event) {
		if (!(event.getSelection() instanceof IStructuredSelection)) {
			return;
		}
		selectionChanged(event.getSelection());
	}

	/**
	 * 
	 * Get selection provider
	 * 
	 * @return selection provider
	 */
	public ISelectionProvider getSelectionProvider() {
		return this.provider;
	}

	/**
	 * 
	 * Set selection provider
	 * 
	 * @param provider the selection provider
	 */
	public void setSelectionProvider(ISelectionProvider provider) {
		if (provider != null) {
			if (this.provider != null) {
				this.provider.removeSelectionChangedListener(this);
			}
			this.provider = provider;
			this.provider.addSelectionChangedListener(this);
			selectionChanged(this.provider.getSelection());
		}
	}

	/**
	 * Handle with selection changed object to determine this action's enabled
	 * status,it is not intented to be override
	 * 
	 * @param selection the ISelection object
	 */
	protected void selectionChanged(ISelection selection) {
		if (selection == null || selection.isEmpty()) {
			setEnabled(isSupported(null));
			return;
		}
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		if (allowMultiSelections()) {
			setEnabled(true);
			for (Object object : structuredSelection.toArray()) {
				if (!isSupported(object)) {
					setEnabled(false);
					break;
				}
			}
			if (isEnabled() && structuredSelection.size() > 1
					&& !isSupported(structuredSelection.toArray())) {
				setEnabled(false);
			}
		} else {
			setEnabled(structuredSelection.size() == 1
					&& isSupported(structuredSelection.getFirstElement()));
		}
	}
}
