package cm.performance.prototype.event.renew;

import java.util.EventObject;

public class RecordReaderEvent extends EventObject {
	private static final long serialVersionUID = 6036216284369213781L;
	private int pageNumber;

	public RecordReaderEvent(Object source) {
		super(source);
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getCurrentPage() {
		return pageNumber;
	}

	public int next() {
		return ++pageNumber;
	}

	public int previous() {
		return --pageNumber;
	}
}
