package com.cubrid.common.ui.query.editor;

import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.cubridmanager.core.common.model.ServerInfo;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;

public interface IDatabaseProvider {
	public CubridDatabase getDatabase();

	public DatabaseInfo getDatabaseInfo();

	public ServerInfo getServerInfo();
}
