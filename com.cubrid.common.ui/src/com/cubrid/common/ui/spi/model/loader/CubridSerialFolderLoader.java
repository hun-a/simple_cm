package com.cubrid.common.ui.spi.model.loader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import com.cubrid.common.core.common.model.SerialInfo;
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
import com.cubrid.cubridmanager.core.cubrid.serial.task.GetSerialInfoListTask;

public class CubridSerialFolderLoader extends
CubridNodeLoader {

	public static final String SERIAL_FOLDER_ID = "Serials";

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
				database.getDatabaseInfo().setSerialInfoList(null);
				parent.removeAllChild();
				CubridNodeManager.getInstance().fireCubridNodeChanged(
						new CubridNodeChangedEvent(
								(ICubridNode) parent,
								CubridNodeChangedEventType.CONTAINER_NODE_REFRESH));
				return;
			}
			DatabaseInfo databaseInfo = database.getDatabaseInfo();
			final GetSerialInfoListTask task = new GetSerialInfoListTask(
					databaseInfo);
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
			List<SerialInfo> serialInfoList = task.getSerialInfoList();
			List<SerialInfo> authSerialInfoList = new ArrayList<SerialInfo>();

			if (serialInfoList != null && !serialInfoList.isEmpty()) {
				for (SerialInfo serialInfo : serialInfoList) {
					authSerialInfoList.add(serialInfo);
					String id = parent.getId() + NODE_SEPARATOR
							+ serialInfo.getName();
					ICubridNode serialNode = createSerialNode(id, serialInfo);
					parent.addChild(serialNode);
				}
			}
			databaseInfo.setSerialInfoList(authSerialInfoList);
			Collections.sort(parent.getChildren());
			setLoaded(true);
			CubridNodeManager.getInstance().fireCubridNodeChanged(
					new CubridNodeChangedEvent((ICubridNode) parent,
							CubridNodeChangedEventType.CONTAINER_NODE_REFRESH));
		}
	}

	/**
	 * 
	 * Create serial node
	 * 
	 * @param id The node id
	 * @param serialInfo The model object
	 * @return ICubridNode
	 */
	public static ICubridNode createSerialNode(String id, SerialInfo serialInfo) {
		ICubridNode serialNode = new DefaultSchemaNode(id,
				serialInfo.getName(), "icons/navigator/serial_item.png");
		serialNode.setId(id);
		serialNode.setType(NodeType.SERIAL);
		serialNode.setModelObj(serialInfo);
		serialNode.setContainer(false);
		return serialNode;
	}
}
