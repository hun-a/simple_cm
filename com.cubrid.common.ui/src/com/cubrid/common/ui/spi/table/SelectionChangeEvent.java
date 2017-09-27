package com.cubrid.common.ui.spi.table;

import org.eclipse.swt.graphics.Point;

public class SelectionChangeEvent {
	public Point[] selectedArray = {};
	
	public SelectionChangeEvent(Point[] selectedArray) {
		this.selectedArray = selectedArray;
	}
}
