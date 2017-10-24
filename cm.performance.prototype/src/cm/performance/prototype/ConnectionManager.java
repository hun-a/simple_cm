package cm.performance.prototype;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static final String URL = "jdbc:cubrid:218.233.240.77:63100:demodb:dba::";
	private static final String CLASS_PATH = "cubrid.jdbc.driver.CUBRIDDriver";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(CLASS_PATH);
		return DriverManager.getConnection(URL);
	}
}
