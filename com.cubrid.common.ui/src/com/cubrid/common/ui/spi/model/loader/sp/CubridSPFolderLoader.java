package com.cubrid.common.ui.spi.model.loader.sp;

import org.eclipse.core.runtime.IProgressMonitor;

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
import com.cubrid.cubridmanager.core.common.model.DbRunningType;

public class CubridSPFolderLoader extends
CubridNodeLoader {

	private static final String FUNCTION_FOLDER_NAME = Messages.msgFunctionFolderName;
	private static final String PROCEDURE_FOLDER_NAME = Messages.msgProcedureFolderName;

	public static final String SP_FOLDER_ID = "Stored procedure";
	public static final String FUNCTION_FOLDER_ID = "Function";
	public static final String PROCEDURE_FOLDER_ID = "Procedure";

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
				parent.removeAllChild();
				CubridNodeManager.getInstance().fireCubridNodeChanged(
						new CubridNodeChangedEvent(
								(ICubridNode) parent,
								CubridNodeChangedEventType.CONTAINER_NODE_REFRESH));
				return;
			}
			// add function folder
			String functionFolderId = parent.getId() + NODE_SEPARATOR
					+ FUNCTION_FOLDER_ID;
			ICubridNode functionFolder = parent.getChild(functionFolderId);
			if (functionFolder == null) {
				functionFolder = new DefaultSchemaNode(functionFolderId,
						FUNCTION_FOLDER_NAME, "icons/navigator/folder.png");
				functionFolder.setType(NodeType.STORED_PROCEDURE_FUNCTION_FOLDER);
				functionFolder.setContainer(true);
				ICubridNodeLoader loader = new CubridFunctionFolderLoader();
				loader.setLevel(getLevel());
				functionFolder.setLoader(loader);
				parent.addChild(functionFolder);
				if (getLevel() == DEFINITE_LEVEL) {
					functionFolder.getChildren(monitor);
				}
			} else {
				if (functionFolder.getLoader() != null
						&& functionFolder.getLoader().isLoaded()) {
					functionFolder.getLoader().setLoaded(false);
					functionFolder.getChildren(monitor);
				}
			}
			// add procedure folder
			String procedureFolderId = parent.getId() + NODE_SEPARATOR
					+ PROCEDURE_FOLDER_ID;
			ICubridNode procedureFolder = parent.getChild(procedureFolderId);
			if (procedureFolder == null) {
				procedureFolder = new DefaultSchemaNode(procedureFolderId,
						PROCEDURE_FOLDER_NAME, "icons/navigator/folder.png");
				procedureFolder.setType(NodeType.STORED_PROCEDURE_PROCEDURE_FOLDER);
				procedureFolder.setContainer(true);
				ICubridNodeLoader loader = new CubridProcedureFolderLoader();
				loader.setLevel(getLevel());
				procedureFolder.setLoader(loader);
				parent.addChild(procedureFolder);
				if (getLevel() == DEFINITE_LEVEL) {
					procedureFolder.getChildren(monitor);
				}
			} else {
				if (procedureFolder.getLoader() != null
						&& procedureFolder.getLoader().isLoaded()) {
					procedureFolder.getLoader().setLoaded(false);
					procedureFolder.getChildren(monitor);
				}
			}
			setLoaded(true);
			CubridNodeManager.getInstance().fireCubridNodeChanged(
					new CubridNodeChangedEvent((ICubridNode) parent,
							CubridNodeChangedEventType.CONTAINER_NODE_REFRESH));
		}
	}
}
