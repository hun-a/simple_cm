package com.cubrid.common.ui.perspective;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

import com.cubrid.common.core.util.ApplicationType;
import com.cubrid.common.core.util.ApplicationUtil;
import com.cubrid.common.ui.CommonUIPlugin;
import com.cubrid.common.ui.spi.persist.PersistUtils;

public class PerspectiveManager {
	private static final Map<String, IPerspectiveChangedListener> listenerMap = new HashMap<String, IPerspectiveChangedListener>();
	private static PerspectiveManager instance = null;
	private String activePerspectiveId;

	private PerspectiveManager() {
	}

	/**
	 * Return the only PerspectiveManager
	 *
	 * @return PerspectiveManager
	 */
	public static PerspectiveManager getInstance() {
		synchronized (PerspectiveManager.class) {
			if (instance == null) {
				instance = new PerspectiveManager();
			}
		}
		return instance;
	}

	public synchronized void addPerspectiveListener(IPerspectiveChangedListener listener) {
		listenerMap.put(listener.getPerspectiveId(), listener);
	}

	public synchronized void removePerspectiveListener(IPerspectiveChangedListener listener) {
		listenerMap.remove(listener.getPerspectiveId());
	}

	public synchronized void firePerspectiveChanged(String sourceId, String targetId) {
		if (!sourceId.equals(targetId)) {
			PerspectiveChangeEvent event = new PerspectiveChangeEvent(sourceId,
					targetId);

			IPerspectiveChangedListener listenrHide = listenerMap.get(sourceId);
			if (listenrHide != null) {
				listenrHide.hidePerspectiveHide(event);
			}

			IPerspectiveChangedListener listenrShow = listenerMap.get(targetId);
			if (listenrShow != null) {
				listenrShow.showPerspective(event);
			}

			for (IPerspectiveChangedListener listener : listenerMap.values()) {
				listener.perspectiveChanged(event);
			}
			setSelectedPerspective(targetId);
		}
	}

	public synchronized String getCurrentPerspectiveId() {
		if (activePerspectiveId != null) {
			return activePerspectiveId;
		}

		if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() == null) {
			return "";
		}

		return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getPerspective().getId();
	}

	public synchronized void openPerspective(String perspectiveId) {
		try {
			if (activePerspectiveId == null) {
				activePerspectiveId = PerspectiveManager.getInstance().getCurrentPerspectiveId();
			}
			String oldPerspectiveId = activePerspectiveId;
			activePerspectiveId = perspectiveId;
			PlatformUI.getWorkbench().showPerspective(perspectiveId,
					PlatformUI.getWorkbench().getActiveWorkbenchWindow());
			PerspectiveManager.getInstance().firePerspectiveChanged(oldPerspectiveId,
					activePerspectiveId);
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
	}

	public synchronized ApplicationType getCurrentMode() {
		String id = getCurrentPerspectiveId();
		if (IPerspectiveConstance.CM_PERSPECTIVE_ID.equals(id)) {
			return ApplicationType.CUBRID_MANAGER;
		} else if (IPerspectiveConstance.CQB_PERSPECTIVE_ID.equals(id)) {
			return ApplicationType.CUBRID_QUERY_BROWSER;
		}

		return ApplicationUtil.getApplicationType();
	}

	public boolean isManagerMode() {
		return ApplicationType.CUBRID_MANAGER.equals(getCurrentMode());
	}

	public String getSelectedPerspective() {
		return PersistUtils.getGlobalPreferenceValue(CommonUIPlugin.PLUGIN_ID,
				"selected_perspective");
	}

	public void setSelectedPerspective(String id) {
		PersistUtils.setGlobalPreferenceValue(CommonUIPlugin.PLUGIN_ID, "selected_perspective", id);
	}
}
