package com.cubrid.common.ui.common.action;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;

import com.cubrid.common.ui.common.dialog.GroupEditDialog;
import com.cubrid.common.ui.common.navigator.CubridNavigatorView;
import com.cubrid.common.ui.spi.ICubridGroupNodeManager;
import com.cubrid.common.ui.spi.action.SelectionAction;
import com.cubrid.common.ui.spi.model.CubridGroupNode;

public class GroupPropertyAction extends
SelectionAction {

	public static final String ID = GroupPropertyAction.class.getName();

	private String navigatorViewId = "";

	public GroupPropertyAction(Shell shell, String text,
			ImageDescriptor enabledIcon, ImageDescriptor disabledIcon) {
		this(shell, null, text, enabledIcon, disabledIcon);
	}

	public GroupPropertyAction(Shell shell, ISelectionProvider provider,
			String text, ImageDescriptor enabledIcon,
			ImageDescriptor disabledIcon) {
		super(shell, provider, text, enabledIcon);
		this.setId(ID);
		this.setToolTipText(text);
		this.setDisabledImageDescriptor(disabledIcon);
	}

	public void setNavigatorViewId(String id) {
		navigatorViewId = id;
	}

	/**
	 * Filter
	 */
	public void run() {
		Object[] selected = this.getSelectedObj();
		if (selected == null || selected.length == 0) {
			return;
		}
		if (!(selected[0] instanceof CubridGroupNode)) {
			return;
		}
		CubridNavigatorView cubridNavigatorView = CubridNavigatorView.getNavigatorView(navigatorViewId);
		if (cubridNavigatorView == null) {
			return;
		}
		TreeViewer tv = cubridNavigatorView.getViewer();
		CubridGroupNode group = (CubridGroupNode) selected[0];
		GroupEditDialog dialog = new GroupEditDialog(shell,
				cubridNavigatorView.getGroupNodeManager(),
				cubridNavigatorView.getGroupNodeManager().getAllGroupNodes(),
				group);
		if (dialog.open() == Dialog.OK) {
			Object[] objs = tv.getExpandedElements();
			cubridNavigatorView.setShowGroup(true);
			if (objs != null) {
				tv.setExpandedElements(objs);
			}
		}
	}

	/**
	 * Return whether allow multi-selection
	 *
	 * @return boolean
	 */
	public boolean allowMultiSelections() {
		return false;
	}

	/**
	 * Support CubridGroupNode except Default Group Node
	 *
	 * @param obj Object that selected.
	 * @return support or not.
	 */
	public boolean isSupported(Object obj) {
		boolean ins = obj instanceof CubridGroupNode;
		if (!ins) {
			return false;
		}
		CubridGroupNode group = (CubridGroupNode) obj;

		return !group.getId().equals(
				ICubridGroupNodeManager.DEFAULT_GROUP_NODE.getId());
	}

}
