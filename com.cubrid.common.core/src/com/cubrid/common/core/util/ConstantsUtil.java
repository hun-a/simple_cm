package com.cubrid.common.core.util;

public class ConstantsUtil {
	public static final String SCHEMA_DESCRIPTION_TABLE = "_cub_schema_comments";
	public static final String CUNITOR_HA_TABLE = "_cunitor_ha_delay";

	/**
	 * Judge the table is extensional system table
	 *
	 * @param tableName
	 * @return
	 */
	public static boolean isExtensionalSystemTable(String tableName) {
		return StringUtil.isEqual(tableName, SCHEMA_DESCRIPTION_TABLE)
				|| StringUtil.isEqual(tableName, CUNITOR_HA_TABLE);
	}
}
