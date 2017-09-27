package com.cubrid.cubridmanager.core.logs.model;

public class LogInfo {

	private String path = null;
	private String type = null;
	private String owner = null;
	private String size = null;
	private String lastupdate = null;
	private String filename = null;

	/**
	 * get the path.
	 * 
	 * @return String
	 */
	public String getPath() {
		return path;
	}

	/**
	 * set the path.
	 * 
	 * @param path String
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * get the owner.
	 * 
	 * @return String
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * set the owner.
	 * 
	 * @param owner String
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * get the size.
	 * 
	 * @return String
	 */
	public String getSize() {
		return size;
	}

	/**
	 * set the size.
	 * 
	 * @param size String
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * get the lastupdate.
	 * 
	 * @return String
	 */
	public String getLastupdate() {
		return lastupdate;
	}

	/**
	 * set the lastupdate.
	 * 
	 * @param lastupdate String
	 */
	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	/**
	 * get the type.
	 * 
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * set the type.
	 * 
	 * @param type String
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * get the name of file.
	 * 
	 * @return String
	 */
	public String getName() {
		if (path != null && path.lastIndexOf("/") > 0) {
			return path.substring(path.lastIndexOf("/") + 1);
		}
		return "";
	}

	/**
	 * get the filename.
	 * 
	 * @return String
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * set the filename.
	 * 
	 * @param filename String
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
