package com.cubrid.cubridmanager.core.cubrid.table.model;

import java.util.ArrayList;
import java.util.List;

public class SuperClass {
	String name;
	List<String> classAttributes = null; // String
	List<String> attributes = null;
	List<String> classMethods = null;
	List<String> methods = null;

	/**
	 * add an attribute to list
	 * 
	 * @param attribute String the given attribute
	 */
	public void addAttribute(String attribute) {
		if (null == attributes) {
			attributes = new ArrayList<String>();
		}
		attributes.add(attribute);
	}

	/**
	 * add a class attribute to list
	 * 
	 * @param classAttribute String the given class attribute
	 */
	public void addClassAttribute(String classAttribute) {
		if (null == classAttributes) {
			classAttributes = new ArrayList<String>();
		}
		classAttributes.add(classAttribute);
	}

	/**
	 * add a class method to list
	 * 
	 * @param classMethod String the given class method
	 */
	public void addClassMethod(String classMethod) {
		if (null == classMethods) {
			classMethods = new ArrayList<String>();
		}
		classMethods.add(classMethod);
	}

	/**
	 * add a method to list
	 * 
	 * @param method String the given method name
	 */
	public void addMethod(String method) {
		if (null == methods) {
			methods = new ArrayList<String>();
		}
		methods.add(method);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getClassAttributes() {
		return classAttributes;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public List<String> getClassMethods() {
		return classMethods;
	}

	public List<String> getMethods() {
		return methods;
	}
}
