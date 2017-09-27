package com.cubrid.common.ui.query.tuner.action;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.cubrid.common.ui.query.editor.QueryEditorPart;
import com.cubrid.common.ui.query.tuner.dialog.QueryTunerDialog;
import com.cubrid.common.ui.spi.action.FocusAction;
import com.cubrid.common.ui.spi.model.CubridDatabase;

public class QueryTunerRunAction extends
FocusAction {
	public static final String ID = QueryTunerRunAction.class.getName();

	/**
	 * The constructor
	 *
	 * @param shell
	 * @param text
	 * @param enabledIcon
	 * @param disabledIcon
	 */
	public QueryTunerRunAction(Shell shell, String text,
			ImageDescriptor enabledIcon, ImageDescriptor disabledIcon) {
		this(shell, null, text, enabledIcon, disabledIcon);
	}

	/**
	 * The constructor
	 *
	 * @param shell
	 * @param provider
	 * @param text
	 * @param enabledIcon
	 * @param disabledIcon
	 */
	public QueryTunerRunAction(Shell shell, ISelectionProvider provider,
			String text, ImageDescriptor enabledIcon,
			ImageDescriptor disabledIcon) {
		super(shell, null, text, enabledIcon);
		this.setId(ID);
		this.setToolTipText(text);
		this.setDisabledImageDescriptor(disabledIcon);
	}

	public void run() {
		Control control = getFocusProvider();
		String query = "";
		if (control instanceof StyledText) {
			StyledText stext = (StyledText) control;
			String data = stext.getSelectionText();
			if (data != null && !data.equals("")) {
				query = data;
			}
		}
		CubridDatabase database = getDatabaseWithSelection();

		if (database != null) {
			new QueryTunerDialog(getShell(), getDatabaseWithSelection(), query).open();
		}
	}

	/**
	 * get the schema information.
	 *
	 * @return schemaInfo
	 */
	private CubridDatabase getDatabaseWithSelection() { // FIXME extract to module

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (null == window) {
			return null;
		}

		IEditorPart editor = window.getActivePage().getActiveEditor();
		if (editor == null) {
			return null;
		}

		if (!(editor instanceof QueryEditorPart)) {
			return null;
		}

		QueryEditorPart queryEditorPart = (QueryEditorPart) editor;

		CubridDatabase db = queryEditorPart.getSelectedDatabase();
		if (db == null || !db.isLogined()) {
			return null;
		}

		return db;
	}
}
