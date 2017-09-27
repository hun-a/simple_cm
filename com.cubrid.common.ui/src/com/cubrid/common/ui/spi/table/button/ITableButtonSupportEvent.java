package com.cubrid.common.ui.spi.table.button;

import org.eclipse.swt.widgets.Table;

public interface ITableButtonSupportEvent {
	public void showEditDialog(Table table, int index);
}
