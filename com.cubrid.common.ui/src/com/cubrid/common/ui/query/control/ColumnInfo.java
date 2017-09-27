package com.cubrid.common.ui.query.control;

import com.cubrid.common.ui.spi.util.FieldHandlerUtils;

public class ColumnInfo implements Cloneable {

	private String index;
	private String name;
	private String type;
	private int precision;
	private int scale;
	private String childElementType;

	public ColumnInfo(String index, String name, String type,
			String childElementType, int precision, int scale) {
		super();
		this.index = index;
		this.name = name;
		this.type = type;
		this.childElementType = childElementType;
		this.precision = precision;
		this.scale = scale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getChildElementType() {
		return childElementType;
	}

	public void setChildElementType(String childElementType) {
		this.childElementType = childElementType;
	}

	/**
	 *
	 * Get complete type
	 *
	 * @return String
	 */
	public String getComleteType() { // FIXME move this logic to core module
		String res = FieldHandlerUtils.getComleteType(type, childElementType,
				precision, scale);
		return res == null ? "" : res;

	}

	public String getShortType() { // FIXME move this logic to core module
		String res = FieldHandlerUtils.getComleteType(type, childElementType,
				precision, scale, true);
		return res == null ? "" : res;
	}

	/**
	 * Clone a object
	 *
	 * @return ColumnInfo
	 */
	public ColumnInfo clone() {
		ColumnInfo columnInfo = null;
		try {
			columnInfo = (ColumnInfo) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return columnInfo;
	}
}
