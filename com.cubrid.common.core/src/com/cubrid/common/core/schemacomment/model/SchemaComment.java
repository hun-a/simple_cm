package com.cubrid.common.core.schemacomment.model;

public class SchemaComment { // FIXME add description
	private String table;
	private String column;
	private String description;
	private String objectName;
	private CommentType type;

	public String getId() {
		if (table == null && column == null && type == null) {
			return null;
		}

		if (type == null) {
			if (column == null) {
				return table + "*";
			} else {
				return table + "*" + column;
			}
		}

		return type.getName() + "*" + objectName;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isTable() {
		return table != null && column == null;
	}

	public boolean isColumn() {
		return table != null && column != null;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public void setType(CommentType type) {
		this.type = type;
	}

	public CommentType getType() {
		return type;
	}
}
