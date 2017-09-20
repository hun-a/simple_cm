package cm.performance.model;

public class Presence {
	public static final Presence CONNECT = new Presence("Connect");
	public static final Presence DISCONNECT = new Presence("Disconnect");
	private String value;

	private Presence(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
