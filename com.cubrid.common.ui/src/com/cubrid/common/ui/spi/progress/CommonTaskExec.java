package com.cubrid.common.ui.spi.progress;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.cubrid.common.core.task.AbstractUITask;
import com.cubrid.common.core.task.ITask;
import com.cubrid.common.ui.spi.IMessageHandler;

public class CommonTaskExec extends TaskExecutor {

	//private ITaskExecutorInterceptor interceptor;
	private IStatus taskExeStatus;
	private final String taskName;
	private IMessageHandler messageHandler;

	///**
	//* The constructor
	//* 
	//* @param taskName
	//* @param interceptor
	//*/
	//public CommonTaskExec(String taskName, ITaskExecutorInterceptor interceptor) {
	//this.taskName = taskName;
	//this.interceptor = interceptor;
	//}

	/**
	 * The constructor
	 * 
	 * @param taskName
	 */
	public CommonTaskExec(String taskName, IMessageHandler messageHandler) {
		this.taskName = taskName;
		this.messageHandler = messageHandler;
	}

	/**
	 * The constructor
	 * 
	 * @param taskName
	 */
	public CommonTaskExec(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * Execute the task
	 * 
	 * @param monitor the monitor
	 * @return <code>true</code>if success;<code>false</code> otherwise
	 */
	public boolean exec(final IProgressMonitor monitor) {
		if (monitor.isCanceled()) {
			return false;
		}
		if (taskName != null) {
			monitor.beginTask(taskName, IProgressMonitor.UNKNOWN);
		}
		for (final ITask task : taskList) {
			if (task instanceof AbstractUITask) {
				((AbstractUITask) task).execute(monitor);
			} else {
				task.execute();
			}

			String errorMsg = task.getErrorMsg();
			if (messageHandler != null) {
				errorMsg = messageHandler.translate(task.getErrorMsg());
			}

			if (openErrorBox(null, errorMsg, monitor)) {
				return false;
			}
			//	Display.getDefault().syncExec(new Runnable() {
			//		public void run() {
			//			if (interceptor != null) {
			//				taskExeStatus = interceptor.postTaskFinished(task);
			//			}
			//		}
			//	});
			if (taskExeStatus != null && taskExeStatus == Status.CANCEL_STATUS) {
				return false;
			}
			if (taskExeStatus != null && taskExeStatus != Status.OK_STATUS) {
				errorMsg = taskExeStatus.getMessage();
				if (messageHandler != null) {
					errorMsg = messageHandler.translate(taskExeStatus.getMessage());
				}
				openErrorBox(null, errorMsg, monitor);
				return false;
			}
			if (monitor.isCanceled()) {
				return false;
			}
		}
		//Display.getDefault().syncExec(new Runnable() {
		//	public void run() {
		//		if (interceptor != null) {
		//			interceptor.completeAll();
		//		}
		//	}
		//});
		return true;
	}

}