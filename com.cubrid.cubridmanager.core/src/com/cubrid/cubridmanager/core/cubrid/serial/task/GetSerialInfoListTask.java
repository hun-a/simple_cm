package com.cubrid.cubridmanager.core.cubrid.serial.task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.cubrid.common.core.common.model.SerialInfo;
import com.cubrid.common.core.util.CompatibleUtil;
import com.cubrid.common.core.util.LogUtil;
import com.cubrid.cubridmanager.core.Messages;
import com.cubrid.cubridmanager.core.common.jdbc.JDBCTask;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;

public class GetSerialInfoListTask extends JDBCTask {
	private static final Logger LOGGER = LogUtil.getLogger(GetSerialInfoListTask.class);
	private final List<SerialInfo> serialInfoList = new ArrayList<SerialInfo>();

	/**
	 * The constructor
	 *
	 * @param dbInfo
	 *
	 */
	public GetSerialInfoListTask(DatabaseInfo dbInfo) {
		super("GetSerialInfoList", dbInfo);
	}

	/**
	 * Execute to get serial information list by JDBC
	 */
	public void execute() { // FIXME extract to utility class
		try {
			if (errorMsg != null && errorMsg.trim().length() > 0) {
				return;
			}

			if (connection == null || connection.isClosed()) {
				errorMsg = Messages.error_getConnection;
				return;
			}

			//databaseInfo.getServerInfo().compareVersionKey("8.2.2") >= 0;
			boolean isSupportCache = CompatibleUtil.isSupportCache(databaseInfo);
			String sql = "SELECT owner.name, db_serial.* FROM db_serial WHERE class_name IS NULL";

			// [TOOLS-2425]Support shard broker
			if (databaseInfo.isShard()) {
				sql = databaseInfo.wrapShardQuery(sql);
			}

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
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
				boolean isCycle = false;
				if (cyclic != null && cyclic.equals("1")) {
					isCycle = true;
				}
				String cacheCount = null;
				if (isSupportCache) {
					cacheCount = rs.getString("cached_num");
				}
				SerialInfo serialInfo = new SerialInfo(name, owner, currentVal,
						incrementVal, maxVal, minVal, isCycle, startVal,
						cacheCount, className, attName);
				serialInfoList.add(serialInfo);
			}
		} catch (SQLException e) {
			errorMsg = e.getMessage();
			LOGGER.error(e.getMessage(), e);
		} finally {
			finish();
		}
	}

	/**
	 * Get serial information list
	 *
	 * @return List<SerialInfo> The list that includes the instances of
	 *         SerialInfo
	 */
	public List<SerialInfo> getSerialInfoList() {
		return serialInfoList;
	}
}
