package com.cubrid.cubridmanager.core.cubrid.serial.task;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.cubrid.common.core.common.model.SerialInfo;
import com.cubrid.common.core.util.CompatibleUtil;
import com.cubrid.common.core.util.LogUtil;
import com.cubrid.cubridmanager.core.Messages;
import com.cubrid.cubridmanager.core.common.jdbc.JDBCTask;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;

public class GetSerialInfoTask extends JDBCTask {
	private static final Logger LOGGER = LogUtil.getLogger(GetSerialInfoTask.class);
	private boolean isCommentSupport = false;

	/**
	 * The constructor
	 *
	 * @param dbInfo
	 */
	public GetSerialInfoTask(DatabaseInfo dbInfo) {
		super("GetSerialInfo", dbInfo);
		isCommentSupport = CompatibleUtil.isCommentSupports(dbInfo);
	}

	/**
	 * Get serial information by serial name
	 *
	 * @param serialName String The given serial name
	 * @return SerialInfo The instance of SerialInfo
	 */
	public SerialInfo getSerialInfo(String serialName) { // FIXME extract to utility class
		SerialInfo serialInfo = null;
		try {
			if (errorMsg != null && errorMsg.trim().length() > 0) {
				return null;
			}

			if (connection == null || connection.isClosed()) {
				errorMsg = Messages.error_getConnection;
				return null;
			}

			boolean isSupportCache = CompatibleUtil.isSupportCache(databaseInfo);

			String sql = "SELECT owner.name, db_serial.* FROM db_serial WHERE name=?";
			// [TOOLS-2425]Support shard broker
			if (databaseInfo.isShard()) {
				sql = databaseInfo.wrapShardQuery(sql);
			}

			stmt = connection.prepareStatement(sql);
			((PreparedStatement) stmt).setString(1, serialName);
			rs = ((PreparedStatement) stmt).executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String owner = rs.getString("owner.name");
				String currentVal = rs.getString("current_val");
				String incrementVal = rs.getString("increment_val");
				String maxVal = rs.getString("max_val");
				String minVal = rs.getString("min_val");
				String cyclic = rs.getString("cyclic");
				String startVal = rs.getString("started");
				String className = rs.getString("class_name");
				String attName = rs.getString("att_name");
				String cacheCount = null;
				if (isSupportCache) {
					cacheCount = rs.getString("cached_num");
				}
				boolean isCycle = false;
				if (cyclic != null && cyclic.equals("1")) {
					isCycle = true;
				}
				serialInfo = new SerialInfo(name, owner, currentVal,
						incrementVal, maxVal, minVal, isCycle, startVal,
						cacheCount, className, attName);
				String description = null;
				if (isCommentSupport) {
					description = rs.getString("comment");
					serialInfo.setDescription(description);
				}
			}

		} catch (SQLException e) {
			errorMsg = e.getMessage();
			LOGGER.error(e.getMessage(), e);
		} finally {
			finish();
		}

		return serialInfo;
	}
}
