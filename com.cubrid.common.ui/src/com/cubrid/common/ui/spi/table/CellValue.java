package com.cubrid.common.ui.spi.table;

import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class CellValue implements Serializable {
	private static final long serialVersionUID = 6256628990873561491L;
	private static final String KEY_FILE_CHARSET = "file_charset";
	private final Map<String, Object> properties = new HashMap<String, Object>();
	private String showValue;
	private Object value;
	private boolean hasLoadAll = true;

	public CellValue() {
	}

	public CellValue(Object value) {
		this(value, null);
	}

	public CellValue(Object value, String showValue) {
		this.value = value;
		this.showValue = showValue;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public Object getProperty(String name) {
		return properties.get(name);
	}

	public void putProperty(String name, Object obj) {
		properties.put(name, obj);
	}

	public String getFileCharset() {
		return (String) getProperty(KEY_FILE_CHARSET);
	}

	public void setFileCharset(String charset) {
		putProperty(KEY_FILE_CHARSET, charset);
	}

	public String getShowValue() {
		return showValue;
	}

	public void setShowValue(String showValue) {
		this.showValue = showValue;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getStringValue() {
		if (value == null) {
			return null;
		}

		if (value instanceof String) {
			return (String) value;
		}

		if (value instanceof byte[]) {
			return new String((byte[]) value);
		}

		return value.toString();
	}

	public boolean hasLoadAll() {
		return hasLoadAll;
	}

	public void setHasLoadAll(boolean hasLoadAll) {
		this.hasLoadAll = hasLoadAll;
	}

	/**
	 * Return whether equal
	 *
	 * @param Object
	 * @return boolean
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CellValue)) {
			return false;
		}
		Object cellValue = ((CellValue) object).getValue();
		if (cellValue == null && value == null) {
			return true;
		} else if (cellValue != null && value != null) {
			return cellValue.equals(value);
		}
		return false;
	}

	public CellValue clone() {
		try {
			return (CellValue) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public int hashCode() {
		return super.hashCode();
	}
}
