package com.cubrid.cubridmanager.core.cubrid.table.model;

import java.util.ArrayList;
import java.util.List;

public class ClassList {

	private List<ClassItem> classList = null;

	/**
	 * add a class item to a class list
	 * 
	 * @param item ClassItem The instance of ClassItem
	 */
	public void addClass(ClassItem item) {
		synchronized (this) {
			if (null == classList) {
				classList = new ArrayList<ClassItem>();
			}
			if (!classList.contains(item)) {
				classList.add(item);
			}
		}
	}

	/**
	 * Remove the given the instance of ClassItem
	 * 
	 * @param item ClassItem The given instance of ClassItem
	 */
	public void removeClass(ClassItem item) {
		synchronized (this) {
			if (null != classList) {
				classList.remove(item);
			}
		}
	}

	/**
	 * Remove all the class from classList
	 * 
	 */
	public void removeAllClass() {
		synchronized (this) {
			if (null != classList) {
				classList.clear();
			}
		}
	}

	/**
	 * Get the classList
	 * 
	 * @return List<ClassItem> The list that includes the instances of ClassItem
	 */
	public List<ClassItem> getClassList() {
		synchronized (this) {
			if (classList == null) {
				return new ArrayList<ClassItem>();
			}
			return classList;
		}
	}
}