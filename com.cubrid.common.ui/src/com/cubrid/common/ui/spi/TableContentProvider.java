package com.cubrid.common.ui.spi;

import static com.cubrid.common.core.util.NoOp.noOp;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TableContentProvider implements
IStructuredContentProvider {

	/**
	 * Returns the elements to display in the viewer when its input is set to
	 * the given element.
	 *
	 * @param inputElement the input element
	 * @return the array of elements to display in the viewer
	 */
	@SuppressWarnings("rawtypes")
	public Object[] getElements(Object inputElement) {
		if (!(inputElement instanceof List)) {
			return new Object[] {};
		}
		return ((List) inputElement).toArray();
	}

	/**
	 * Notifies this content provider that the given viewer's input has been
	 * switched to a different element.
	 *
	 * @param viewer the viewer
	 * @param oldInput the old input element, or <code>null</code> if the viewer
	 *        did not previously have an input
	 * @param newInput the new input element, or <code>null</code> if the viewer
	 *        does not have an input
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		noOp();
	}

	/**
	 * Disposes of this content provider. This is called by the viewer when it
	 * is disposed.
	 */
	public void dispose() {
		noOp();
	}
}
