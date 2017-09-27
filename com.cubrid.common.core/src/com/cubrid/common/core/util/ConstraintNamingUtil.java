package com.cubrid.common.core.util;

import java.util.List;

public final class ConstraintNamingUtil {
	private ConstraintNamingUtil() {
	}

	/**
	 * default name for reverse unique index
	 * 
	 * @param tableName the given table name
	 * @param attrList List<String> the given list that includes the attributes
	 * @return String a string that indicates reverse unique name
	 */
	public static String getReverseUniqueName(String tableName, List<String> attrList) {
		assert (!attrList.isEmpty());
		StringBuilder bf = new StringBuilder();
		bf.append("ru_").append(tableName);
		for (String attr : attrList) {
			bf.append("_").append(attr);
		}
		
		return bf.toString();
	}

	/**
	 * default name for unique index
	 * 
	 * @param tableName String the given table name
	 * @param ruleList List<String> the given list that includes the info of
	 *        rules
	 * @return String a string that indicates the unique name
	 */
	public static String getUniqueName(String tableName, List<String> ruleList) {
		assert (!ruleList.isEmpty());
		StringBuilder bf = new StringBuilder();
		bf.append("u_").append(tableName);
		for (String rule : ruleList) {
			String[] strs = rule.split(" ");
			bf.append("_").append(strs[0]);
			if (strs[1].equalsIgnoreCase("DESC")) {
				bf.append("_d");
			}
		}
		
		return bf.toString();
	}

	/**
	 * default name for PK
	 * 
	 * @param tableName String the given table name
	 * @param attrList List<String> the given list that includes the info of
	 *        attributes
	 * @return String a string that indicates the pk name
	 */
	public static String getPKName(String tableName, List<String> attrList) {
		assert (!attrList.isEmpty());
		StringBuilder bf = new StringBuilder();
		bf.append("pk_").append(tableName);
		for (String attr : attrList) {
			bf.append("_").append(attr);
		}
		
		return bf.toString();
	}

	/**
	 * default name for FK
	 * 
	 * @param tableName String the given table name
	 * @param attrList List<String> the given list that includes the info of
	 *        attributes
	 * @return String a string that indicates the foreign key name
	 */
	public static String getFKName(String tableName, List<String> attrList) {
		assert (!attrList.isEmpty());
		StringBuilder bf = new StringBuilder();
		bf.append("fk_").append(tableName);
		for (String attr : attrList) {
			bf.append("_").append(attr);
		}
		
		return bf.toString();
	}

	/**
	 * default name for index
	 * 
	 * @param tableName String the given table name
	 * @param ruleList List<String> the given list that includes the rules
	 * @return String a string that indicates the index name
	 */
	public static String getIndexName(String tableName, List<String> ruleList) {
		assert (!ruleList.isEmpty());
		StringBuilder bf = new StringBuilder();
		bf.append("i_").append(tableName);
		for (String rule : ruleList) {
			String[] strs = rule.split(" ");
			String str = strs[0];
			if (str.indexOf("(") != -1 && str.indexOf(")") != -1) {
				str = str.substring(0, str.indexOf("("));
			}
			bf.append("_").append(str);
			// TODO if strs.length == 1 ?
			if (strs.length > 1 && strs[1].equalsIgnoreCase("DESC")) {
				bf.append("_d");
			}
		}
		
		return bf.toString();
	}

	/**
	 * default name for reverse index
	 * 
	 * @param tableName String the given table name
	 * @param attrList List<String> the given list that includes the info of
	 *        attributes
	 * @return String a string that indicates the info reverse index name
	 */
	public static String getReverseIndexName(String tableName, List<String> attrList) {
		assert (!attrList.isEmpty());
		StringBuilder bf = new StringBuilder();
		bf.append("ri_").append(tableName);
		for (String attr : attrList) {
			bf.append("_").append(attr);
		}
		
		return bf.toString();
	}
}
