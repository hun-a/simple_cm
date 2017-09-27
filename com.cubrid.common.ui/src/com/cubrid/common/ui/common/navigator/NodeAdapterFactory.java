package com.cubrid.common.ui.common.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.progress.IDeferredWorkbenchAdapter;
import org.eclipse.ui.progress.IElementCollector;

import com.cubrid.common.ui.common.Messages;
import com.cubrid.common.ui.spi.model.ICubridNode;

public class NodeAdapterFactory implements
		IAdapterFactory {
	/**
	 * 
	 * CUBRID node adaptor
	 * 
	 * @author pangqiren 2009-3-2
	 */

	private static class CubridNodeAdapter implements
	IDeferredWorkbenchAdapter {

		/**
		 * Called by a job run in a separate thread to fetch the children of
		 * this adapter. The adapter should in return notify of new children via
		 * the collector. This is generally used when a content provider is
		 * getting elements.
		 * 
		 * @param object the object to fetch the children for
		 * @param collector the collector to notify about new children. Should
		 *        not be <code>null</code>.
		 * @param monitor a progress monitor that will never be
		 *        <code>null<code> to
		 *                   support reporting and cancellation.
		 */
		public void fetchDeferredChildren(Object object,
				IElementCollector collector, IProgressMonitor monitor) {
			if (object instanceof ICubridNode) {
				ICubridNode node = (ICubridNode) object;
				monitor.beginTask(Messages.bind(Messages.msgLoadingChildren,
						node.getLabel()), IProgressMonitor.UNKNOWN);
				ICubridNode[] nodeArr = node.getChildren(monitor);
				if (monitor.isCanceled()) {
					return;
				}
				if (nodeArr != null && nodeArr.length > 0) {
					collector.add(nodeArr, monitor);
				}
				collector.done();
			}
			monitor.done();
		}

		/**
		 * Returns the children of this object. When this object is displayed in
		 * a tree, the returned objects will be this element's children. Returns
		 * an empty array if this object has no children.
		 * 
		 * @param object The object to get the children for.
		 * @return Object[]
		 */
		public Object[] getChildren(Object object) {
			return new Object[] {};
		}

		/**
		 * Returns an image descriptor to be used for displaying an object in
		 * the workbench. Returns <code>null</code> if there is no appropriate
		 * image.
		 * 
		 * @param object The object to get an image descriptor for.
		 * @return ImageDescriptor
		 */
		public ImageDescriptor getImageDescriptor(Object object) {
			return null;
		}

		/**
		 * Returns the label text for this element.
		 * 
		 * @param obj The object to get a label for.
		 * @return String
		 */
		public String getLabel(Object obj) {
			if (obj instanceof ICubridNode) {
				ICubridNode node = (ICubridNode) obj;
				return node.getLabel();
			}
			return null;
		}

		/**
		 * Returns the logical parent of the given object in its tree. Returns
		 * <code>null</code> if there is no parent, or if this object doesn't
		 * belong to a tree.
		 * 
		 * @param obj The object to get the parent for.
		 * @return Object
		 */
		public Object getParent(Object obj) {
			if (obj instanceof ICubridNode) {
				ICubridNode node = (ICubridNode) obj;
				return node.getParent();
			}
			return null;
		}

		/**
		 * Returns the rule used to schedule the deferred fetching of children
		 * for this adapter.
		 * 
		 * @param object the object whose children are being fetched
		 * @return the scheduling rule. May be <code>null</code>.
		 * @see org.eclipse.core.runtime.jobs.Job#setRule(ISchedulingRule)
		 */
		public ISchedulingRule getRule(final Object object) {
			return null;
		}

		/**
		 * Returns whether this adapter may have children. This is an optimized
		 * method used by content providers to allow showing the [+] expand icon
		 * without having yet fetched the children for the element.
		 * 
		 * @return <code>true</code>if the adapter may have childen, and
		 *         <code>false</code> otherwise.
		 */
		public boolean isContainer() {
			return true;
		}

	}

	private final CubridNodeAdapter cubridNodeAdapter = new CubridNodeAdapter();

	/**
	 * Get adapter object
	 * 
	 * @param object the source object
	 * @param type the adapter type
	 * 
	 * @return the adapter object
	 */
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Object object, Class type) {
		if (object instanceof ICubridNode
				&& (type == ICubridNode.class
				|| type == IDeferredWorkbenchAdapter.class || type == IWorkbenchAdapter.class)) {
			return cubridNodeAdapter;
		}
		return null;
	}

	/**
	 * Get the adapter list
	 * 
	 * @return the adapter array
	 */
	@SuppressWarnings("rawtypes")
	public Class[] getAdapterList() {
		return new Class[] {ICubridNode.class, IDeferredWorkbenchAdapter.class,
				IWorkbenchAdapter.class };
	}
}
