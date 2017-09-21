package cm.performace.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

import com.cubrid.jdbc.proxy.driver.CUBRIDDriverProxy;
import com.cubrid.jdbc.proxy.manage.JdbcClassLoaderFactory;

public class CUBRIDConnection {
	public static Connection getConnection(String jdbc, String url) throws Exception {
		JdbcClassLoaderFactory.registerClassLoader(jdbc);
		String realVersion = JdbcClassLoaderFactory.validateJdbcFile(jdbc);
		Driver driver = new CUBRIDDriverProxy(realVersion);
		return driver.connect(url, new Properties());
	}
}
