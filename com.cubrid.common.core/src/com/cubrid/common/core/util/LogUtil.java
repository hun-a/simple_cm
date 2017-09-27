package com.cubrid.common.core.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LogUtil {
	private LogUtil() {
	}

	/**
	 * re-initialize logger configurations
	 *
	 * @param level Level
	 * @param workspace String
	 */
	public static void configLogger(Level level, String workspace) {
		Properties configPro = new Properties();
		InputStream in = null;
		try {
			in = new LogUtil().getClass().getResourceAsStream("/log4j.properties");
			configPro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			configPro = null;
		} finally {
			FileUtil.close(in);
		}

		// If log4j.properties can't be found.
		if (configPro == null) {
			return;
		}

		if (Level.ERROR.equals(level)) {
			configPro.put("log4j.rootLogger", "ERROR,stdout,logfile");
		} else if (Level.DEBUG.equals(level)) {
			configPro.put("log4j.rootLogger", "DEBUG,stdout,logfile");
		}

		String logPath = workspace;
		if (workspace == null) {
			logPath = System.getProperty("user.home");
		}
		if (logPath != null) {
			logPath = logPath + File.separator + "logs" + File.separator + "cubrid.log"; //TODO: have to rename another name.
			configPro.put("log4j.appender.logfile.file", logPath);
		}

		PropertyConfigurator.configure(configPro);
	}

	public static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}
}
