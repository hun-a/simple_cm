package com.cubrid.cubridmanager.core.common.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PropertyChangeProvider {

	PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	/**
	 * Add a PropertyChangeListener to the listener list.
	 * 
	 * @param listener PropertyChangeListener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}

	/**
	 * Report a bound property update to any registered listeners. No event is
	 * fired if old and new are equal and non-null.
	 * 
	 * @param prop String
	 * @param oldValue Object
	 * @param newValue Object
	 */
	protected void firePropertyChange(String prop, Object oldValue,
			Object newValue) {
		listeners.firePropertyChange(prop, oldValue, newValue);
	}

	/**
	 * Report a bound property update to any registered listeners. No event is
	 * fired if old and new are equal and non-null.
	 * 
	 * @param prop String
	 * @param child Object
	 */
	protected void fireStructureChange(String prop, Object child) {
		listeners.firePropertyChange(prop, null, child);
	}

	/**
	 * Remove a PropertyChangeListener from the listener list.
	 * 
	 * @param listener PropertyChangeListener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

}