package com.cubrid.common.core;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class CubridCommonCorePlugin extends Plugin {
	public static final String PLUGIN_ID = "com.cubrid.common.core";
	private static CubridCommonCorePlugin plugin;

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static CubridCommonCorePlugin getDefault() {
		return plugin;
	}
}
