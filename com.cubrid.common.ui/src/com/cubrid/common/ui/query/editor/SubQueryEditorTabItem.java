package com.cubrid.common.ui.query.editor;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;

import com.cubrid.common.ui.query.control.CombinedQueryEditorComposite;

public class SubQueryEditorTabItem extends CTabItem{
	private CombinedQueryEditorComposite control;
	private int tabIndex;
	
	public SubQueryEditorTabItem(CTabFolder parent, int style) {
		super(parent, style);
	}
	
	public void setControl(CombinedQueryEditorComposite control) {
		this.control = control;
		super.setControl(control);
	}
	
	public CombinedQueryEditorComposite getControl() {
		return control;
	}

	/**
	 * @return the tabIndex
	 */
	public int getTabIndex() {
		return tabIndex;
	}

	/**
	 * @param tabIndex the tabIndex to set
	 */
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}	
}
