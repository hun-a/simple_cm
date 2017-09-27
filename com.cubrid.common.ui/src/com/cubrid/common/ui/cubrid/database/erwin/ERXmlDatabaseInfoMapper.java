package com.cubrid.common.ui.cubrid.database.erwin;

import java.util.HashMap;
import java.util.Map;

import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;

public class ERXmlDatabaseInfoMapper {
	private static final Map<DatabaseInfo, WrappedDatabaseInfo> mapper = new HashMap<DatabaseInfo, WrappedDatabaseInfo>();

	public static WrappedDatabaseInfo getWrappedDatabaseInfo(DatabaseInfo info) {
		return mapper.get(info);
	}

	public static void addWrappedDatabaseInfo(DatabaseInfo info, WrappedDatabaseInfo wInfo) {
		mapper.put(info, wInfo);
	}
}
