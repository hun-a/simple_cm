package com.cubrid.common.ui.spi.model;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;

import com.cubrid.common.core.task.ITask;
import com.cubrid.common.ui.spi.util.CommonUITool;

public abstract class CubridNodeLoader implements
ICubridNodeLoader {
	private boolean isLoaded = false;
	private int level = FIRST_LEVEL;
	public static final String USERS_FOLDER_ID = "Users";
	/**
	 * Return whether it has been loaded
	 * 
	 * @return <code>true</code> if it is loaded;<code>false</code> otherwise
	 */
	public boolean isLoaded() {
		return isLoaded;
	}

	/**
	 * 
	 * Set loaded status
	 * 
	 * @param isLoaded whether it is loaded
	 */
	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;

	}

	/**
	 * Set loaded level
	 * 
	 * @param level the loaded depth
	 */
	public void setLevel(int level) {
		this.level = level;
		if (level != FIRST_LEVEL && level != DEFINITE_LEVEL) {
			this.level = FIRST_LEVEL;
		}
	}

	/**
	 * Get loaded level
	 * 
	 * @return the loaded depth
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * 
	 * Monitoring cancel operation
	 * 
	 * @param monitor IProgressMonitor
	 * @param tasks ITask[]
	 */
	public void monitorCancel(final IProgressMonitor monitor,
			final ITask[] tasks) {
		String name = getClass().getName() == null ? "loading"
				: getClass().getName();
		Thread thread = new Thread(name + " monitoring thread") {
			public void run() {
				while (!monitor.isCanceled() && !isLoaded()) {
					try {
						sleep(WAIT_TIME);
					} catch (InterruptedException e) {
					}
				}
				if (monitor.isCanceled() && tasks != null && tasks.length > 0) {
					for (ITask task : tasks) {
						if (task != null) {
							task.cancel();
						}
					}
				}
			}
		};
		thread.start();
	}

	/**
	 * 
	 * Open the error box
	 * 
	 * @param msg the error message
	 */
	protected void openErrorBox(final String msg) {
		Display display = Display.getDefault();
		display.syncExec(new Runnable() {
			public void run() {
				if (msg != null && msg.trim().length() > 0) {
					CommonUITool.openErrorBox(msg);
				}
			}
		});
	}
}
