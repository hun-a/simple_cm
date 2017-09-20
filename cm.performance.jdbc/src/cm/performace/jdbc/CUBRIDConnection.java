package cm.performace.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.util.Properties;

import com.cubrid.jdbc.proxy.driver.CUBRIDDriverProxy;
import com.cubrid.jdbc.proxy.manage.JdbcClassLoaderFactory;

public class CUBRIDConnection {
	private static final String JDBC_PATH =
			"/Users/hun/RND/01.QA/00.TEST/jdbc/00.CUBRID/JDBC-10.1.1.7667-cubrid.jar";
	
	public static Connection getConnection(String url) throws Exception {
		JdbcClassLoaderFactory.registerClassLoader(JDBC_PATH);
		String realVersion = JdbcClassLoaderFactory.validateJdbcFile(JDBC_PATH);
		Driver driver = new CUBRIDDriverProxy(realVersion);
		return driver.connect(url, new Properties());
	}
}
