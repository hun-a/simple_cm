package com.cubrid.common.ui.common.sqlrunner.dialog;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.cubrid.common.core.util.StringUtil;
import com.cubrid.common.ui.common.Messages;
import com.cubrid.common.ui.common.sqlrunner.event.monitor.ExportErrorDataProgress;
import com.cubrid.common.ui.common.sqlrunner.model.SqlRunnerFailed;
import com.cubrid.common.ui.spi.dialog.CMTitleAreaDialog;
import com.cubrid.common.ui.spi.persist.QueryOptions;
import com.cubrid.common.ui.spi.util.CommonUITool;

public class ExportErrorDataDialog extends CMTitleAreaDialog {
	private Combo fileCharsetCombo;
	private Text saveErrExcelPath;
	private Button saveErrExcelBtn;
	private Map<String, List<SqlRunnerFailed>> failedListMap;
	private String fileName;
	
	public ExportErrorDataDialog(Shell parentShell, final  Map<String, List<SqlRunnerFailed>> failedListMap, String fileName) {
		super(parentShell);
		setShellStyle(SWT.APPLICATION_MODAL); 
		this.failedListMap = failedListMap;
		this.fileName = fileName;
	}
	
	protected Control createDialogArea(Composite parent) {
		setTitle(Messages.titleExportErrorLog);
		setMessage(Messages.msgExportErrorLog);
		
		Composite comp = new Composite(parent, SWT.BORDER);
		comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		comp.setLayout(new GridLayout(1, false));
		
		Composite charsetComp = new Composite(comp, SWT.NONE);
		charsetComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		charsetComp.setLayout(new GridLayout(2, false));
		
		new Label(charsetComp, SWT.NONE).setText(com.cubrid.common.ui.cubrid.table.Messages.lblFileCharset);
		fileCharsetCombo = new Combo(charsetComp, SWT.NONE);
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			fileCharsetCombo.setLayoutData(gridData);
			fileCharsetCombo.setItems(QueryOptions.getAllCharset(null));
			fileCharsetCombo.select(0);
		}
		
		Composite exlComp = new Composite(comp, SWT.NONE);
		exlComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		exlComp.setLayout(new GridLayout(3, false));
		
		new Label(exlComp, SWT.NONE).setText(Messages.runSQLDialogExcelPathLabel);
		
		saveErrExcelPath = new Text(exlComp, SWT.BORDER);
		saveErrExcelPath.setLayoutData(new GridData(GridData.FILL_BOTH));
		saveErrExcelPath.setEditable(false);
		
		saveErrExcelBtn = new Button(exlComp, SWT.NONE);
		saveErrExcelBtn.setText(Messages.brokerLogTopMergeOpenBtn);
		saveErrExcelBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent event) {
				DirectoryDialog dialog = new DirectoryDialog(
						PlatformUI.getWorkbench().getDisplay().getActiveShell());
				dialog.setFilterPath(saveErrExcelPath.getText());

				String dir = dialog.open();
				if (dir != null) {
					if (!dir.endsWith(File.separator)) {
						dir += File.separator;
					}
					saveErrExcelPath.setText(dir);
				}
			}
			
		});
		
		return parent;
	}
	
	/**
	 * When press button,call it
	 * 
	 * @param buttonId the button id
	 */
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			if (StringUtil.isEmpty(saveErrExcelPath.getText())) {
				CommonUITool.openErrorBox(Messages.msgExportErrorLogFailed);
				return;
			}

			ExportErrorDataProgress progress = new ExportErrorDataProgress(
					failedListMap, saveErrExcelPath.getText(), fileName,
					fileCharsetCombo.getText());
			if (!progress.export()) {
				CommonUITool.openErrorBox(progress.getErrMsg());
				return;
			}

			CommonUITool.openInformationBox(Messages.titleSuccess,
					Messages.msgExportErrorLogSuccess);
		}

		setReturnCode(buttonId);
		close();
	}
	
	/**
	 * Constrain the shell size
	 */
	protected void constrainShellSize() {
		super.constrainShellSize();
		getShell().setText(Messages.titleExportErrorLog);
		CommonUITool.centerShell(getShell());
	}

	public Combo getFileCharsetCombo() {
		return fileCharsetCombo;
	}

	public void setFileCharsetCombo(Combo fileCharsetCombo) {
		this.fileCharsetCombo = fileCharsetCombo;
	}

	public Text getSaveErrExcelPath() {
		return saveErrExcelPath;
	}

	public void setSaveErrExcelPath(Text saveErrExcelPath) {
		this.saveErrExcelPath = saveErrExcelPath;
	}
}
