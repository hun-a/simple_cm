package com.cubrid.common.ui.er.loader;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import com.cubrid.common.core.common.model.SchemaInfo;
import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.common.ui.spi.model.DefaultSchemaNode;
import com.cubrid.common.ui.spi.model.ICubridNode;
import com.cubrid.common.ui.spi.model.NodeType;
import com.cubrid.common.ui.spi.model.loader.schema.CubridTablesFolderLoader;

public class ERSchemaTableNodesLoader {
	private final CubridDatabase dbNode;

	public ERSchemaTableNodesLoader(CubridDatabase dbNode) {
		this.dbNode = dbNode;
	}

	/**
	 * Get the tree view of CQB
	 * 
	 * @return TreeViewer
	 */
	public TreeViewer getTreeView() {
		// on CM CubridNavigatorView navigatorView =
		// CubridNavigatorView.getNavigatorView("com.cubrid.cubridmanager.host.navigator");
		// CubridNavigatorView navigatorView =
		// CubridNavigatorView.getNavigatorView(CubridQueryNavigatorView.ID);
		// return navigatorView.getViewer();
		return null;
	}

	/**
	 * Get all the user tables node. If the table folder hasnot been expanded,
	 * it cannot get all nodes
	 * 
	 * @return List<DefaultSchemaNode>
	 */
	public List<DefaultSchemaNode> getAllTablesNode() {
		List<DefaultSchemaNode> nodes = new ArrayList<DefaultSchemaNode>();
		if (!dbNode.isLogined()) {
			return nodes;
		}

		String tablesFolderId = dbNode.getId()
				+ CubridTablesFolderLoader.TABLES_FULL_FOLDER_SUFFIX_ID;
		ICubridNode tablesFolder = dbNode.getChild(tablesFolderId);
		if (null == tablesFolder) {
			return nodes;
		}

		List<ICubridNode> children = tablesFolder.getChildren();
		for (ICubridNode node : children) {
			if (NodeType.USER_TABLE.equals(node.getType())
					&& node instanceof DefaultSchemaNode) {
				nodes.add((DefaultSchemaNode) node);
			}
		}

		return nodes;
	}

	/**
	 * Get all the user tables node infos
	 * 
	 * @return List<SchemaInfo>
	 */
	public List<SchemaInfo> getAllUserTablesInfo() {
		List<DefaultSchemaNode> tableNodes = getAllTablesNode();
		List<SchemaInfo> tables = new ArrayList<SchemaInfo>();

		for (DefaultSchemaNode node : tableNodes) {
			SchemaInfo table = dbNode.getDatabaseInfo().getSchemaInfo(
					node.getName());
			if (null != table) {
				tables.add(table);
			}
		}

		return tables;
	}

	/**
	 * Load the user table folder
	 */
	public void load() {
		if (!dbNode.isLogined()) {
			return;
		}

		String tablesFolderId = dbNode.getId()
				+ CubridTablesFolderLoader.TABLES_FULL_FOLDER_SUFFIX_ID;
		final ICubridNode tablesFolder = dbNode.getChild(tablesFolderId);
		if (null == tablesFolder) {
			return;
		}

		if (tablesFolder.getChildren().size() < 1) {
			final TreeViewer tv = getTreeView();
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					tv.expandToLevel(tablesFolder, 1);
				}
			});
		}
	}
}
