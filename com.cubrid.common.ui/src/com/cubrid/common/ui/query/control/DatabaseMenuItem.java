package com.cubrid.common.ui.query.control;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.cubrid.common.ui.spi.model.CubridDatabase;

public class DatabaseMenuItem extends
MenuItem {

	String id = null;
	CubridDatabase database = null;
	String groupName = null;

	public DatabaseMenuItem(String id, Menu parent, int style) {
		super(parent, style);
		this.id = id;
	}

	public CubridDatabase getDatabase() {
		return database;
	}

	public void setDatabase(CubridDatabase database) {
		this.database = database;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @see org.eclipse.swt.widgets.MenuItem#checkSubclass()
	 */
	protected void checkSubclass() {
		// do nothing
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @see org.eclipse.swt.widgets.Widget#toString()
	 * @return a string representation of the receiver
	 */
	public String toString() {
		return id;
	}

}
