package com.cubrid.common.core.util;

public class ApplicationUtil {

	public static ApplicationType applicationType = ApplicationType.NONE;
	public static final String CMT_PLUGIN_ID = "com.cubrid.cubridmigration.plugin";
	public static final String CM_PLUGIN_ID = "org.cubrid.cubridmanager.plugin.manager";
	public static final String CQB_PLUGIN_ID = "org.cubrid.cubridquery.plugin.querybrowser";
	public static final String CQB_UI_PLUGIN_ID = "com.cubrid.cubridquery.ui";
	public static final String CM_UI_PLUGIN_ID = "com.cubrid.cubridmanager.ui";
	
	public static ApplicationType getApplicationType() {
		return ApplicationUtil.applicationType;
	}

	public static void setApplicationType(ApplicationType applicationType) {
		ApplicationUtil.applicationType = applicationType;
	}

}
