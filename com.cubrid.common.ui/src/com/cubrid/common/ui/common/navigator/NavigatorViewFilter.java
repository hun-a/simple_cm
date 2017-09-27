package com.cubrid.common.ui.common.navigator;

import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import com.cubrid.common.ui.spi.model.ICubridNode;
import com.cubrid.common.ui.spi.model.NodeType;

public class NavigatorViewFilter extends ViewerFilter {

	/**
	 * Returns whether the given element makes it through this filter.
	 * 
	 * @param viewer the viewer
	 * @param parentElement the parent element
	 * @param element the element
	 * @return <code>true</code> if element is included in the filtered set, and
	 *         <code>false</code> if excluded
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		ICubridNode parent = null;
		if (parentElement instanceof ICubridNode) {
			parent = (ICubridNode) parentElement;
		}
		ICubridNode filteredNode = null;
		if (element instanceof ICubridNode) {
			filteredNode = (ICubridNode) element;
		}
		if (filteredNode == null) {
			return true;
		}
		return select(viewer, parent, filteredNode);
	}

	/**
	 * Returns whether the given node makes it through this filter.
	 * 
	 * @param viewer the viewer
	 * @param parent the parent node
	 * @param node the node
	 * @return <code>true</code> if node is included in the filtered set, and
	 *         <code>false</code> if excluded
	 */
	protected boolean select(Viewer viewer, ICubridNode parent, ICubridNode node) {
		if (NodeFilterManager.getInstance().isExistIdFilter(node.getId())) {
			return false;
		} else if (parent == null && node.getType().equals(NodeType.GROUP)) {
			int matched = 0;
			List<ICubridNode> nodes = node.getChildren();
			if (nodes == null || nodes.size() == 0) {
				return !NodeFilterManager.getInstance().isMatch(node.getLabel());
			}
			for (ICubridNode cnode : nodes) {
				if (cnode == null) {
					continue;
				}
				if (!NodeFilterManager.getInstance().isMatch(cnode.getLabel())
						&& cnode instanceof ICubridNode
						&& (cnode.getType().equals(NodeType.DATABASE)
								|| cnode.getType().equals(NodeType.SERVER))) {
					matched++;
					break;
				}
			}

			if (matched == 0) {
				return false;
			}
		} else if (parent != null && parent.getType().equals(NodeType.GROUP)) {
			return !NodeFilterManager.getInstance().isMatch(node.getLabel());
		} else if (parent == null && node.getType().equals(NodeType.MONITOR_DASHBOARD)
				|| parent == null && node.getType().equals(NodeType.MONITOR_STATISTIC_PAGE)) {
			return !NodeFilterManager.getInstance().isMatch(node.getLabel());
		} else if (NodeFilterManager.getInstance().isMatch(node.getLabel())
				&& node instanceof ICubridNode
				&& (parent == null && node.getType().equals(NodeType.DATABASE) 
				|| parent == null && node.getType().equals(NodeType.SERVER))) {
			return false;
		}

		return true;
	}
}
