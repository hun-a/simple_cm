package com.cubrid.common.ui.common.action;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreeViewer;

import com.cubrid.common.ui.spi.model.ICubridNode;

public class CollapseAllAction extends Action {
	public static final String ID = CollapseAllAction.class.getName();
	private TreeViewer treeViewer;

	public CollapseAllAction(String text, ImageDescriptor image, TreeViewer treeViewer) {
		super(text);
		setId(ID);
		this.setToolTipText(text);
		this.setImageDescriptor(image);
		this.treeViewer = treeViewer;
	}

	public void setTargetTreeViewer(TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
	}

	public void run() {
		if (treeViewer != null) {
			// You can use TreeViewer.collapseAll(), but this will influence the search operation.
			// After call it, then search, it will not be able to expand the tree.
			// Hence, you should use the below method.
			Object inputObj = treeViewer.getInput();
			if (!(inputObj instanceof List<?>)) {
				return;
			}
			List<?> list = (List<?>) inputObj;
			for (Object obj : list) {
				if (!(obj instanceof ICubridNode)) {
					continue;
				}
				treeViewer.collapseToLevel(obj, 1);
			}
		}
	}
}
