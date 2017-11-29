package com.cubrid.common.ui.query.control.event;

import java.util.EventListener;

public interface RecordReaderListener extends EventListener {
	public void readEvent(RecordReaderEvent event);
}
