package com.cubrid.common.ui.cubrid.table.dialog.exp;

import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.ui.cubrid.table.Messages;
import com.cubrid.common.ui.cubrid.table.progress.ExportConfig;
import com.cubrid.common.ui.cubrid.table.progress.ExportDataEditorInput;
import com.cubrid.common.ui.cubrid.table.progress.ExportDataViewPart;
import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.common.ui.spi.util.CommonUITool;

public class ExportDataWizard extends
Wizard {
	private static final Logger LOGGER = LogUtil.getLogger(ExportDataWizard.class);
	private ExportTypePage exportTypePage;
	private ExportSettingPage exportSettingPage;
	private ExportSettingForLoadDBPage exportSettingForLoadDBPage;
	private ExportDataConfirmPage confirmPage;
	private ExportConfig exportConfig;
	private CubridDatabase database;
	private List<String> tableNameList;

	public ExportDataWizard() {

	}

	/**
	 * The constructor
	 * 
	 * @param database
	 * @param tableName
	 */
	public ExportDataWizard(CubridDatabase database, List<String> tableNameList) {
		this.database = database;
		this.tableNameList = tableNameList;

		setWindowTitle(Messages.exportShellTitle);
	}

	/**
	 * Add wizard page
	 */
	public void addPages() {
		exportTypePage = new ExportTypePage();
		addPage(exportTypePage);
		exportSettingPage = new ExportSettingPage();
		addPage(exportSettingPage);
		exportSettingForLoadDBPage = new ExportSettingForLoadDBPage();
		addPage(exportSettingForLoadDBPage);
		confirmPage = new ExportDataConfirmPage();
		addPage(confirmPage);

		setWindowTitle(Messages.exportShellTitle);

		WizardDialog dialog = (WizardDialog) getContainer();
		dialog.addPageChangedListener(exportTypePage);
		dialog.addPageChangedListener(exportSettingPage);
		dialog.addPageChangedListener(exportSettingForLoadDBPage);
		dialog.addPageChangedListener(confirmPage);

		dialog.addPageChangingListener(exportTypePage);
		dialog.addPageChangingListener(exportSettingPage);
		dialog.addPageChangingListener(exportSettingForLoadDBPage);
		dialog.addPageChangingListener(confirmPage);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		boolean result = CommonUITool.openConfirmBox(Messages.confirmStartExportWizard);
		if (result) {
			ExportDataEditorInput input = new ExportDataEditorInput();
			input.setDatabase(database);
			input.setExportConfig(exportConfig);
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(
						input, ExportDataViewPart.ID);
			} catch (Exception e) {
				CommonUITool.openErrorBox(getShell(), e.getMessage());
				LOGGER.error("", e);
			}

		}
		return result;
	}

	/**
	 * perform Cancel
	 * 
	 * @return boolean
	 */
	public boolean performCancel() {
		return CommonUITool.openConfirmBox(Messages.confirmExitExportWizard);
	}

	public IWizardPage getNextPage(IWizardPage page) {
		if (page.equals(exportTypePage)) {
			if (exportTypePage.getExportType() == ExportConfig.EXPORT_TO_LOADDB) {
				return exportSettingForLoadDBPage;
			} else if (exportTypePage.getExportType() == ExportConfig.EXPORT_TO_FILE) {
				return exportSettingPage;
			} else { //history
				if (getConfigModel().getExportType() == ExportConfig.EXPORT_TO_LOADDB) {
					return exportSettingForLoadDBPage;
				} else {
					return exportSettingPage;
				}
			}
		} else if (exportSettingPage.equals(page) || exportSettingForLoadDBPage.equals(page)) {
			return confirmPage;
		}
		return super.getNextPage(page);
	}

	public boolean canFinish() {
		if (this.getContainer().getCurrentPage() instanceof ExportDataConfirmPage) {
			return true;
		}
		return false;
	}

	public CubridDatabase getDatabase() {
		return database;
	}

	public List<String> getTableNameList() {
		return tableNameList;
	}

	public ExportConfig getConfigModel() {
		return exportConfig;
	}

	public void setConfigModel(ExportConfig configModel) {
		this.exportConfig = configModel;
	}
}
