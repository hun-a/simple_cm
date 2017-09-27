package com.cubrid.cubridmanager.core.cubrid.table.model;

import com.cubrid.common.core.common.model.DBAttribute;

public class DBAttributeStatistic extends
		DBAttribute {

	private String minValue = null;
	private String maxValue = null;
	private int valueDistinctCount = 0;

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public int getValueDistinctCount() {
		return valueDistinctCount;
	}

	public void setValueDistinctCount(int valueDistinctCount) {
		this.valueDistinctCount = valueDistinctCount;
	}

	/**
	 * Override the equals method of Object
	 * 
	 * @param obj Object the reference object with which to compare.
	 * @return boolean true if this object is the same as the obj argument;
	 *         false otherwise.
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DBAttributeStatistic)) {
			return false;
		}
		if (!super.equals(obj)) {
			return false;
		}
		DBAttributeStatistic dbAttribute = (DBAttributeStatistic) obj;
		boolean equal = dbAttribute.minValue == null ? this.minValue == null
				: dbAttribute.minValue.equals(this.minValue);
		equal = equal
				&& (dbAttribute.maxValue == null ? this.maxValue == null
				: dbAttribute.maxValue.equals(this.maxValue));
		equal = equal
				&& dbAttribute.valueDistinctCount == this.valueDistinctCount;
		return equal;
	}

	/**
	 * @return int a hash code value for this object
	 */
	public int hashCode() {
		int resultMin = minValue == null ? 0 : minValue.hashCode();
		int resultMax = maxValue == null ? 0 : maxValue.hashCode();
		return 29 * resultMin + 31 * resultMax;
	}
}
