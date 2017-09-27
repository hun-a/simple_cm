package com.cubrid.cubridmanager.core.cubrid.table.model;

public class DbAuth {
	private String grantorName;
	private String granteeName;
	private String className;
	private String authType;
	private boolean isGrantable;

	/**
	 * Get the grantorName
	 * 
	 * @return the grantorName
	 */
	public String getGrantorName() {
		return grantorName;
	}

	/**
	 * @param grantorName the grantorName to set
	 */
	public void setGrantorName(String grantorName) {
		this.grantorName = grantorName;
	}

	/**
	 * Get the granteeName
	 * 
	 * @return the granteeName
	 */
	public String getGranteeName() {
		return granteeName;
	}

	/**
	 * @param granteeName the granteeName to set
	 */
	public void setGranteeName(String granteeName) {
		this.granteeName = granteeName;
	}

	/**
	 * Get the className
	 * 
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Get the authType
	 * 
	 * @return the authType
	 */
	public String getAuthType() {
		return authType;
	}

	/**
	 * @param authType the authType to set
	 */
	public void setAuthType(String authType) {
		this.authType = authType;
	}

	/**
	 * Get the isGrantable
	 * 
	 * @return the isGrantable
	 */
	public boolean isGrantable() {
		return isGrantable;
	}

	/**
	 * @param isGrantable the isGrantable to set
	 */
	public void setGrantable(String isGrantable) {
		if (isGrantable.equalsIgnoreCase("YES")) {
			this.isGrantable = true;
		} else {
			this.isGrantable = false;
		}
	}

}
