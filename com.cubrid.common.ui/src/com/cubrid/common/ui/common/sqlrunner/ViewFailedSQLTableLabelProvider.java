package com.cubrid.common.ui.common.sqlrunner;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.cubrid.common.ui.common.sqlrunner.model.SqlRunnerFailed;

public class ViewFailedSQLTableLabelProvider extends LabelProvider
		implements ITableLabelProvider {
	/**
	 * Retrieves the column's text by column index
	 * 
	 * @param element to be displayed.
	 * @param columnIndex is the index of column. Begin with 0.
	 * @return String to be filled in the column.
	 */
	public String getColumnText(Object element, int columnIndex) {
		SqlRunnerFailed p = (SqlRunnerFailed) element;
		switch (columnIndex) {
		case 0:
			return Long.toString(p.getLineIndex());
		case 1:
			return p.getSql();
		case 2:
			return p.getErrorMessage();
		default:
			return null;
		}
	}

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
}
