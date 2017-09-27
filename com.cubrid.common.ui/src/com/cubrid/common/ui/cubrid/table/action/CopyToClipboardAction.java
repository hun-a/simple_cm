package com.cubrid.common.ui.cubrid.table.action;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.core.util.StringUtil;
import com.cubrid.common.ui.query.editor.QueryEditorPart;
import com.cubrid.common.ui.query.editor.QueryUnit;
import com.cubrid.common.ui.spi.action.SelectionAction;
import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.common.ui.spi.model.DefaultSchemaNode;
import com.cubrid.common.ui.spi.model.ICubridNode;
import com.cubrid.common.ui.spi.model.ISchemaNode;
import com.cubrid.common.ui.spi.model.NodeType;
import com.cubrid.common.ui.spi.util.ActionSupportUtil;
import com.cubrid.common.ui.spi.util.CommonUITool;
import com.cubrid.common.ui.spi.util.LayoutUtil;

public abstract class CopyToClipboardAction extends
SelectionAction {
	private static final Logger LOGGER = LogUtil.getLogger(CopyToClipboardAction.class);
	private boolean isCopyToEditor = false;

	/**
	 * If copy to query editor.
	 * 
	 * @return if true copy SQL to editor.
	 */
	public boolean isCopyToEditor() {
		return isCopyToEditor;
	}

	/**
	 * Set if copy SQL to query editor.
	 * 
	 * @param isCopyToEditor boolean.
	 */
	public void setCopyToEditor(boolean isCopyToEditor) {
		this.isCopyToEditor = isCopyToEditor;
	}

	/**
	 * The constructor
	 * 
	 * @param shell
	 * @param provider
	 * @param text
	 * @param icon
	 */
	public CopyToClipboardAction(Shell shell, ISelectionProvider provider,
			String text, ImageDescriptor icon) {
		super(shell, provider, text, icon);
		this.setToolTipText(text);
	}

	/**
	 * Sets this action support this object
	 * 
	 * @see org.eclipse.jface.action.IAction.ISelectionAction
	 * @param obj Object
	 * @return boolean
	 */
	public boolean isSupported(Object obj) {
		return ActionSupportUtil.isSupportMultiSelection(obj, new String[]{
				NodeType.USER_TABLE, NodeType.USER_PARTITIONED_TABLE_FOLDER },
				false);
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		final Object[] obj = this.getSelectedObj();
		if (!isSupported(obj)) {
			setEnabled(false);
			return;
		}

		doRun(obj);
	}

	/**
	 * Run
	 * 
	 * @param nodes
	 */
	public void run(ICubridNode[] nodes) {
		doRun(nodes);
	}

	/**
	 * Do run
	 * 
	 * @param objects
	 */
	protected void doRun(final Object[] objects) {
		final int len = objects.length;
		final Display display = PlatformUI.getWorkbench().getDisplay();
		BusyIndicator.showWhile(display, new Runnable() {
			public void run() {
				IEditorPart ep = null;
				if (isCopyToEditor) {
					IWorkbenchPage activePage = LayoutUtil.getActivePage();
					ep = activePage.getActiveEditor();
					if (!(ep instanceof QueryEditorPart)) {
						ep = openNewQueryEditor();
					}
					if (!(ep instanceof QueryEditorPart)) {
						ep = null;
					}
				}

				StringBuffer allTableSql = new StringBuffer();
				for (int i = 0; i < len; i++) {
					DefaultSchemaNode table = (DefaultSchemaNode) objects[i];
					String sql = getStmtSQL(table, ep);
					if (sql != null && sql.trim().length() > 0) {
						allTableSql.append(sql);
						allTableSql.append(StringUtil.NEWLINE);
						allTableSql.append(StringUtil.NEWLINE);
					}
				}
				if (allTableSql.length() > 0) {
					if (isCopyToEditor) {
						if (ep instanceof QueryEditorPart) {
							((QueryEditorPart) ep).setQuery(allTableSql.toString(), true, false, false);
						}
					} else {
						CommonUITool.copyContentToClipboard(allTableSql.toString());
					}
				}
			}
		});
	}

	/**
	 * Open the new query editor
	 * 
	 * @return IEditorPart
	 */
	private IEditorPart openNewQueryEditor() {
		IWorkbenchPage activePage = LayoutUtil.getActivePage();
		QueryUnit queryUnit = new QueryUnit();
		Object[] selected = getSelectedObj();
		CubridDatabase database = null;
		if (selected.length >= 1 && selected[0] instanceof ISchemaNode) {
			database = ((ISchemaNode) selected[0]).getDatabase();
			queryUnit.setDatabase(database);
		}
		try {
			IEditorPart editor = activePage.openEditor(queryUnit,
					QueryEditorPart.ID);
			if (editor != null && database != null) {
				((QueryEditorPart) editor).connect(database);
			}
			return editor;
		} catch (PartInitException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Get statement SQL, the sub class should extend it.
	 * 
	 * @param schemaNode DefaultSchemaNode
	 * @return String
	 */
	abstract protected String getStmtSQL(DefaultSchemaNode schemaNode, IEditorPart editorPart);

	/**
	 * Generate a sql for a specific shard on the query editor.
	 * [TOOLS-2425]Support shard broker
	 *
	 * @param schemaNode
	 * @param editorPart
	 * @param sql
	 * @return
	 */
	protected String wrapShardSQL(DefaultSchemaNode schemaNode, IEditorPart editorPart, String sql) {
		return sql;
	}
}
