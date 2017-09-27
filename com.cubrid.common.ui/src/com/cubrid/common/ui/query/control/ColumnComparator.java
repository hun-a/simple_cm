package com.cubrid.common.ui.query.control;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

import com.cubrid.common.ui.spi.table.CellValue;
import com.cubrid.common.ui.spi.util.FieldHandlerUtils;

@SuppressWarnings("rawtypes")
public class ColumnComparator implements Comparator, Serializable { // FIXME reuse

	private static final long serialVersionUID = 6519024461474907900L;
	private final String columnIndex;
	private final String columnType;
	private boolean isAsc = true;

	/**
	 *
	 * @param columnIndex
	 * @param columnType
	 * @param isAsc
	 */
	public ColumnComparator(String columnIndex, String columnType, boolean isAsc) {
		this.columnIndex = columnIndex;
		this.columnType = columnType.trim().toUpperCase();
		this.isAsc = isAsc;
	}

	/**
	 * set the isAsc
	 *
	 * @param isAsc boolean
	 */
	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	/**
	 *
	 * @return boolean
	 */
	public boolean isAsc() {
		return this.isAsc;
	}

	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * @param o1 the first object to be compared.
	 * @param o2 the second object to be compared.
	 * @return a negative integer, zero, or a positive integer as the first
	 *         argument is less than, equal to, or greater than the second.
	 */
	public int compare(Object o1, Object o2) {
		CellValue value1 = null, value2 = null;
		Map map1 = (Map) o1;
		Map map2 = (Map) o2;
		if (map1 != null) {
			value1 = (CellValue) map1.get(columnIndex);
		}

		if (map2 != null) {
			value2 = (CellValue) map2.get(columnIndex);
		}
		String str1 = getStrValue(value1);
		String str2 = getStrValue(value2);

		return FieldHandlerUtils.comparedDBValues(columnType, str1, str2, isAsc);
	}

	private String getStrValue(CellValue value) {
		if (value == null || value.getStringValue() == null) {
			return "";
		}

		return value.getStringValue();
	}
}
