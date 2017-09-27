package com.cubrid.common.core.common.model;

import static com.cubrid.common.core.util.NoOp.noOp;

import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;

public class DBResolution implements Cloneable {
	private static final Logger LOGGER = LogUtil.getLogger(DBResolution.class);
	private String name;
	private String className;
	private String alias;
	private boolean isClassResolution;

	public DBResolution(String name, String className, String alias) {
		this.name = name;
		this.className = className;
		this.alias = alias;
	}

	/**
	 * Override the method of Object
	 *
	 * @return DBResolution a clone of this instance.
	 */
	@Override
	public DBResolution clone() {
		try {
			return (DBResolution) super.clone();
		} catch (CloneNotSupportedException e) {
			LOGGER.debug(e.getMessage(), e);
		}
		return null;
	}

	public DBResolution() {
		noOp();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Get the alias
	 *
	 * @return String The alias
	 */
	public String getAlias() {
		if (alias == null) {
			return "";
		}
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 *@return int a hash code value for this object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * @param obj Object the reference object with which to compare.
	 * @return true if this object is the same as the obj argument; false
	 *         otherwise.
	 */
	@Override
	public boolean equals(Object obj) { // FIXME more simplify
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DBResolution other = (DBResolution) obj;
		if (className == null) {
			if (other.className != null) {
				return false;
			}
		} else if (!className.equals(other.className)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public boolean isClassResolution() {
		return isClassResolution;
	}

	public void setClassResolution(boolean isClassResolution) {
		this.isClassResolution = isClassResolution;
	}
}
