package com.cubrid.common.ui.query.sqlmap;

public class BindParameter {

	private String name;
	private String value;
	private BindParameterType type;

	public BindParameter(String name, String value, BindParameterType type) {
		setName(name);
		setValue(value);
		setType(type);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BindParameterType getType() {
		return type;
	}

	public void setType(BindParameterType type) {
		this.type = type;
	}

	public enum BindParameterType {
		NUMBER("N") {
			public String wrap(String string) {
				return string;
			}
		},
		STRING("S") {
			public String wrap(String string) {
				return "'" + string + "'";
			}
		},
		FUNCTION("F") {
			public String wrap(String string) {
				return string;
			}
		};

		public String wrap(String string) {
			return string;
		}

		String type;

		BindParameterType(String type) {
			this.type = type;
		}
	}

}
