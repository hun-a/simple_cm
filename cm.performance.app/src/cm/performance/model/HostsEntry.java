package cm.performance.model;

import java.sql.Connection;

import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.common.ui.spi.model.CubridServer;
import com.cubrid.cubridmanager.core.common.model.ServerInfo;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;
import com.cubrid.cubridmanager.core.cubrid.user.model.DbUserInfo;

public class HostsEntry extends Host {
	private final String server;
	private final int port;
	private final String db;
	private final String user;
	private final String password;
	private final String jdbcDriverPath;
	private Presence presence;
	private final HostsGroup group;
	private Connection conn;

	public HostsEntry(HostsGroup group, String server,
			int port, String db, String user,
			String password, String jdbcDriverPath) {
		this.group = group;
		this.server = server;
		this.port = port;
		this.db = db;
		this.user = user;
		this.password = password;
		this.jdbcDriverPath = jdbcDriverPath;
		this.presence = Presence.DISCONNECT;
	}

	public Presence getPresence() {
		return presence;
	}

	public void setPresence(Presence presence) {
		this.presence = presence;
		group.fireHostsChanged(this);
	}

	public String getServer() {
		return server;
	}
	
	public String getDb() {
		return db;
	}

	public HostsGroup getParent() {
		return group;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public CubridDatabase connect() {
		// Set the ServerInfo
		ServerInfo serverInfo = new ServerInfo();
		serverInfo.addDatabase(db);
		serverInfo.setJdbcDriverVersion(jdbcDriverPath);
		
		// Set the DatabaseInfo
		DatabaseInfo dbInfo = new DatabaseInfo(db, serverInfo);
		DbUserInfo dbUserInfo = new DbUserInfo(db, user, password, "", true);
		dbInfo.addDbUserInfo(dbUserInfo);
		dbInfo.setBrokerIP(server);
		dbInfo.setBrokerPort(Integer.toString(port));
		dbInfo.setAuthLoginedDbUserInfo(dbUserInfo);
		
		CubridServer server = new CubridServer(Long.toString(System.currentTimeMillis()), "test", null, null);
		server.setServerInfo(serverInfo);
		
		// Set the CubridDatabase
		CubridDatabase database = new CubridDatabase(Long.toString(System.currentTimeMillis()), "");
		database.setDatabaseInfo(dbInfo);
		database.setServer(server);
		database.setLogined(true);
		
		return database;
	}
}
