package cm.performance.prototype.event.renew;

import java.util.EventListener;

public interface RecordReaderListener extends EventListener {
	public void readEvent(RecordReaderEvent event);
}
