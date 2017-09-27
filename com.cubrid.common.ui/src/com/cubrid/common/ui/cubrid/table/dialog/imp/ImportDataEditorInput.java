package com.cubrid.common.ui.cubrid.table.dialog.imp;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.cubrid.common.ui.spi.model.CubridDatabase;

public class ImportDataEditorInput implements IEditorInput {

	private ImportConfig importConfig;
	private CubridDatabase database;

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter) {
		if (adapter.equals(CubridDatabase.class)) {
			return database;
		} else if (adapter.equals(ImportConfig.class)) {
			return importConfig;
		}
		return null;
	}

	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		return database.getName() + "@"
				+ database.getDatabaseInfo().getBrokerIP();
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return this.getName();
	}

	public CubridDatabase getDatabase() {
		return database;
	}

	public void setDatabase(CubridDatabase database) {
		this.database = database;
	}

	public ImportConfig getImportConfig() {
		return importConfig;
	}

	public void setImportConfig(ImportConfig importConfig) {
		this.importConfig = importConfig;
	}
}
