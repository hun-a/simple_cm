package com.cubrid.common.ui.spi.model.loader.schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;

import com.cubrid.common.core.task.ITask;
import com.cubrid.common.ui.cubrid.table.control.SchemaInfoEditorPart;
import com.cubrid.common.ui.spi.CubridNodeManager;
import com.cubrid.common.ui.spi.Messages;
import com.cubrid.common.ui.spi.event.CubridNodeChangedEvent;
import com.cubrid.common.ui.spi.event.CubridNodeChangedEventType;
import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.common.ui.spi.model.CubridNodeLoader;
import com.cubrid.common.ui.spi.model.DefaultSchemaNode;
import com.cubrid.common.ui.spi.model.ICubridNode;
import com.cubrid.common.ui.spi.model.ICubridNodeLoader;
import com.cubrid.common.ui.spi.model.ISchemaNode;
import com.cubrid.common.ui.spi.model.NodeType;
import com.cubrid.common.ui.spi.util.CommonUITool;
import com.cubrid.cubridmanager.core.common.model.DbRunningType;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;
import com.cubrid.cubridmanager.core.cubrid.table.model.ClassInfo;
import com.cubrid.cubridmanager.core.cubrid.table.model.TableColumn;
import com.cubrid.cubridmanager.core.cubrid.table.task.GetAllClassListTask;
import com.cubrid.cubridmanager.core.cubrid.table.task.GetUserClassColumnsTask;

