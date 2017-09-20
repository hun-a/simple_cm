package cm.performance.model;

public class Session {
	private HostsGroup rootGroup;
	private String name;
	private String server;
	
	public Session() {
	}
	
	public void setSessionDescription(String name, String server) {
		this.name = name;
		this.server = server;
	}

	public HostsGroup getRoot() {
		if (rootGroup == null)
			rootGroup = new HostsGroup(null, "RootGroup");
		return rootGroup;
	}

	public String getName() {
		return name;
	}

	public String getServer() {
		return server;
	}
}
