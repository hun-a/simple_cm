package com.cubrid.common.ui.spi.progress;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.cubrid.common.core.task.AbstractUITask;
import com.cubrid.common.core.task.ITask;
import com.cubrid.common.ui.CommonUIPlugin;

public class CommonTaskJobExec extends
		TaskJobExecutor {
	private ITaskExecutorInterceptor interceptor;
	private Dialog dialog;
	private IStatus taskExeStatus;

	/*
	 * Constructor
	 */
	public CommonTaskJobExec(ITaskExecutorInterceptor interceptor) {
		this.interceptor = interceptor;
		if (interceptor instanceof Dialog) {
			this.dialog = (Dialog) interceptor;
		}
	}

	public CommonTaskJobExec() {
		//empty
	}

	/**
	 * Execute the task
	 * 
	 * @param monitor the monitor
	 * @return <code>true</code>if success;<code>false</code> otherwise
	 */
	public IStatus exec(IProgressMonitor monitor) {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				setDialogVisible(false);
			}
		});

		if (monitor.isCanceled()) {
			cancel();
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					closeDialog();
				}
			});
			return Status.CANCEL_STATUS;
		}

		for (final ITask task : taskList) {
			if (task instanceof AbstractUITask) {
				((AbstractUITask) task).execute(monitor);
			} else {
				task.execute();
			}
			final String msg = task.getErrorMsg();
			if (msg != null && msg.length() > 0 && !monitor.isCanceled()
					&& !isCanceled()) {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						setDialogVisible(true);
					}
				});
				return new Status(IStatus.ERROR,
						CommonUIPlugin.PLUGIN_ID, msg);
			} else {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						taskExeStatus = refresh(task);
					}
				});
			}
			if (taskExeStatus != Status.OK_STATUS) {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						setDialogVisible(true);
					}
				});
				return taskExeStatus;
			}
			if (monitor.isCanceled()) {
				cancel();
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						closeDialog();
					}
				});
				return Status.CANCEL_STATUS;
			}
		}
		return Status.OK_STATUS;
	}

	/**
	 * Notification that a job has completed execution, either due to cancel,
	 * successful completion, or failure. The event status object indicates how
	 * the job finished, and the reason for failure, if applicable.
	 * 
	 * @param event the event details
	 */
	public void done(IJobChangeEvent event) {
		if (event.getResult() == Status.OK_STATUS) {
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					complete();
					closeDialog();
				}
			});
		}
	}

	/**
	 * Refresh result after a task is completed.
	 * 
	 * @param task the ITask object
	 * @return <code>Status.OK_STATUS</code>if success;error status otherwise
	 */
	protected IStatus refresh(ITask task) {
		IStatus status = Status.OK_STATUS;
		if (null != interceptor && !isCanceled()) {
			status = interceptor.postTaskFinished(task);
		}
		return status;
	}

	/**
	 * Execute the some tips or refresh UI when all the tasks have been
	 * completed.
	 */
	protected void complete() {
		if (null != interceptor) {
			interceptor.completeAll();
		}
	}

	/**
	 * Close the dialog
	 */
	protected void closeDialog() {
		if (null != dialog && dialog.getShell() != null
				&& !dialog.getShell().isDisposed()) {
			dialog.close();
		}
	}

	/**
	 * Set the dialog visible or invisible.
	 * 
	 * @param flag whether it is visible
	 */
	protected void setDialogVisible(boolean flag) {
		if (null != dialog && dialog.getShell() != null
				&& !dialog.getShell().isDisposed()) {
			dialog.getShell().setVisible(flag);
		}
	}
}
