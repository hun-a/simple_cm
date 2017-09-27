package com.cubrid.common.ui.spi.model.loader;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import com.cubrid.common.core.common.model.Trigger;
import com.cubrid.common.core.task.ITask;
import com.cubrid.common.core.util.ApplicationType;
import com.cubrid.common.core.util.ApplicationUtil;
import com.cubrid.common.ui.perspective.PerspectiveManager;
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
import com.cubrid.cubridmanager.core.cubrid.trigger.task.GetTriggerListTask;
import com.cubrid.cubridmanager.core.cubrid.trigger.task.JDBCGetTriggerListTask;

public class CubridTriggerFolderLoader extends
CubridNodeLoader {

	public static final String TRIGGER_FOLDER_ID = "Triggers";

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
				database.getDatabaseInfo().setTriggerList(null);
				parent.removeAllChild();
				CubridNodeManager.getInstance().fireCubridNodeChanged(
						new CubridNodeChangedEvent(
								(ICubridNode) parent,
								CubridNodeChangedEventType.CONTAINER_NODE_REFRESH));
				return;
			}
			DatabaseInfo databaseInfo = database.getDatabaseInfo();
			ITask task = null;
			if (ApplicationType.CUBRID_MANAGER.equals(PerspectiveManager.getInstance().getCurrentMode())) {
				task = new GetTriggerListTask(
						parent.getServer().getServerInfo());
				((GetTriggerListTask) task).setDbName(database.getLabel());
			} else {
				task = new JDBCGetTriggerListTask(databaseInfo);
			}

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
			List<Trigger> triggerList = null;
			if (task instanceof GetTriggerListTask) {
				triggerList = ((GetTriggerListTask) task).getTriggerInfoList();
			} else if (task instanceof JDBCGetTriggerListTask) {
				triggerList = ((JDBCGetTriggerListTask) task).getTriggerInfoList();
			}
			if (triggerList != null && !triggerList.isEmpty()) {
				for (Trigger trigger : triggerList) {
					String id = parent.getId() + NODE_SEPARATOR
							+ trigger.getName();
					ICubridNode triggerNode = createTriggerNode(id, trigger);
					parent.addChild(triggerNode);
				}
			}
			databaseInfo.setTriggerList(triggerList);
			Collections.sort(parent.getChildren());
			setLoaded(true);
			CubridNodeManager.getInstance().fireCubridNodeChanged(
					new CubridNodeChangedEvent((ICubridNode) parent,
							CubridNodeChangedEventType.CONTAINER_NODE_REFRESH));
		}
	}

	/**
	 * 
	 * Create trigger node
	 * 
	 * @param id The node id
	 * @param trigger The model object
	 * @return ICubridNode
	 */
	public static ICubridNode createTriggerNode(String id, Trigger trigger) {
		ICubridNode triggerNode = new DefaultSchemaNode(id, trigger.getName(),
				"icons/navigator/trigger_item.png");
		triggerNode.setType(NodeType.TRIGGER);
		triggerNode.setModelObj(trigger);
		triggerNode.setContainer(false);
		return triggerNode;
	}
}
