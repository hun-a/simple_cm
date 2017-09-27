package com.cubrid.common.ui.query.control.queryplan;

public enum PLAN_DISPLAY_MODE { // rename as java standard
	TEXT(0), TREE(1), GRAPH(2);

	private int value;

	PLAN_DISPLAY_MODE(int i) {
		value = i;
	}

	public int getInt() {
		return value;
	}
}