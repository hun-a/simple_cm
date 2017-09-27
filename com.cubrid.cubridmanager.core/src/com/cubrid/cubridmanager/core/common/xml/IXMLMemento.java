package com.cubrid.cubridmanager.core.common.xml;

import java.util.List;

public interface IXMLMemento {

	/**
	 * Return the created child with the given node name
	 * 
	 * @param name the node name
	 * @return the created child with the given node name
	 */
	public IXMLMemento createChild(String name);

	/**
	 * Return the first child with the given node name.
	 * 
	 * @param name the name
	 * @return the first child with the node name
	 */
	public IXMLMemento getChild(String name);

	/**
	 * Return all children with the given node name
	 * 
	 * @param name the node name
	 * @return the list of children with the node name
	 */
	public IXMLMemento[] getChildren(String name);

	/**
	 * Return the floating point value of the given key.
	 * 
	 * @param key the key
	 * @return the value, or <code>null</code> if the key was not found or was
	 *         found but was not a floating point number
	 */
	public Float getFloat(String key);

	/**
	 * Return the integer value of the given key.
	 * 
	 * @param key the key
	 * @return the value, or <code>null</code> if the key was not found or was
	 *         found but was not an integer
	 */
	public Integer getInteger(String key);

	/**
	 * Return the string value of the given key.
	 * 
	 * @param key the key
	 * @return the value, or <code>null</code> if the key was not found or was
	 *         found but was not an integer
	 */
	public String getString(String key);

	/**
	 * Return the boolean value of the given key.
	 * 
	 * @param key the key
	 * @return the value, or <code>null</code> if the key was not found or was
	 *         found but was not a boolean
	 */
	public Boolean getBoolean(String key);

	/**
	 * Return the text node data of this element
	 * 
	 * @return the node content
	 */
	public String getTextData();

	/**
	 * return all attribute name of this xml memento object
	 * 
	 * @return all attribute name of this xml memento object
	 */
	public List<String> getAttributeNames();

	/**
	 * Set the value of the given key to the given integer.
	 * 
	 * @param key the key
	 * @param value the value
	 */
	public void putInteger(String key, int value);

	/**
	 * Set the value of the given key to the given boolean value.
	 * 
	 * @param key the key
	 * @param value the value
	 */
	public void putBoolean(String key, boolean value);

	/**
	 * Set the value of the given key to the given string.
	 * 
	 * @param key the key
	 * @param value the value
	 */
	public void putString(String key, String value);

	/**
	 * Set the str to the text node of this element
	 * 
	 * @param str the node content
	 */
	public void putTextData(String str);
}