package com.cubrid.cubridmanager.core.cubrid.table.model;

public class SchemaChangeLog {
	private String oldValue;
	private String newValue;
	private SchemeInnerType type;

	/**
	 * This enum indicates the four Scheme types
	 * 
	 * @author sq
	 * @version 1.0 - 2009-12-29 created by sq
	 */
	public enum SchemeInnerType {
		TYPE_SCHEMA("schema"), TYPE_ATTRIBUTE("attribute"), TYPE_CLASSATTRIBUTE(
				"classattribute"), TYPE_POSITION("position_change"), TYPE_FK(
				"fk"), TYPE_INDEX("index"), // including index,reverse
											// index,unique, reverse unique
											// index
		TYPE_TABLE_NAME("tablename"), TYPE_OWNER("owner"), TYPE_SUPER_TABLE(
				"supertablename"), TYPE_PARTITION("partition");

		String text = null;

		SchemeInnerType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

		/**
		 * Get the exist Scheme type
		 * 
		 * @param text String the given type indicates a kind of Scheme type
		 * @return SchemeInnerType the instance of SchemeInnerType
		 */
		public static SchemeInnerType eval(String text) {
			SchemeInnerType[] array = SchemeInnerType.values();
			for (SchemeInnerType a : array) {
				if (a.getText().equals(text)) {
					return a;
				}
			}
			return null;
		}
	};

	public SchemaChangeLog(String oldValue, String newValue,
			SchemeInnerType type) {
		super();
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.type = type;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public SchemeInnerType getType() {
		return type;
	}

	public void setType(SchemeInnerType type) {
		this.type = type;
	}

}
