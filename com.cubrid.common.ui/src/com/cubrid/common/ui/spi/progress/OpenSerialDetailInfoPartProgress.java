package com.cubrid.common.ui.spi.progress;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.slf4j.Logger;

import com.cubrid.common.core.common.model.SerialInfo;
import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.cubridmanager.core.cubrid.serial.task.GetSerialInfoListTask;

public class OpenSerialDetailInfoPartProgress implements IRunnableWithProgress {

	private static final Logger LOGGER = LogUtil.getLogger(OpenSerialDetailInfoPartProgress.class);
	private final CubridDatabase database;
	private List<SerialInfo> serialList = null;
	private boolean success = false;

	public OpenSerialDetailInfoPartProgress (CubridDatabase database) {
		this.database = database;
	}

	public void run(IProgressMonitor monitor) throws InvocationTargetException,
	InterruptedException {
		final GetSerialInfoListTask task = new GetSerialInfoListTask(
				database.getDatabaseInfo());
		task.execute();
		if (!(task.isSuccess())) {
			LOGGER.error(task.getErrorMsg());
			return ;
		}

		serialList = task.getSerialInfoList();
		success = true;
	}

	/**
	 * load serialinfo list
	 * 
	 * @return Catalog
	 */
	public void loadSerialInfoList() {
		Display display = Display.getDefault();
		display.syncExec(new Runnable() {
			public void run() {
				try {
					new ProgressMonitorDialog(null).run(true, false,
							OpenSerialDetailInfoPartProgress.this);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		});
	}

	public boolean isSuccess() {
		return success;
	}

	public List<SerialInfo> getSerialList() {
		return serialList;
	}

}
