package com.cubrid.common.ui.cubrid.table.action;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.ui.cubrid.table.control.SchemaInfoEditorPart;
import com.cubrid.common.ui.spi.action.SelectionAction;
import com.cubrid.common.ui.spi.model.ICubridNode;
import com.cubrid.common.ui.spi.model.NodeType;
import com.cubrid.common.ui.spi.util.ActionSupportUtil;

public class ShowSchemaEditorAction extends
SelectionAction {
	private static final Logger LOGGER = LogUtil.getLogger(ShowSchemaEditorAction.class);
	public static final String ID = ShowSchemaEditorAction.class.getName();

	/**
	 * The constructor
	 * 
	 * @param shell
	 * @param text
	 */
	public ShowSchemaEditorAction(Shell shell, String text) {
		this(shell, text, null);
	}

	/**
	 * The constructor
	 * 
	 * @param shell
	 * @param text
	 * @param icon
	 */
	public ShowSchemaEditorAction(Shell shell, String text, ImageDescriptor icon) {
		this(shell, null, text, icon);
	}

	/**
	 * The constructor
	 * 
	 * @param shell
	 * @param provider
	 * @param text
	 * @param icon
	 */
	protected ShowSchemaEditorAction(Shell shell, ISelectionProvider provider,
			String text, ImageDescriptor icon) {
		super(shell, provider, text, icon);
		this.setId(ID);
		this.setToolTipText(text);
	}

	/**
	 * Open the schema editor
	 * 
	 */
	public void run() {
		Object[] obj = this.getSelectedObj();
		if (!isSupported(obj)) {
			setEnabled(false);
			return;
		}

		performShowSchema((ICubridNode) obj[0]);

	}

	public void run(ICubridNode node) {
		performShowSchema(node);
	}

	private void performShowSchema(ICubridNode node) {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window == null) {
			return;
		}
		try {
			window.getActivePage().openEditor(node, SchemaInfoEditorPart.ID);
		} catch (PartInitException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Sets this action support to select multi-object
	 * 
	 * @see org.eclipse.jface.action.IAction.ISelectionAction
	 * @return boolean
	 */
	public boolean allowMultiSelections() {
		return false;
	}

	/**
	 * Sets this action support this object
	 * 
	 * @see org.eclipse.jface.action.IAction.ISelectionAction
	 * @param obj Object
	 * @return boolean
	 */
	public boolean isSupported(Object obj) {
		return ActionSupportUtil.isSupportSingleSelection(obj, new String[]{
				NodeType.USER_TABLE, NodeType.USER_VIEW,
				NodeType.USER_PARTITIONED_TABLE_FOLDER,
				NodeType.USER_PARTITIONED_TABLE, NodeType.SYSTEM_TABLE,
				NodeType.SYSTEM_VIEW });
	}
}
