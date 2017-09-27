package com.cubrid.common.ui.spi;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class TableViewerSorter extends
		ViewerSorter {
	protected static final int ASCENDING = 0;
	protected static final int DESCENDING = 1;

	protected int column;
	protected int direction;

	private HashMap<Integer, Comparator<Object>> columnComparators;

	public TableViewerSorter(HashMap<Integer, Comparator<Object>> columnComparators){
		this.columnComparators = columnComparators;
	}

	public TableViewerSorter(){
		columnComparators = new HashMap<Integer, Comparator<Object>>();
	}

	/**
	 * Does the sort. If it's a different column from the previous sort, do an
	 * ascending sort. If it's the same column as the last sort, toggle the sort
	 * direction.
	 * 
	 * @param column the table column index
	 */
	public void doSort(int column) {
		if (column == this.column) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.column = column;
			direction = ASCENDING;
		}
	}

	/**
	 * Compares the object for sorting
	 * 
	 * @param viewer the Viewer object
	 * @param e1 the object
	 * @param e2 the object
	 * @return the compared value
	 */
	@SuppressWarnings("rawtypes")
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (!(e1 instanceof Map) || !(e2 instanceof Map)) {
			return 0;
		}
		int rc = 0;
		Map map1 = (Map) e1;
		Map map2 = (Map) e2;
		Object obj1 = map1.get("" + column);
		Object obj2 = map2.get("" + column);
		Comparator<Object> comparator = columnComparators.get(column);

		if (comparator != null) {
			rc = comparator.compare (obj1, obj2);
		} else if (obj1 instanceof Number && obj2 instanceof Number) {
			Number num1 = (Number) obj1;
			Number num2 = (Number) obj2;
			if (num1.doubleValue() > num2.doubleValue()) {
				rc = 1;
			} else if (num1.doubleValue() < num2.doubleValue()) {
				rc = -1;
			} else {
				rc = 0;
			}
		} else if (obj1 instanceof String && obj2 instanceof String) {
			String str1 = (String) obj1;
			String str2 = (String) obj2;
			rc = str1.compareTo(str2);
		} else {
			return 0;
		}
		// If descending order, flip the direction
		if (direction == DESCENDING) {
			rc = -rc;
		}
		return rc;
	}

	public boolean isAsc() {
		return this.direction == ASCENDING;
	}

	/**
	 * 
	 * Set whether asc
	 * 
	 * @param isAsc boolean
	 */
	public void setAsc(boolean isAsc) {
		if (isAsc) {
			this.direction = ASCENDING;
		} else {
			this.direction = DESCENDING;
		}
	}

	public void setColumnComparator(Integer column, Comparator<Object> comparator){
		columnComparators.put(column, comparator);
	}
}
