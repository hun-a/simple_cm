package com.cubrid.common.ui.er.control;

import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.ui.er.editor.ERSchemaEditor;
import com.cubrid.common.ui.er.model.ERSchema;

abstract public class ExportDataController {
	private static final Logger LOGGER = LogUtil
			.getLogger(ExportDataController.class);

	protected ERSchemaEditor erSchemaEditor;
	protected ERSchema erSchema;
	protected String latestFileFullName = null;

	public ExportDataController() {
	}

	public ExportDataController(ERSchemaEditor erSchemaEditor) {
		this.erSchemaEditor = erSchemaEditor;
	}

	public ExportDataController(ERSchema erSchema) {
		this.erSchema = erSchema;
	}

	abstract public boolean exportData(Shell parentShell, boolean isDirectSave);

	public static ExportDataController getNewAdaptor(Class adaptor,
			ERSchemaEditor erSchemaEditor) {

		try {
			ExportDataController currentExporter = (ExportDataController) adaptor
					.newInstance();
			currentExporter.setErSchemaEditor(erSchemaEditor);
			return currentExporter;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public ERSchemaEditor geterSchemaEditor() {
		return erSchemaEditor;
	}

	public void setErSchemaEditor(ERSchemaEditor erSchemaEditor) {
		this.erSchemaEditor = erSchemaEditor;
	}

	protected ERSchema getERSchema() {
		if (erSchema == null) {
			erSchema = erSchemaEditor.getERSchema();
		}
		return erSchema;
	}

	protected void setERSchema(ERSchema erSchema) {
		this.erSchema = erSchema;
	}

	public String getLatestFileFullName() {
		return latestFileFullName;
	}

	public void setLatestFileFullName(String latestFileFullName) {
		this.latestFileFullName = latestFileFullName;
	}
}
