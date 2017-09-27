package com.cubrid.common.ui.er;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.cubrid.common.ui.CommonUIPlugin;
import com.cubrid.common.ui.spi.model.CubridDatabase;

public class SchemaEditorInput implements
IEditorInput {
	private CubridDatabase database = null;
	private TreeViewer tv = null;
	private static String NAME = "ER Design";
	private String toolTip = NAME;

	public SchemaEditorInput(CubridDatabase database, TreeViewer tv) {
		this.database = database;
		this.tv = tv;
	}

	public TreeViewer getTv() {
		return tv;
	}

	public void setTv(TreeViewer tv) {
		this.tv = tv;
	}

	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return CommonUIPlugin.getImageDescriptor("icons/action/schema_edit_on.png");
	}

	public String getName() {
		return NAME;
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return NAME;
	}

	public String getToolTip() {
		return NAME;
	}

	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

	public CubridDatabase getDatabase() {
		return database;
	}

	public void setDatabase(CubridDatabase database) {
		this.database = database;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof SchemaEditorInput)) {
			return false;
		}

		if (((SchemaEditorInput) obj).getName().equals(this.getName())) {
			return false;
		}

		return this.database.equals(((SchemaEditorInput) obj).getDatabase());
	}

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter) {
		if (adapter.equals(SchemaEditorInput.class)) {
			return this;
		}
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public int hashCode() {
		return super.hashCode();
	}
}
