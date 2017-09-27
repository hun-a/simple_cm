package com.cubrid.common.ui.spi.dialog;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.cubrid.common.ui.spi.util.CommonUITool;

public abstract class CMTitleAreaDialog extends TitleAreaDialog {

	/**
	 * The constructor
	 * 
	 * @param parentShell
	 */
	public CMTitleAreaDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected int getShellStyle() {
		return super.getShellStyle() | SWT.RESIZE | SWT.MAX;
	}

	/**
	 * Constrain shell size
	 */
	protected void constrainShellSize() {
		super.constrainShellSize();
		CommonUITool.centerShell(getShell());
	}

	/**
	 * Create button bar
	 * 
	 * @param parent the parent composite
	 * @return the Control object
	 */
	protected Control createButtonBar(Composite parent) {
		Label bottomBarSeparator = new Label(parent, SWT.HORIZONTAL
				| SWT.SEPARATOR);
		bottomBarSeparator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return super.createButtonBar(parent);
	}

	/**
	 * Create button bar without separator
	 * 
	 * @param parent the parent composite
	 * @return the Control object
	 */
	protected Control createButtonBarWithoutSeparator(Composite parent) {
		return super.createButtonBar(parent);
	}
}