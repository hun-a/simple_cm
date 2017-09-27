package com.cubrid.common.ui.spi.model.loader.sp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import com.cubrid.common.core.task.ITask;
import com.cubrid.common.ui.spi.CubridNodeManager;
import com.cubrid.common.ui.spi.event.CubridNodeChangedEvent;
import com.cubrid.common.ui.spi.event.CubridNodeChangedEventType;
import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.common.ui.spi.model.CubridNodeLoader;
import com.cubrid.common.ui.spi.model.DefaultSchemaNode;
import com.cubrid.common.ui.spi.model.ICubridNode;
import com.cubrid.common.ui.spi.model.ISchemaNode;
import com.cubrid.common.ui.spi.model.NodeType;
import com.cubrid.cubridmanager.core.common.model.DbRunningType;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;
import com.cubrid.cubridmanager.core.cubrid.sp.model.SPInfo;
import com.cubrid.cubridmanager.core.cubrid.sp.model.SPType;
import com.cubrid.cubridmanager.core.cubrid.sp.task.GetSPInfoListTask;
import com.cubrid.cubridmanager.core.cubrid.user.model.DbUserInfo;

public class CubridFunctionFolderLoader extends
CubridNodeLoader {

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
				database.getDatabaseInfo().setSpFunctionInfoList(null);
				parent.removeAllChild();
				CubridNodeManager.getInstance().fireCubridNodeChanged(
						new CubridNodeChangedEvent(
								(ICubridNode) parent,
								CubridNodeChangedEventType.CONTAINER_NODE_REFRESH));
				return;
			}
			DatabaseInfo databaseInfo = database.getDatabaseInfo();
			final GetSPInfoListTask task = new GetSPInfoListTask(databaseInfo);
			task.setSpType(SPType.FUNCTION);
			monitorCancel(monitor, new ITask[]{task });

			task.execute();
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
			parent.removeAllChild();
			List<SPInfo> spInfoList = task.getSPInfoList();
			DbUserInfo userInfo = database.getDatabaseInfo().getAuthLoginedDbUserInfo();
			boolean isDBA = false;
			String loginUserName = "";
			if (userInfo != null && userInfo.isDbaAuthority()) {
				isDBA = true;
			}
			if (userInfo != null) {
				loginUserName = userInfo.getName();
			}
			List<SPInfo> authSpInfoList = new ArrayList<SPInfo>();
			if (spInfoList != null && !spInfoList.isEmpty()) {
				for (SPInfo spInfo : spInfoList) {
					if (!isDBA
							&& !loginUserName.equalsIgnoreCase(spInfo.getOwner())) {
						continue;
					}
					authSpInfoList.add(spInfo);
					String id = parent.getId() + NODE_SEPARATOR
							+ spInfo.getSpName();
					ICubridNode spNode = createFunctionNode(id, spInfo);
					parent.addChild(spNode);
				}
			}
			databaseInfo.setSpFunctionInfoList(authSpInfoList);
			Collections.sort(parent.getChildren());
			setLoaded(true);
			CubridNodeManager.getInstance().fireCubridNodeChanged(
					new CubridNodeChangedEvent((ICubridNode) parent,
							CubridNodeChangedEventType.CONTAINER_NODE_REFRESH));
		}
	}

	/**
	 * 
	 * Create function node
	 * 
	 * @param id The node id
	 * @param spInfo The model object
	 * @return ICubridNode
	 */
	public static ICubridNode createFunctionNode(String id, SPInfo spInfo) {
		ICubridNode spNode = new DefaultSchemaNode(id, spInfo.getSpName(),
				"icons/navigator/procedure_func_item.png");
		spNode.setType(NodeType.STORED_PROCEDURE_FUNCTION);
		spNode.setModelObj(spInfo);
		spNode.setContainer(false);
		return spNode;
	}
}
