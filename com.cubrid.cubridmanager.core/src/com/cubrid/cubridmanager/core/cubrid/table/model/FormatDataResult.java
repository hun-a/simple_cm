package com.cubrid.cubridmanager.core.cubrid.table.model;

public class FormatDataResult {

	String formatedString;
	Object formatedJavaObj;
	boolean success;

	public String getFormatResult() {
		return formatedString;
	}

	public void setFormatResult(String formatResult) {
		this.formatedString = formatResult;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getFormatedString() {
		return formatedString;
	}

	public void setFormatedString(String formatedString) {
		this.formatedString = formatedString;
	}

	public Object getFormatedJavaObj() {
		return formatedJavaObj;
	}

	public void setFormatedJavaObj(Object formatedRealObj) {
		this.formatedJavaObj = formatedRealObj;
	}

}
