package com.cubrid.common.core.common.model;

import java.util.ArrayList;
import java.util.List;

public class DBMethod {
	// method name, which could be used in sQL/X
	String name;
	// the class(table) on which the method is defined
	String inherit;
	// the argument
	List<String> arguments = null;
	// function name, which is defined in a object file
	String function;

	/**
	 * add an argument to list
	 *
	 * @param argument String The given argument
	 */
	public void addArgument(String argument) {
		if (null == arguments) {
			arguments = new ArrayList<String>();
		}
		arguments.add(argument);
	}

	/**
	 * get the method name
	 *
	 * @return String The method name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the method name
	 *
	 * @param name The method name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get the class name which the method operator
	 *
	 * @return String The class name
	 */
	public String getInherit() {
		return inherit;
	}

	/**
	 * set the class name which the method operator
	 *
	 * @param inherit String The class name
	 */
	public void setInherit(String inherit) {
		this.inherit = inherit;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public List<String> getArguments() {
		return arguments;
	}
}
