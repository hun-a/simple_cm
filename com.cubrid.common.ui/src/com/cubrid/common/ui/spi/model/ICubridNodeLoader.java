package com.cubrid.common.ui.spi.model;

import org.eclipse.core.runtime.IProgressMonitor;

public interface ICubridNodeLoader {

	public static final int DEFINITE_LEVEL = Integer.MAX_VALUE;

	public static final int FIRST_LEVEL = 1;

	public static final int WAIT_TIME = 1000;

	public static final String NODE_SEPARATOR = "/";

	/**
	 * 
	 * Load children object for parent
	 * 
	 * @param parent the parent node
	 * @param monitor the IProgressMonitor object
	 */
	public void load(ICubridNode parent, IProgressMonitor monitor);

	/**
	 * Return whether it has been loaded
	 * 
	 * @return <code>true</code> if it is loaded;<code>false</code> otherwise
	 */
	public boolean isLoaded();

	/**
	 * 
	 * Set loaded status
	 * 
	 * @param isLoaded whether it is loaded
	 */
	public void setLoaded(boolean isLoaded);

	/**
	 * Set loaded level
	 * 
	 * @param level the loaded depth
	 */
	public void setLevel(int level);

	/**
	 * Get loaded level
	 * 
	 * @return the loaded depth
	 */
	public int getLevel();
}
