package com.cubrid.common.ui.cubrid.table.editor;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.cubrid.common.core.common.model.SchemaInfo;
import com.cubrid.common.ui.cubrid.table.Messages;
import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.common.ui.spi.model.ISchemaNode;
import com.cubrid.cubridmanager.core.cubrid.database.model.Collation;

public class TableEditorInput implements IEditorInput {
	private final CubridDatabase database;
	private final boolean isNewTableFlag;
	private final SchemaInfo schemaInfo;
	private final ISchemaNode editedTableNode;
	private List<String> dbUserList;
	private List<Collation> collations;
	private int type;
	
	/**
	 *
	 * @param database
	 * @param isNewTableFlag
	 * @param schemaInfo
	 * @param table
	 * @param type EditTableAction.MODE_TABLE_EDIT or EditTableAction.MODE_INDEX_EDIT
	 */
	public TableEditorInput (CubridDatabase database, boolean isNewTableFlag,
			SchemaInfo schemaInfo, ISchemaNode table, int type) {
		this.database = database;
		this.editedTableNode = table;
		this.isNewTableFlag = isNewTableFlag;
		this.schemaInfo = schemaInfo;
		this.type = type;
	}
	
	@SuppressWarnings("all")
	public Object getAdapter(Class adapter) {
		return null;
	}

	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		if (isNewTableFlag) {
			return Messages.newTableMsgTitle;
		} else {
			return Messages.bind(Messages.editTableMsgTitle,
					schemaInfo.getClassname());
		}
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		if (isNewTableFlag) {
			return Messages.newTableMsgTitle;
		} else {
			return Messages.bind(Messages.editTableMsgTitle,
					schemaInfo.getClassname());
		}
	}

	public CubridDatabase getDatabase() {
		return database;
	}

	public boolean isNewTableFlag() {
		return isNewTableFlag;
	}

	public SchemaInfo getSchemaInfo() {
		return schemaInfo;
	}

	public ISchemaNode getEditedTableNode() {
		return editedTableNode;
	}

	public List<String> getDbUserList() {
		return dbUserList;
	}

	public void setDbUserList(List<String> dbUserList) {
		this.dbUserList = dbUserList;
	}

	public List<Collation> getCollationList() {
		return collations;
	}
	
	public void setCollationList(List<Collation> collations) {
		this.collations = collations;
	}

	public int getType() {
		return type;
	}
}
