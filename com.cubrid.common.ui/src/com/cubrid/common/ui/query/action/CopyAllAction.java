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

import com.cubrid.common.ui.query.editor.ICopiableFromTable;
import com.cubrid.common.ui.spi.action.ActionManager;
import com.cubrid.common.ui.spi.action.FocusAction;
import com.cubrid.common.ui.spi.util.CommonUITool;

public class CopyAllAction extends FocusAction {

	public static final String ID = CopyAllAction.class.getName();

	/**
	 * The constructor
	 * 
	 * @param shell
	 * @param focusProvider
	 * @param text
	 * @param icon
	 */
	public CopyAllAction(Shell shell, Control focusProvider, String text,
			ImageDescriptor icon) {
		super(shell, focusProvider, text, icon);
		this.setId(ID);
	}

	/**
	 * The constructor
	 * 
	 * @param shell
	 * @param text
	 * @param icon
	 */
	public CopyAllAction(Shell shell, String text, ImageDescriptor icon) {
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
			setEnabled(true);
		} else if (event.getSource() instanceof Table) {
			setEnabled(true);
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
			String data = stext.getText();
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
					copyAbleEditor.copyAllItems();
				}
			}
		}
	}
}