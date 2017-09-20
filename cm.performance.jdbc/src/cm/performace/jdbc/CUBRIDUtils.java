package cm.performace.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class CUBRIDUtils {

	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
