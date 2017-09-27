package com.cubrid.common.core.common.model;

import static com.cubrid.common.core.util.NoOp.noOp;

import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;

public class SerialInfo implements Cloneable {
	private static final Logger LOGGER = LogUtil.getLogger(SerialInfo.class);
	private String name;
	private String owner;
	private String currentValue;
	private String incrementValue;
	private String maxValue;
	private String minValue;
	private boolean isCyclic = false;
	private String startedValue;
	private String className;
	private String attName;
	private String cacheCount;
	private String description;

	/**
	 * The constructor
	 *
	 * @param name
	 * @param owner
	 * @param currentValue
	 * @param incrementValue
	 * @param maxValue
	 * @param minValue
	 * @param isCyclic
	 * @param startedValue
	 * @param cacheCount
	 * @param className
	 * @param attName
	 */
	public SerialInfo(String name, String owner, String currentValue,
			String incrementValue, String maxValue, String minValue,
			boolean isCyclic, String startedValue, String cacheCount,
			String className, String attName) {
		super();
		this.name = name;
		this.owner = owner;
		this.currentValue = currentValue;
		this.incrementValue = incrementValue;
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.isCyclic = isCyclic;
		this.startedValue = startedValue;
		this.cacheCount = cacheCount;
		this.className = className;
		this.attName = attName;
	}

	/**
	 * The constructor
	 */
	public SerialInfo() {
		noOp();
	}

	/**
	 *
	 * Get serial name
	 *
	 * @return String The serial name
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * Set serial name
	 *
	 * @param name String The given serial name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * Get serial owner
	 *
	 * @return String The serial owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 *
	 * Set serial owner
	 *
	 * @param owner String The given serial owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 *
	 * Get current value
	 *
	 * @return String The current value
	 */
	public String getCurrentValue() {
		return currentValue;
	}

	/**
	 *
	 * Set current value
	 *
	 * @param currentValue String The given serial current value
	 */
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}

	/**
	 *
	 * Get increment value
	 *
	 * @return String The increment value
	 */
	public String getIncrementValue() {
		return incrementValue;
	}

	/**
	 *
	 * Set increment value
	 *
	 * @param incrementValue String The given increment value
	 */
	public void setIncrementValue(String incrementValue) {
		this.incrementValue = incrementValue;
	}

	/**
	 *
	 * Get max value
	 *
	 * @return String The maximal value
	 */
	public String getMaxValue() {
		return maxValue;
	}

	/**
	 *
	 * Set max value
	 *
	 * @param maxValue String The given maximal value
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 *
	 * Get minimal value
	 *
	 * @return String The minimal value
	 */
	public String getMinValue() {
		return minValue;
	}

	/**
	 *
	 * Set minimal value
	 *
	 * @param minValue The given minimal value
	 */
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	/**
	 *
	 * Return whether it is cyclic
	 *
	 * @return boolean Whether is cyclic
	 */
	public boolean isCyclic() {
		return isCyclic;
	}

	/**
	 *
	 * Set whether it is cyclic
	 *
	 * @param isCycle boolean Whether is cyclic
	 */
	public void setCyclic(boolean isCycle) {
		this.isCyclic = isCycle;
	}

	/**
	 *
	 * Get started value
	 *
	 * @return String The started value
	 */
	public String getStartedValue() {
		return startedValue;
	}

	/**
	 *
	 * Set started value
	 *
	 * @param startedValue String The given started value
	 */
	public void setStartedValue(String startedValue) {
		this.startedValue = startedValue;
	}

	/**
	 *
	 * Get cache count
	 *
	 * @return String the cache count
	 */
	public String getCacheCount() {
		return cacheCount;
	}

	/**
	 *
	 * Set cache count
	 *
	 * @param cacheCount the string
	 */
	public void setCacheCount(String cacheCount) {
		this.cacheCount = cacheCount;
	}

	/**
	 *
	 * Get class name that the serial belong to
	 *
	 * @return String The class name
	 */
	public String getClassName() {
		return className;
	}

	/**
	 *
	 * Set class name that the serial belong to
	 *
	 * @param className String The given class name
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 *
	 * Get attribute name of class that the serial belong to
	 *
	 * @return String The attribute name
	 */
	public String getAttName() {
		return attName;
	}

	/**
	 *
	 * Set attribute name of class that the serial belong to
	 *
	 * @param attName The given attribute name
	 */
	public void setAttName(String attName) {
		this.attName = attName;
	}

	/**
	 * Override the equals method of Object
	 *
	 * @param obj Object The given comparator
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) { // FIXME reduce code lines
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SerialInfo)) {
			return false;
		}
		SerialInfo a = (SerialInfo) obj;
		boolean equal = a.name == null ? this.name == null
				: a.name.equals(this.name);
		equal = equal
				&& (a.owner == null ? this.owner == null
						: a.owner.equals(this.owner));

		equal = equal
				&& (a.currentValue == null ? this.currentValue == null
						: a.currentValue.equals(this.currentValue));
		equal = equal
				&& (a.incrementValue == null ? this.incrementValue == null
						: a.incrementValue.equals(this.incrementValue));

		equal = equal
				&& (a.maxValue == null ? this.maxValue == null
						: a.maxValue.equals(this.maxValue));
		equal = equal
				&& (a.minValue == null ? this.minValue == null
						: a.minValue.equals(this.minValue));

		equal = equal
				&& (a.startedValue == null ? this.startedValue == null
						: a.startedValue.equals(this.startedValue));

		equal = equal
				&& (a.cacheCount == null ? this.cacheCount == null
						: a.cacheCount.equals(this.cacheCount));

		equal = equal
				&& (a.className == null ? this.className == null
						: a.className.equals(this.className));

		equal = equal
				&& (a.attName == null ? this.attName == null
						: a.attName.equals(this.attName));
		equal = equal && (a.isCyclic == this.isCyclic);
		return equal;
	}

	/**
	 * Override the hashCode method of Object
	 *
	 * @return int
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	/**
	 * Override the clone method of Object
	 *
	 * @return SerialInfo
	 */
	@Override
	public SerialInfo clone() {
		try {
			return (SerialInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	public String getTableAutoIncrementString() { // FIXME add description
		return getMinValue() + "," + getIncrementValue();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
