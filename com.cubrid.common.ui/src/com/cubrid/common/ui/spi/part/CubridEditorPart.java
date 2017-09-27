package com.cubrid.common.ui.spi.part;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.ui.spi.CubridNodeManager;
import com.cubrid.common.ui.spi.LayoutManager;
import com.cubrid.common.ui.spi.event.CubridNodeChangedEvent;
import com.cubrid.common.ui.spi.event.CubridNodeChangedEventType;
import com.cubrid.common.ui.spi.event.ICubridNodeChangedListener;
import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.common.ui.spi.model.CubridServer;
import com.cubrid.common.ui.spi.model.ICubridNode;

public abstract class CubridEditorPart extends
EditorPart implements
ICubridNodeChangedListener {

	private final Logger LOGGER = LogUtil.getLogger(getClass());

	protected ICubridNode cubridNode;

	/**
	 * Initializes this editor with the given editor site and input.
	 * 
	 * @param site the editor site
	 * @param input the editor input
	 * @exception PartInitException if this editor was not initialized
	 *            successfully
	 */
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		if (input != null && input.getToolTipText() != null) {
			setTitleToolTip(input.getToolTipText());
		}
		String title = this.getPartName();
		CubridServer server = null;
		String serverName = "";
		String port = "";
		if (input instanceof ICubridNode) {
			cubridNode = (ICubridNode) input;
			server = cubridNode.getServer();
			if (null != server) {
				serverName = server.getLabel();
				port = server.getMonPort();
			}
		}
		if (input != null) {
			if (title == null) {
				if (null == server) {
					setPartName(input.getName());
				} else {
					setPartName(input.getName() + "@" + serverName + ":" + port);
				}
			} else {
				if (null == server) {
					setPartName(title + " - " + input.getName());
				} else {
					setPartName(title + " - " + input.getName() + "@"
							+ serverName + ":" + port);
				}
			}
		}
		CubridNodeManager.getInstance().addCubridNodeChangeListener(this);
	}

	/**
	 * Dispose the resource and object
	 */
	public void dispose() {
		super.dispose();

		CubridNodeManager.getInstance().removeCubridNodeChangeListener(this);
		//don't open a new now if the close one is last one TOOLS-1079
		//IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		//if (page != null && page.getEditorReferences().length == 0) {
		//	QueryUnit unit = new QueryUnit();
		//	unit.setDatabase(null);
		//	try {
		//		page.openEditor(unit, QueryEditorPart.ID);
		//	} catch (PartInitException e) {
		//		LOGGER.error(e.getMessage(), e);
		//	}
		//}
	}

	/**
	 * Call this method when this editor is focus
	 */
	public void setFocus() {
		if (null != cubridNode) {
			LayoutManager.getInstance().getTitleLineContrItem().changeTitleForViewOrEditPart(
					cubridNode, this);
			LayoutManager.getInstance().getStatusLineContrItem().changeStuatusLineForViewOrEditPart(
					cubridNode, this);
		}
	}

	/**
	 * close the editors which are the same database 
	 * @param event
	 * @param database
	 */
	public void close(CubridNodeChangedEvent event, CubridDatabase database) {
		ICubridNode cubridNode = event.getCubridNode();
		CubridNodeChangedEventType eventType = event.getType();
		if (cubridNode == null || eventType == null) {
			return;
		}
		if (event.getSource() instanceof CubridDatabase) {
			CubridDatabase eventCubridDatabase = (CubridDatabase)event.getSource();
			if (eventCubridDatabase.equals(database)) {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				if (window == null) {
					return; 
				}
				window.getActivePage().closeEditor(this, true);
			}
		}
	}

	/**
	 * close the editors which are the same server 
	 * @param event
	 * @param database
	 */
	public void close(CubridNodeChangedEvent event, CubridServer server) {
		ICubridNode cubridNode = event.getCubridNode();
		CubridNodeChangedEventType eventType = event.getType();
		if (cubridNode == null || eventType == null) {
			return;
		}
		if (event.getSource() instanceof CubridServer) {
			CubridServer eventCubridServer = (CubridServer)event.getSource();
			if (eventCubridServer.equals(server)) {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				if (window == null) {
					return; 
				}
				window.getActivePage().closeEditor(this, true);
			}
		}
	}
}
