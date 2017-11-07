package cm.performance.prototype.test.event;

import java.util.EventObject;

public class CustomEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	public CustomEvent(Object source) {
		super(source);
	}
}
