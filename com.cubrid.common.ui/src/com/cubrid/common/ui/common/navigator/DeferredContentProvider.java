package com.cubrid.common.ui.common.navigator;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.common.ui.spi.model.CubridServer;
import com.cubrid.common.ui.spi.model.ICubridNode;

public class DeferredContentProvider implements ITreeContentProvider {

	private CubridDeferredTreeContentManager contentManager = null;

	/**
	 * Return the elements to display in the tree viewer when its input is set
	 * to the given element.
	 * 
	 * @param inputElement the input element
	 * @return the array of elements to display in the viewer
	 */
	@SuppressWarnings("unchecked")
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof List) {
			List<ICubridNode> list = (List<ICubridNode>) inputElement;
			ICubridNode[] nodeArr = new ICubridNode[list.size()];
			return list.toArray(nodeArr);
		}
		return new Object[]{};
	}

	/**
	 * Return the child elements of the given parent element.
	 * 
	 * @param element the parent element
	 * @return an array of child elements
	 */
	public Object[] getChildren(Object element) {
		if (contentManager != null) {
			Object[] children = contentManager.getChildren(element);
			if (children != null) {
				return children;
			}
		}
		return new Object[]{};
	}

	/**
	 * Return whether the given element has children.
	 * 
	 * 
	 * @param element the element
	 * @return <code>true</code> if the given element has children, and
	 *         <code>false</code> if it has no children
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof CubridServer) {
			CubridServer server = (CubridServer) element;
			if (server.isConnected()) {
				return true;
			}
		} else if (element instanceof CubridDatabase) {
			CubridDatabase database = (CubridDatabase) element;
			return database.isLogined();
		} else if (element instanceof ICubridNode) {
			ICubridNode node = (ICubridNode) element;
			return node.isContainer();
		}
		return false;
	}

	/**
	 * Return the parent for the given element, or <code>null</code>
	 * 
	 * @param element the element
	 * @return the parent element, or <code>null</code> if it has none or if the
	 *         parent cannot be computed
	 */
	public Object getParent(Object element) {
		if (element instanceof ICubridNode) {
			ICubridNode node = (ICubridNode) element;
			return node.getParent();
		}
		return null;
	}

	/**
	 * Disposes of this content provider. This is called by the viewer when it
	 * is disposed.
	 */
	public void dispose() {
		// ignore
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
		if (viewer instanceof TreeViewer) {
			contentManager = new CubridDeferredTreeContentManager((TreeViewer)viewer);
		}
	}

	public CubridDeferredTreeContentManager getDeferredTreeContentManager() {
		return contentManager;
	}
}
