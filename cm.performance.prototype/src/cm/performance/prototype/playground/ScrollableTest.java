package cm.performance.prototype.playground;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableTest {
	public static void main(String[] args) {
		try {
			Class.forName("cubrid.jdbc.driver.CUBRIDDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "SELECT * FROM table_1 ORDER BY column_3 limit 100";
		String url = "jdbc:cubrid:218.233.240.77:63100:demodb:dba::";
		try (Connection conn = DriverManager.getConnection(url);
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sql)) {
			
			boolean isReverse = true;
			int count = 0;
			final int REVERSE_COUNT = 50;
			
			rs.afterLast();
			
			while (rs.previous()) {
				printReslutSet(rs);
				if (++count == REVERSE_COUNT) {
					break;
				}
			}
			while (rs.next()) {
				printReslutSet(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void printReslutSet(ResultSet rs) throws SQLException {
		ResultSetMetaData rsMeta = rs.getMetaData();
		int columnCount = rsMeta.getColumnCount();
		
		for (int i = 1; i <= columnCount; i++) {
			System.out.print(rs.getObject(i) + "\t");
		}
		System.out.println();
	}
}
