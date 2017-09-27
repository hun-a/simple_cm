package com.cubrid.common.ui.spi.model;

public class DefaultSchemaNode extends
		DefaultCubridNode implements ISchemaNode {

	private CubridDatabase cubridDatabase = null;

	/**
	 * The constructor
	 * 
	 * @param id
	 * @param label
	 * @param iconPath
	 */
	public DefaultSchemaNode(String id, String label, String iconPath) {
		super(id, label, iconPath);
	}

	/**
	 * 
	 * Get CUBRID Database node
	 * 
	 * @return the CubridDatabase object
	 */
	public CubridDatabase getDatabase() {
		return cubridDatabase;
	}

	/**
	 * 
	 * Set CUBRID database node
	 * 
	 * @param database the CubridDatabase object
	 */
	public void setDatabase(CubridDatabase database) {
		this.cubridDatabase = database;
	}

	/**
	 * Add child object to this node
	 * 
	 * @param obj the ICubridNode object
	 */
	public void addChild(ICubridNode obj) {
		if (obj instanceof ISchemaNode && !isContained(obj)) {
			ISchemaNode schemaNode = (ISchemaNode) obj;
			schemaNode.setDatabase(this.getDatabase());
		}
		super.addChild(obj);
	}

	/**
	 * Compare the object for sorter in the same tree level
	 * 
	 * @param obj the ICubridNode object
	 * @return <code>1<code> greater;<code>0</code>equal;<code>-1</code> less
	 */
	public int compareTo(ISchemaNode obj) {
		if (obj == null) {
			return 1;
		}
		//sort for tables, SYSTEM_TABLE_FOLDER and SYSTEM_VIEW_FOLDER is the first
		if (NodeType.SYSTEM_TABLE_FOLDER.equals(getType())
				|| NodeType.SYSTEM_VIEW_FOLDER.equals(getType())) {
			return -1;
		} else if (NodeType.SYSTEM_TABLE_FOLDER.equals(obj.getType())
				|| NodeType.SYSTEM_VIEW_FOLDER.equals(obj.getType())) {
			return 1;
		}
		return super.compareTo(obj);
	}


	/**
	 * Return whether the current object is equal the obj
	 * 
	 * @param obj the object
	 * @return <code>true</code> if they are equal;<code>false</code> otherwise
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof DefaultSchemaNode)) {
			return false;
		}
		return super.equals(obj);
	}

	/**
	 * Return the hash code value
	 * 
	 * @return the hash code value
	 */
	public int hashCode() {
		return this.getId().hashCode();
	}

	public DefaultSchemaNode clone() throws CloneNotSupportedException{
		DefaultSchemaNode obj = (DefaultSchemaNode) super.clone();
		return obj;
	}


}
