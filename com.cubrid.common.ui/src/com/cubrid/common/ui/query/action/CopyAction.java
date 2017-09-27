package com.cubrid.common.ui.query.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import com.cubrid.common.ui.query.editor.ICopiableFromTable;
import com.cubrid.common.ui.spi.action.ActionManager;
import com.cubrid.common.ui.spi.action.FocusAction;
import com.cubrid.common.ui.spi.util.CommonUITool;

public class CopyAction extends FocusAction {

	public static final String ID = ActionFactory.COPY.getId();

	/**
	 * The constructor
	 * 
	 * @param shell
	 * @param focusProvider
	 * @param text
	 * @param icon
	 */
	public CopyAction(Shell shell, Control focusProvider, String text,
			ImageDescriptor icon) {
		super(shell, focusProvider, text, icon);
		this.setId(ID);
		this.setActionDefinitionId("org.eclipse.ui.edit.copy");
	}

	/**
	 * The constructor
	 * 
	 * @param shell
	 * @param text
	 * @param icon
	 */
	public CopyAction(Shell shell, String text, ImageDescriptor icon) {
		this(shell, null, text, icon);
	}

	/**
	 * Notifies that the focus gained event
	 * 
	 * @param event an event containing information about the focus change
	 */
	public void focusGained(FocusEvent event) {
		setEnabled(false);
		if (event.getSource() instanceof StyledText) {
			StyledText stext = (StyledText) event.getSource();
			boolean isEnabled = stext != null
					&& stext.getSelectionText() != null
					&& stext.getSelectionText().length() > 0;
					setEnabled(isEnabled);
		} else if (event.getSource() instanceof Table) {
			Table table = (Table) event.getSource();
			boolean isEnabled = table.getSelection().length > 0;
			setEnabled(isEnabled);
		}
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		TextTransfer textTransfer = TextTransfer.getInstance();
		Clipboard clipboard = CommonUITool.getClipboard();
		Control control = getFocusProvider();
		if (control instanceof StyledText) {
			StyledText stext = (StyledText) control;
			String data = stext.getSelectionText();
			if (data != null && !data.equals("")) {
				clipboard.setContents(new Object[]{data },
						new Transfer[]{textTransfer });
				IAction pasteAction = ActionManager.getInstance().getAction(
						PasteAction.ID);
				FocusAction.changeActionStatus(pasteAction, stext);
			}
		} else {
			/*Copy from the active editor*/
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (window != null) {
				IEditorPart editor = window.getActivePage().getActiveEditor();
				if (editor != null && editor instanceof ICopiableFromTable) {
					ICopiableFromTable copyAbleEditor = (ICopiableFromTable) editor;
					copyAbleEditor.copySelectedItems();
				}
			}
		}
	}
}