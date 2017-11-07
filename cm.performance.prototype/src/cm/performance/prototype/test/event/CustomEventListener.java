package cm.performance.prototype.test.event;

import java.util.EventListener;

public interface CustomEventListener extends EventListener {
	void customEvent(CustomEvent event);
}
