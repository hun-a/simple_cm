package com.cubrid.common.ui.common.sqlrunner.dialog;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;

import com.cubrid.common.ui.perspective.PerspectiveManager;

public class RunSQLFileDialogDNDController {
	private static Map<String, TreeViewer> perspectiveTreeviewerMap = new HashMap<String, TreeViewer>();
	private RunSQLFileDialog dialog;

	public RunSQLFileDialogDNDController (RunSQLFileDialog dialog) {
		this.dialog = dialog;
	}

	/**
	 * Register the drag source
	 *
	 * @param treeViewer TreeViewer
	 */
	public static void registerDragSource(String perspectiveId, TreeViewer treeViewer) {
		synchronized (RunSQLFileDialogDNDController.class) {
			if (perspectiveId != null && treeViewer != null) {
				perspectiveTreeviewerMap.put(perspectiveId, treeViewer);
			}
		}
	}

	/**
	 * register drag source and DB tree target
	 */
	public void registerDropTarget() {
		synchronized (this) {
			DropTarget target = new DropTarget(dialog.getDatabaseTableComp(),
					DND.DROP_MOVE);
			target.setTransfer(new Transfer[]{TextTransfer.getInstance() });
			target.addDropListener(new DropTargetAdapter() {
				/**
				 * @see org.eclipse.swt.dnd.DropTargetAdapter#drop(org.eclipse.swt.dnd.DropTargetEvent)
				 * @param event the information associated with the drop event
				 */
				public void drop(DropTargetEvent event) {
					addDatabase();
				}
			});
		}
	}

	/**
	 * add database
	 */
	public void addDatabase() {
		synchronized (this) {
			TreeViewer treeViewer = perspectiveTreeviewerMap.get(PerspectiveManager.getInstance().getCurrentPerspectiveId());
			if (treeViewer == null) {
				return;
			}
			ISelection selection = treeViewer.getSelection();
			if (!(selection instanceof TreeSelection)) {
				return;
			}

			TreeSelection ts = (TreeSelection) selection;
			Object[] objs = ts.toArray();
			dialog.addDatabase(objs);
		}
	}
}
