package com.cubrid.cubridmanager.core;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class CubridManagerCorePlugin extends
		Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.cubrid.cubridmanager.core";

	// The shared instance
	private static CubridManagerCorePlugin plugin;

	/**
	 * @param context BundleContext
	 * @throws Exception exception
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/**
	 * @param context BundleContext
	 * @throws Exception exception
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static CubridManagerCorePlugin getDefault() {
		return plugin;
	}

}
