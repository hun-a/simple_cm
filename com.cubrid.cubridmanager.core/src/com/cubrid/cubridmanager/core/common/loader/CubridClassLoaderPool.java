package com.cubrid.cubridmanager.core.common.loader;

import java.sql.Driver;

import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.jdbc.proxy.driver.CUBRIDDriverProxy;

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
			return new CUBRIDDriverProxy(jdbcDriverVersion);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return null;
	}
}
