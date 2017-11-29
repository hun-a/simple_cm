package com.cubrid.cubridmanager.core.common.loader;

import java.sql.Driver;

import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.jdbc.proxy.driver.CUBRIDDriverProxy;
import com.cubrid.jdbc.proxy.manage.JdbcClassLoaderFactory;

public final class CubridClassLoaderPool {
	private static final Logger LOGGER = LogUtil.getLogger(CubridClassLoaderPool.class);

	private CubridClassLoaderPool() {
	}

	/**
	 * Get the CUBRID driver based on the jdbc driver version
	 * 
	 * @param jdbcDriverVersion String
	 * @return Driver
	 */
	public static Driver getCubridDriver(String jdbcDriverVersion) {
		try {
			String path = "/Users/hun/RND/01.QA/00.TEST/jdbc/00.CUBRID/JDBC-10.1.1.7667-cubrid.jar";
			JdbcClassLoaderFactory.registerClassLoader(path);
			String realVersion = JdbcClassLoaderFactory.validateJdbcFile(path);
			return new CUBRIDDriverProxy(realVersion);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
