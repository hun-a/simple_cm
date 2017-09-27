package com.cubrid.common.core.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;

public class CubridUtil {
	private static final Logger LOGGER = LogUtil.getLogger(CubridUtil.class);

	public static String fetchStatisticsWithRawText(Connection conn) {
		StringBuilder statLogs = new StringBuilder();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SHOW EXEC STATISTICS;");
			while (rs.next()) {
				statLogs.append(rs.getString(1)).append(" : ").append(rs.getString(2)).append("\n");
			}
		} catch (Exception e) {
			LOGGER.error("", e);
		} finally {
			QueryUtil.freeQuery(stmt, rs);
			stmt = null;
			rs = null;
		}

		return statLogs.toString();
	}
	
	public static Map<String, String> fetchStatistics(Connection conn) {
		Map<String, String> statistics = new LinkedHashMap<String, String>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SHOW EXEC STATISTICS;");
			while (rs.next()) {
				statistics.put(rs.getString(1), rs.getString(2));
			}
		} catch (Exception ignored) {
		} finally {
			QueryUtil.freeQuery(stmt, rs);
		}

		return statistics;
	}
	
	public static String makeStatisticsWithRawText(Map<String, String> statistics) {
		if (statistics == null) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		for (Map.Entry<String, String> entry : statistics.entrySet()) {
			result.append(entry.getKey()).append(" : ").append(entry.getValue())
					.append(StringUtil.NEWLINE);
		}
		return result.toString();
	}

	public static LinkedHashMap<String, String> makeBlankStatistics() {
		LinkedHashMap<String, String> statistics = new LinkedHashMap<String, String>();
		return statistics;
	}

	public static void changeCollectExecStats(Connection conn, boolean tuningMode) {
		Statement stmt = null;
		String sql = "SET @collect_exec_stats = " + (tuningMode ? "1" : "0");
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception ignored) {
		} finally {
			QueryUtil.freeQuery(stmt);
		}
	}

	public static void beginCollectExecStats(Connection conn) {
		Statement stmt = null;
		try {
			String sql = "SET @collect_exec_stats = 0;";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			QueryUtil.freeQuery(stmt);

			sql = "SET @collect_exec_stats = 1;";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception ignored) {
		} finally {
			QueryUtil.freeQuery(stmt);
		}
	}
}
