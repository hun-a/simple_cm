package com.cubrid.common.ui;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.cubrid.common.ui.common.navigator.NodeAdapterFactory;
import com.cubrid.common.ui.spi.model.ICubridNode;

public class CommonUIPlugin extends
AbstractUIPlugin {
	public static final String PLUGIN_ID = "com.cubrid.common.ui";
	private static CommonUIPlugin plugin;

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		Platform.getAdapterManager().registerAdapters(new NodeAdapterFactory(), ICubridNode.class);
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static CommonUIPlugin getDefault() {
		return plugin;
	}

	public static ImageDescriptor getImageDescriptor(String path) {
		ImageDescriptor imageDesc = getDefault().getImageRegistry().getDescriptor(path);
		if (imageDesc == null) {
			imageDesc = AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
			if (imageDesc != null) {
				getDefault().getImageRegistry().put(path, imageDesc);
			}
		}
		return imageDesc;
	}

	public static Image getImage(String path) {
		Image image = getDefault().getImageRegistry().get(path);
		if (image == null || image.isDisposed()) {
			ImageDescriptor imageDesc = AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
			if (CommonUIPlugin.getDefault().getImageRegistry().get(path) != null) {
				CommonUIPlugin.getDefault().getImageRegistry().remove(path);
			}
			CommonUIPlugin.getDefault().getImageRegistry().put(path, imageDesc);
			return CommonUIPlugin.getDefault().getImageRegistry().get(path);
		}
		return image;
	}

	public static IDialogSettings getPluginDialogSettings() {
		IDialogSettings dialogSettings = getDefault().getDialogSettings();
		IDialogSettings pliginDialogSettings = dialogSettings.getSection(PLUGIN_ID);
		if (pliginDialogSettings == null) {
			return dialogSettings.addNewSection(PLUGIN_ID);
		}
		return pliginDialogSettings;
	}

	public static void savePluginDialogSettings() {
		getDefault().saveDialogSettings();
	}

	public static String getSettingValue(String key) {
		return getPluginDialogSettings().get(key);
	}

	public static void putSettingValue(String key, String value) {
		getPluginDialogSettings().put(key, value);
		savePluginDialogSettings();
	}

	public static String getLastCheckedNoticeVersion() {
		return getSettingValue("LastCheckedNoticeVersion");
	}

	public static void setLastCheckedNoticeVersion(String version) {
		putSettingValue("LastCheckedNoticeVersion", version);
		savePluginDialogSettings();
	}

	public static boolean getFindOptionCaseSensitive() {
		return "Y".equals(getSettingValue("FindOptionCaseSensitive"));
	}

	public static void setFindOptionCaseSensitive(boolean isCaseSensitive) {
		putSettingValue("FindOptionCaseSensitive", isCaseSensitive ? "Y" : "N");
		savePluginDialogSettings();
	}

	public static boolean getFindOptionCaseGuard() {
		return "Y".equals(getSettingValue("FindOptionCaseGuard"));
	}

	public static void setFindOptionCaseGuard(boolean isCaseGuard) {
		putSettingValue("FindOptionCaseGuard", isCaseGuard ? "Y" : "N");
		savePluginDialogSettings();
	}

	public static boolean getFindOptionMatchAll() {
		return "Y".equals(getSettingValue("FindOptionMatchAll"));
	}

	public static void setFindOptionMatchAll(boolean isMatchAll) {
		putSettingValue("FindOptionMatchAll", isMatchAll ? "Y" : "N");
		savePluginDialogSettings();
	}
}
