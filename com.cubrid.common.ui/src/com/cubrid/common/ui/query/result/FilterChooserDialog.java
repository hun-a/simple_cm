package com.cubrid.common.ui.query.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.cubrid.common.ui.query.Messages;
import com.cubrid.common.ui.query.control.ColumnInfo;
import com.cubrid.common.ui.spi.dialog.CMTrayDialog;
import com.cubrid.common.ui.spi.util.CommonUITool;

public class FilterChooserDialog extends CMTrayDialog {
	private CheckboxTableViewer tv;
	private List<ColumnInfo> colInfoList;
	private List<ColumnInfo> selectedColInfoList = new ArrayList<ColumnInfo>();
	//private Table colTable;
	private Button selectAllBtn;
	
	public FilterChooserDialog(Shell parentShell, List<ColumnInfo> colInfoList,
			List<ColumnInfo> selectedColInfoList) {
		super(parentShell);
		this.colInfoList = colInfoList;
		this.selectedColInfoList = selectedColInfoList;
	}

	/**
	 * Create dialog area content
	 * 
	 * @param parent the parent composite
	 * @return the control
	 */
	protected Control createDialogArea(Composite parent) {
		Composite parentComp = (Composite) super.createDialogArea(parent);
		Composite composite = new Composite(parentComp, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 0;
		layout.horizontalSpacing = 0;
		composite.setLayout(layout);
		createComposite(composite);

		return parentComp;
	}

	/**
	 * 
	 * Create the composite
	 * 
	 * @param parent Composite
	 */
	private void createComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		composite.setLayout(layout);

		Label infoLabel = new Label(composite, SWT.NONE);
		infoLabel.setLayoutData(CommonUITool.createGridData(GridData.FILL_HORIZONTAL, 3, 1, -1, -1));
		infoLabel.setText(Messages.lblFilterChooser);

		String[] columnNames = new String[]{Messages.colColumn };
		tv = (CheckboxTableViewer) CommonUITool.createCheckBoxTableViewer(
				composite, null, columnNames,
				CommonUITool.createGridData(GridData.FILL_BOTH, 3, 1, 300, 200));

		tv.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				if (!event.getChecked() && selectAllBtn.getSelection()) {
					selectAllBtn.setSelection(false);
				}

				if (colInfoList != null
						&& colInfoList.size() == tv.getCheckedElements().length) {
					selectAllBtn.setSelection(true);
				}
			}
		});

		final List<Map<String, Object>> colNameList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < colInfoList.size(); i++) {
			ColumnInfo colInfo = colInfoList.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("0", colInfo.getName());
			map.put("1", colInfo);
			colNameList.add(map);
		}
		tv.setInput(colNameList);
		tv.getTable().setFocus();

		selectAllBtn = new Button(composite, SWT.CHECK);
		{
			selectAllBtn.setText(Messages.btnSelectAll);
			GridData gridData = new GridData();
			gridData.grabExcessHorizontalSpace = true;
			gridData.horizontalIndent = 0;
			gridData.horizontalSpan = 3;
			selectAllBtn.setLayoutData(gridData);
		}
		selectAllBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				boolean selection = selectAllBtn.getSelection();
				tv.setAllChecked(selection);
			}
		});

		for (TableItem item : tv.getTable().getItems()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) item.getData();
			if (map != null && selectedColInfoList.contains(map.get("1"))) {
				item.setChecked(true);
			}
		}
		
		TableColumn[] tblCols = tv.getTable().getColumns();
		for (TableColumn tblCol : tblCols) {
			tblCol.setWidth(280);
		}
		
		if(colInfoList != null && selectedColInfoList != null && colInfoList.size() == selectedColInfoList.size()) {
			selectAllBtn.setSelection(true);
		}
	}

	/**
	 * Create buttons for button bar
	 * 
	 * @param parent the parent composite
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, com.cubrid.common.ui.common.Messages.btnOK, true);
		createButton(parent, IDialogConstants.CANCEL_ID, com.cubrid.common.ui.common.Messages.btnCancel, true);
	}

	/**
	 * Constrain the shell size
	 */
	protected void constrainShellSize() {
		super.constrainShellSize();
		getShell().setText(Messages.titleFilterChooser);
	}

	/**
	 * Call this method when the button in button bar is pressed
	 * 
	 * @param buttonId the button id
	 */
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			selectedColInfoList.clear();
			Object[] objs = tv.getCheckedElements();
			if (objs != null && objs.length > 0) {
				for (Object obj : objs) {
					@SuppressWarnings("unchecked")
					Map<String, Object> map = (Map<String, Object>) obj;
					ColumnInfo colInfo = (ColumnInfo) map.get("1");
					selectedColInfoList.add(colInfo);
				}
			}
		}

		super.buttonPressed(buttonId);
	}

	public List<ColumnInfo> getSelectedColInfoList() {
		return selectedColInfoList;
	}
}
