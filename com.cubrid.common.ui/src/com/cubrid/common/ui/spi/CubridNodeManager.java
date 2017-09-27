package com.cubrid.common.ui.spi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Display;

import com.cubrid.common.ui.perspective.PerspectiveManager;
import com.cubrid.common.ui.spi.event.CubridNodeChangedEvent;
import com.cubrid.common.ui.spi.event.ICubridNodeChangedListener;

public final class CubridNodeManager {

	private static CubridNodeManager instance;
	/*PerspectiveID, listenerlist*/
	private final Map<String, List<ICubridNodeChangedListener>> cubridNodeChangeListeners = new HashMap<String, List<ICubridNodeChangedListener>>();

	private CubridNodeManager() {
	}

	/**
	 * Return the only CUBRID Node manager
	 * 
	 * @return CubridNodeManager
	 */
	public static CubridNodeManager getInstance() {
		synchronized (CubridNodeManager.class) {
			if (instance == null) {
				instance = new CubridNodeManager();
				instance.addCubridNodeChangeListener(ConnectionKeepAliveHandler.getInstance());
			}
		}
		return instance;
	}

	/**
	 * 
	 * Add CUBRID node object changed listener
	 * 
	 * @param listener
	 *            the ICubridNodeChangedListener object
	 */
	public void addCubridNodeChangeListener(ICubridNodeChangedListener listener) {
		String perspectiveId = PerspectiveManager.getInstance().getCurrentPerspectiveId();
		List<ICubridNodeChangedListener> list = cubridNodeChangeListeners.get(perspectiveId);
		if (list == null) {
			list = new ArrayList<ICubridNodeChangedListener>();
			cubridNodeChangeListeners.put(perspectiveId, list);
		}
		if (!list.contains(listener)) {
			list.add(listener);
		}
	}

	/**
	 * 
	 * Remove CUBRID node object changed listener
	 * 
	 * @param listener the ICubridNodeChangedListener object
	 */
	public void removeCubridNodeChangeListener(
			ICubridNodeChangedListener listener) {
		String perspectiveId = PerspectiveManager.getInstance().getCurrentPerspectiveId();
		List<ICubridNodeChangedListener> list = cubridNodeChangeListeners.get(perspectiveId);
		if (list != null) {
			list.remove(listener);
		}
	}

	/**
	 * 
	 * Fire CUBRID node object changed event to all added listeners
	 * 
	 * @param event the CubridNodeChangedEvent object
	 */
	public void fireCubridNodeChanged(final CubridNodeChangedEvent event) {
		String perspectiveId = PerspectiveManager.getInstance().getCurrentPerspectiveId();
		List<ICubridNodeChangedListener> list = cubridNodeChangeListeners.get(perspectiveId);
		if (list != null) {
			for (int i = 0; i < list.size(); ++i) {
				final ICubridNodeChangedListener listener = (ICubridNodeChangedListener) list.get(i);
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						listener.nodeChanged(event);
					}
				});
			}
		}
	}

}
