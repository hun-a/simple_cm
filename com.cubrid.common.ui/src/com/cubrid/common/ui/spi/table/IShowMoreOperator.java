package com.cubrid.common.ui.spi.table;

public interface IShowMoreOperator {
	public void handleButtonEvent(int rowIndex, int columnIndex);

	public boolean isShowButton(int rowIndex, int columnIndex);
}
