package cm.performance.prototype.event.renew;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.event.EventListenerList;

public class RecordManager<T> {
	private EventListenerList ListenerList;
	
	public RecordManager() {
		ListenerList = new EventListenerList();
	}

	public void addRecordReaderListener(RecordReaderListener listener) {
		ListenerList.add(RecordReaderListener.class, listener);
	}

	public void removeRecordReaderListener(RecordReaderListener listener) {
		ListenerList.remove(RecordReaderListener.class, listener);
	}

	private void fireRecordReaderEvent(RecordReaderEvent event, int page) {
		event.setPageNumber(page);
		Object[] listeners = ListenerList.getListenerList();
		
		for (int i = listeners.length - 2; i >= 0; i-=2) {
			if (listeners[i] == RecordReaderListener.class) {
				((RecordReaderListener) listeners[i + 1]).readEvent(event);
			}
		}
	}

	public void read(int page) {
		fireRecordReaderEvent(new RecordReaderEvent(this), page);
	}

	@SuppressWarnings("unchecked")
	public T readSpecificRecords(String queryEditorId, int index) {
		String fileName = RecordInfo.getInstance()
				.getFileNameByKeyAndIndex(queryEditorId, index); 
		ObjectInputStream in = null;

		try {
			in = new ObjectInputStream(new FileInputStream(fileName));
			Object obj = in.readObject();
			return (T) obj;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void write(final T t, final String queryEditorId,
			final int index) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				ObjectOutputStream out = null;
				
				try {
					String fileName = "temp/" + Long.toString(System.currentTimeMillis());
					out = new ObjectOutputStream(new FileOutputStream(fileName));
					out.writeObject(t);
					out.flush();
					
					synchronized (this) {
						setFileName(queryEditorId, index, fileName);
						if (index == 0) {
							read(index);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (out != null) {
							out.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void setFileName(String key, int index, String value) {
		RecordInfo.getInstance().setFileName(key, index, value);
	}
	
	public void remove(String key) {
		RecordInfo.getInstance().remove(key);
	}
	
	public int size(String key) {
		return RecordInfo.getInstance().size(key);
	}
	
	public boolean isEmpty(String key) {
		return size(key) <= 0;
	}
}
