package com.cubrid.common.core.task;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

public abstract class AbstractUITask extends
AbstractTask {

	/**
	 * @see AbstractTask#execute()
	 */
	public final void execute() {
		execute(new NullProgressMonitor());
	}

	/**
	 * 
	 * Execute task and update UI progress
	 * 
	 * @param monitor IProgressMonitor
	 */
	public abstract void execute(IProgressMonitor monitor);
}
