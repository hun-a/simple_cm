package com.cubrid.common.core.util;

import java.util.Locale;

public final class PartitionUtil {
	private static final String[] SUPPORTED_DATATYPES = new String[] { "CHAR", "VARCHAR", "VARYING", "INTEGER",
			"SMALLINT", "DATE", "TIMESTAMP", "TIME", "STRING" };

	private PartitionUtil() {
	}

	/**
	 * Get supported data type
	 *
	 * @return The string array
	 */
	public static String[] getSupportedDateTypes() {
		return SUPPORTED_DATATYPES.clone();
	}

	/**
	 * Check whether match the type
	 *
	 * @param dataType String
	 * @return boolean
	 */
	public static boolean isMatchType(String dataType) {
		if (dataType == null || dataType.length() == 0) {
			return false;
		}

		String type = dataType.toUpperCase(Locale.getDefault());
		for (int i = 0; i < SUPPORTED_DATATYPES.length; i++) {
			if (type.startsWith(SUPPORTED_DATATYPES[i])) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Get match data type
	 *
	 * @param dataType The string
	 * @return the String
	 */
	public static String getMatchType(String dataType) {
		if (dataType == null || dataType.length() == 0) {
			return null;
		}

		String type = dataType.toUpperCase(Locale.getDefault());
		if (type.startsWith("CHARACTER VARYING")
				|| type.startsWith("CHAR VARYING")
				|| type.startsWith("VARYING")) {
			return "VARCHAR";
		}

		for (int i = 0; i < SUPPORTED_DATATYPES.length; i++) {
			if (type.startsWith(SUPPORTED_DATATYPES[i])) {
				return SUPPORTED_DATATYPES[i];
			}
		}

		return null;
	}

	/**
	 * Check expression data type whether need add quote for expression value
	 *
	 * @param dataType The string
	 * @return boolean
	 */
	public static boolean isUsingQuoteForExprValue(String dataType) {
		if (dataType == null || dataType.length() == 0) {
			return true;
		}

		String[] usingQuoteDateType = {"CHAR", "VARCHAR", "VARYING", "DATE",
				"TIME", "TIMESTAMP", "STRING" };
		String type = dataType.toUpperCase(Locale.getDefault());
		for (int i = 0; i < usingQuoteDateType.length; i++) {
			if (type.startsWith(usingQuoteDateType[i])) {
				return true;
			}
		}

		return false;
	}
}
