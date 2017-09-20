package cm.performace.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CUBRIDRunner {

	public static ResultSet select(Connection conn, String sql)
			throws SQLException {
		Statement stmt = conn.createStatement();
		return stmt.executeQuery(sql);
	}
}
