package cm.performance.model;

import java.sql.Connection;

import cm.performace.jdbc.CUBRIDConnection;

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
	
	public boolean connect() throws Exception {
		String url = "jdbc:cubrid:" + server + ":" + port + ":"
				+ db + ":" + user + ":" + password + ":";
		conn = CUBRIDConnection.getConnection(jdbcDriverPath, url);
		if (conn != null) {
			return true;
		} 
		return false;	
	}
}