public class CubridTablesFolderLoader extends
CubridNodeLoader {

	private static final String SYSTEM_TABLE_FOLDER_NAME = Messages.msgSystemTableFolderName;
	public static final String SYSTEM_TABLE_FOLDER_ID = "#System Tables";
	public static final String TABLES_FOLDER_ID = "Tables";
	public static final String TABLES_FULL_FOLDER_SUFFIX_ID = ICubridNodeLoader.NODE_SEPARATOR + TABLES_FOLDER_ID;

	/**
	 * 
	 * Load children object for parent
	 * 
	 * @param parent the parent node
	 * @param monitor the IProgressMonitor object
	 */
	public void load(ICubridNode parent, final IProgressMonitor monitor) {
		synchronized (this) {
			if (isLoaded()) {
				return;
			}
			CubridDatabase database = ((ISchemaNode) parent).getDatabase();
			if (!database.isLogined()
					|| database.getRunningType() == DbRunningType.STANDALONE) {
				database.getDatabaseInfo().setUserTableInfoList(null);
				database.getDatabaseInfo().setSysTableInfoList(null);
				database.getDatabaseInfo().setPartitionedTableMap(null);
				database.getDatabaseInfo().clearSchemas();
				parent.removeAllChild();
				CubridNodeManager.getInstance().fireCubridNodeChanged(
						new CubridNodeChangedEvent(
								(ICubridNode) parent,
								CubridNodeChangedEventType.CONTAINER_NODE_REFRESH));
				return;
			}
			DatabaseInfo databaseInfo = database.getDatabaseInfo();
			final GetAllClassListTask task = new GetAllClassListTask(
					databaseInfo);
			monitorCancel(monitor, new ITask[] {task});
			List<ClassInfo> allClassInfoList = task.getSchema(true, true);
			final String errorMsg = task.getErrorMsg();
			if (!monitor.isCanceled() && errorMsg != null
					&& errorMsg.trim().length() > 0) {
				parent.removeAllChild();
				openErrorBox(errorMsg);
				setLoaded(true);
				return;
			}
			if (monitor.isCanceled()) {
				setLoaded(true);
				return;
			}
			// add system table folder
			String systemTableFolderId = parent.getId() + NODE_SEPARATOR
					+ SYSTEM_TABLE_FOLDER_ID;
			ICubridNode systemTableFolder = parent.getChild(systemTableFolderId);
			parent.removeAllChild();
			if (systemTableFolder == null) {
				systemTableFolder = new DefaultSchemaNode(systemTableFolderId,
						SYSTEM_TABLE_FOLDER_NAME, "icons/navigator/folder_sys.png");
				systemTableFolder.setType(NodeType.SYSTEM_TABLE_FOLDER);
				systemTableFolder.setContainer(true);
				ICubridNodeLoader loader = new CubridSystemTableFolderLoader();
				loader.setLevel(getLevel());
				systemTableFolder.setLoader(loader);
				parent.addChild(systemTableFolder);
				if (getLevel() == DEFINITE_LEVEL) {
					systemTableFolder.getChildren(monitor);
				}
			} else {
				parent.addChild(systemTableFolder);
				if (systemTableFolder.getLoader() != null
						&& systemTableFolder.getLoader().isLoaded()) {
					systemTableFolder.getLoader().setLoaded(false);
					systemTableFolder.getChildren(monitor);
				}
			}
			if (allClassInfoList != null) {
				createUserTableNodes(parent, allClassInfoList, getLevel(),
						monitor);
			}
			database.getDatabaseInfo().setUserTableInfoList(allClassInfoList);
			database.getDatabaseInfo().clearSchemas();
			setLoaded(true);
			CubridNodeManager.getInstance().fireCubridNodeChanged(
					new CubridNodeChangedEvent((ICubridNode) parent,
							CubridNodeChangedEventType.CONTAINER_NODE_REFRESH));
		}
	}

	/**
	 * Create user table node
	 * 
	 * @param parent ICubridNode
	 * @param allClassInfoList A list includes all the class info
	 * @param level The load level
	 * @param monitor The IProgressMonitor
	 */
	private void createUserTableNodes(ICubridNode parent,
			List<ClassInfo> allClassInfoList, int level,
			IProgressMonitor monitor) {
		List<String> tables = new ArrayList<String>();
		for (ClassInfo classInfo : allClassInfoList) {
			String id = parent.getId() + NODE_SEPARATOR
					+ classInfo.getClassName();
			ICubridNode classNode = new DefaultSchemaNode(id,
					classInfo.getClassName(),
					"icons/navigator/schema_table_item.png");
			classNode.setEditorId(SchemaInfoEditorPart.ID);
			classNode.setContainer(true);
			classNode.setModelObj(classInfo);
			classNode.setType(NodeType.USER_TABLE);
			parent.addChild(classNode);

			ICubridNodeLoader loader = null;
			if (classInfo.isPartitionedClass()) {
				classNode.setType(NodeType.USER_PARTITIONED_TABLE_FOLDER);
				classNode.setIconPath("icons/navigator/schema_table_partition.png");
				classNode.setContainer(true);
				loader = new CubridPartitionedTableLoader();
			} else {
				loader = new CubridUserTableLoader();
			}
			loader.setLevel(level);
			classNode.setLoader(loader);
			tables.add(classInfo.getClassName());
		}
		if (level == DEFINITE_LEVEL) {
			CubridDatabase database = ((ISchemaNode) parent).getDatabase();
			DatabaseInfo databaseInfo = database.getDatabaseInfo();
			final GetUserClassColumnsTask task = new GetUserClassColumnsTask(
					databaseInfo);
			monitorCancel(monitor, new ITask[] {task});
			Map<String, List<TableColumn>> columnsOfTable = task.getColumns(tables);
			final String errorMsg = task.getErrorMsg();
			if (!monitor.isCanceled() && !task.isInTransation()
					&& errorMsg != null && errorMsg.trim().length() > 0) {

				Display display = Display.getDefault();
				display.syncExec(new Runnable() {
					public void run() {
						CommonUITool.openErrorBox(errorMsg);
					}
				});
				parent.removeAllChild();
				setLoaded(true);
				return;

			}
			if (monitor.isCanceled()) {
				setLoaded(true);
				return;
			}
			for (ClassInfo classInfo : allClassInfoList) {
				String tableId = parent.getId() + NODE_SEPARATOR
						+ classInfo.getClassName();
				ICubridNode node = parent.getChild(tableId);
				CubridUserTableLoader tableLoader = (CubridUserTableLoader) node.getLoader();
				tableLoader.setColumns(columnsOfTable.get(classInfo.getClassName()));
				node.getChildren(monitor);
				tableLoader.setLoaded(true);
			}

		}
	}

	/**
	 * 
	 * Create user table node for other type
	 * 
	 * @param parent ICubridNode
	 * @param id The node id
	 * @param classInfo The model object
	 * @param level The load level
	 * @param monitor The IProgressMonitor
	 * @return ICubridNode object
	 */
	public static ICubridNode createUserTableNode(ICubridNode parent,
			String id, ClassInfo classInfo, int level, IProgressMonitor monitor) {
		ICubridNode classNode = new DefaultSchemaNode(id,
				classInfo.getClassName(),
				"icons/navigator/schema_table_item.png");
		classNode.setEditorId(SchemaInfoEditorPart.ID);
		classNode.setContainer(true);
		classNode.setModelObj(classInfo);
		classNode.setType(NodeType.USER_TABLE);
		parent.addChild(classNode);

		ICubridNodeLoader loader = null;
		if (classInfo.isPartitionedClass()) {
			classNode.setType(NodeType.USER_PARTITIONED_TABLE_FOLDER);
			classNode.setIconPath("icons/navigator/schema_table_partition.png");
			classNode.setContainer(true);
			loader = new CubridPartitionedTableLoader();
		} else {
			loader = new CubridUserTableLoader();
		}
		loader.setLevel(level);
		classNode.setLoader(loader);
		if (level == DEFINITE_LEVEL) {
			classNode.getChildren(monitor);
		}
		return classNode;
	}

}
