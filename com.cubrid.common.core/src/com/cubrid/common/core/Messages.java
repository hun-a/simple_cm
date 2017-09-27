package com.cubrid.common.core;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	static {
		NLS.initializeMessages(CubridCommonCorePlugin.PLUGIN_ID + ".Messages", Messages.class);
	}

	public static String keywordFilename;
	public static String sqlmapEmptyContent;
	public static String sqlmapInvalidFormat;
	public static String sqlmapNoMybatisFormat;
}