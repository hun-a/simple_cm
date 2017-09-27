package com.cubrid.common.ui.common.sqlrunner;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.cubrid.common.ui.common.sqlrunner.model.SqlRunnerFailed;

public class ViewFailedSQLTableContentProvider implements IStructuredContentProvider {
	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@SuppressWarnings("all")
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof List) {
			List<SqlRunnerFailed> list = (List<SqlRunnerFailed>) inputElement;
			SqlRunnerFailed[] nodeArr = new SqlRunnerFailed[list.size()];
			return list.toArray(nodeArr);
		}

		return new Object[] {};
	}
}
