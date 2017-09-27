package com.cubrid.common.ui.query.action;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.ActionFactory;

import com.cubrid.common.ui.spi.action.FocusAction;
import com.cubrid.common.ui.spi.util.CommonUITool;

public class PasteAction extends FocusAction {

	public static final String ID = ActionFactory.PASTE.getId();

	/**
	 * The constructor
	 * 
	 * @param shell
	 * @param focusProvider
	 * @param text
	 * @param icon
	 */
	public PasteAction(Shell shell, Control focusProvider, String text,
			ImageDescriptor icon) {
		super(shell, focusProvider, text, icon);
		this.setId(ID);
		this.setActionDefinitionId("org.eclipse.ui.edit.paste");
	}

	/**
	 * The constructor
	 * 
	 * @param shell
	 * @param text
	 * @param icon
	 */
	public PasteAction(Shell shell, String text, ImageDescriptor icon) {
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
			boolean isEnabled = stext != null && stext.isEnabled()
					&& stext.getEditable() && isHasContent();
			setEnabled(isEnabled);
		}
	}

	/**
	 * Return whether has content in clipboard
	 * 
	 * @return boolean
	 */
	private boolean isHasContent() {
		TextTransfer textTransfer = TextTransfer.getInstance();
		Clipboard clipboard = CommonUITool.getClipboard();
		if (clipboard != null) {
			Object contents = clipboard.getContents(textTransfer);
			if (contents instanceof String) {
				String str = (String) contents;
				return str != null && str.length() > 0;
			}
		}
		return false;
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		TextTransfer textTransfer = TextTransfer.getInstance();
		Clipboard clipboard = CommonUITool.getClipboard();
		Control control = getFocusProvider();
		if (control instanceof StyledText) {
			Object contents = clipboard.getContents(textTransfer);
			if (contents instanceof String) {
				String str = (String) contents;
				StyledText text = (StyledText) control;
				Point range = text.getSelectionRange();
				text.replaceTextRange(range.x, range.y, str);
				text.setSelection(range.x + str.length());
			}
		}
	}
}