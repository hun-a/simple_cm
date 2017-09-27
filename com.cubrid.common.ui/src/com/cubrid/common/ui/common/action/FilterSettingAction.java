package com.cubrid.common.ui.common.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import com.cubrid.common.ui.common.dialog.FilterSettingDialog;
import com.cubrid.common.ui.common.navigator.NodeFilterManager;
import com.cubrid.common.ui.spi.model.ICubridNode;

public class FilterSettingAction extends Action {

	public static final String ID = FilterSettingAction.class.getName();
	private TreeViewer tv;

	public FilterSettingAction(String text, ImageDescriptor image,
			TreeViewer treeViewer) {
		super(text);
		setId(ID);
		this.setToolTipText(text);
		this.setImageDescriptor(image);
		tv = treeViewer;
	}

	public void setTv(TreeViewer tv) {
		this.tv = tv;
	}

	/**
	 * Filter
	 */
	public void run() {
		final List<ICubridNode> defaultCheckedList = new ArrayList<ICubridNode>();
		final List<ICubridNode> defaultGrayCheckedList = new ArrayList<ICubridNode>();
		BusyIndicator.showWhile(Display.getDefault(), new Runnable() {
			public void run() {
				//initial the tree id filter
				Object inputObj = tv.getInput();
				if (!(inputObj instanceof List<?>)) {
					return;
				}
				List<?> list = (List<?>) inputObj;
				Map<String, ICubridNode> nodeMap = new HashMap<String, ICubridNode>();
				for (Object obj : list) {
					if (!(obj instanceof ICubridNode)) {
						continue;
					}
					ICubridNode node = (ICubridNode) obj;
					nodeMap.put(node.getId(), node);
					makeNodeMap(node, nodeMap);
				}
				//make the default checked gray elements list
				List<String> idGrayList = NodeFilterManager.getInstance().getIdGrayFilterList();
				for (int i = 0; idGrayList != null && i < idGrayList.size(); i++) {
					String id = idGrayList.get(i);
					ICubridNode node = nodeMap.get(id);
					if (node != null) {
						defaultGrayCheckedList.add(node);
					}
				}
				//make the default checked elements list
				List<String> idList = NodeFilterManager.getInstance().getIdFilterList();
				if (idList == null || idList.isEmpty()) {
					return;
				}
				for (String id : idList) {
					ICubridNode node = nodeMap.get(id);
					if (node != null) {
						defaultCheckedList.add(node);
						defaultGrayCheckedList.remove(node);
					}
				}
			}
		});
		FilterSettingDialog dialog = new FilterSettingDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				tv, defaultCheckedList, defaultGrayCheckedList);
		if (IDialogConstants.OK_ID == dialog.open()) {
			tv.setFilters(NodeFilterManager.getInstance().getViewerFilter());
		}
	}

	/**
	 *
	 * Make node map
	 *
	 * @param parent ICubridNode
	 * @param nodeMap Map<String, ICubridNode>
	 */
	private void makeNodeMap(ICubridNode parent,
			Map<String, ICubridNode> nodeMap) {
		List<ICubridNode> nodeList = parent.getChildren();
		if (nodeList == null || nodeList.isEmpty()) {
			return;
		}
		for (ICubridNode node : nodeList) {
			nodeMap.put(node.getId(), node);
			if (node.isContainer()) {
				makeNodeMap(node, nodeMap);
			}
		}
	}
}
