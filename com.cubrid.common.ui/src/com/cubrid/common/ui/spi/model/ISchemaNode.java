package com.cubrid.common.ui.spi.model;

public interface ISchemaNode extends ICubridNode {

	/**
	 * 
	 * Get CUBRID Database node
	 * 
	 * @return the CubridDatabase object
	 */
	public CubridDatabase getDatabase();

	/**
	 * 
	 * Set CUBRID database node
	 * 
	 * @param database the CubridDatabase object
	 */
	public void setDatabase(CubridDatabase database);

}
