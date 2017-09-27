package com.cubrid.common.ui.spi.persist;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

import com.cubrid.cubridmanager.core.common.xml.IXMLMemento;
import com.cubrid.cubridmanager.core.common.xml.XMLMemento;

public final class PersistUtils {

	private PersistUtils() {
		//empty
	}

	/**
	 * get the preference object from configuration scope.
	 * 
	 * @param pluginId String
	 * @return IEclipsePreferences
	 */
	public static IEclipsePreferences getGlobalPreference(String pluginId) {
		return new ConfigurationScope().getNode(pluginId);
	}

	/**
	 * Get value by id from global preference of configuration scope
	 * 
	 * @param pluginId String
	 * @param key String
	 * @return the value
	 */
	public static String getGlobalPreferenceValue(String pluginId, String key) {
		return getGlobalPreference(pluginId).get(key, "");
	}

	/**
	 * Save value to global preference of configuration scope
	 * 
	 * @param pluginId String
	 * @param key the key
	 * @param value the value
	 */
	public static void setGlobalPreferenceValue(String pluginId, String key, String value) {
		IEclipsePreferences prefs = getGlobalPreference(pluginId);
		prefs.put(key, value);
		try {
			prefs.flush();
		} catch (BackingStoreException e) {
			prefs = null;
		}
	}

	/**
	 * get the preference object from instance scope.
	 * 
	 * @param pluginId String
	 * @return IEclipsePreferences
	 */
	public static IEclipsePreferences getPreference(String pluginId) {
		return new InstanceScope().getNode(pluginId);
	}

	/**
	 * Get value by id from plugin preference of instance scope
	 * 
	 * @param pluginId String
	 * @param key String
	 * @return the value
	 */
	public static String getPreferenceValue(String pluginId, String key) {
		return getPreference(pluginId).get(key, "");
	}

	/**
	 * Get value by id from plugin preference of instance scope
	 * 
	 * @param pluginId String
	 * @param key String
	 * @param defaultValue String
	 * @return the value
	 */
	public static String getPreferenceValue(String pluginId, String key, String defaultValue) {
		return getPreference(pluginId).get(key, defaultValue);
	}

	/**
	 * Save value to plugin preference of instance scope
	 * 
	 * @param pluginId String
	 * @param key the key
	 * @param value the value
	 */
	public static void setPreferenceValue(String pluginId, String key, String value) {
		IEclipsePreferences prefs = getPreference(pluginId);
		prefs.put(key, value);
		try {
			prefs.flush();
		} catch (BackingStoreException e) {
			prefs = null;
		}
	}

	/**
	 * Get the node XML memento from instance scope preference
	 * 
	 * @param pluginId String
	 * @param key String
	 * @return IXMLMemento
	 */
	public static IXMLMemento getXMLMemento(String pluginId, String key) {

		IEclipsePreferences preference = getPreference(pluginId);
		String xmlString = preference.get(key, "");
		if (xmlString == null || xmlString.length() == 0) {
			return null;
		}
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
			return XMLMemento.loadMemento(in);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 
	 * Save the xml content to instance scope preference
	 * 
	 * @param pluginId String
	 * @param key String
	 * @param memento XMLMemento
	 */
	public static void saveXMLMemento(String pluginId, String key, XMLMemento memento) {
		String xmlString = null;
		try {
			xmlString = memento.saveToString();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		IEclipsePreferences preference = PersistUtils.getPreference(pluginId);
		try {
			preference.put(key, xmlString);
			preference.flush();
		} catch (BackingStoreException e) {
			preference = null;
		}
	}
}
