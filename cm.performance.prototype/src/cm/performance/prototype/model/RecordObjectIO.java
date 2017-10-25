package cm.performance.prototype.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RecordObjectIO<T> {
	private List<Boolean> isAvailable;
	private List<String> filePointerList;
	private int limit;
	
	public RecordObjectIO(int limit) {
		this.limit = limit;
		this.isAvailable = new ArrayList<Boolean>();
		this.filePointerList = new ArrayList<String>();
	}
	
	public void saveObjectToFile(String fileName, T t) {
		isAvailable.add(true);
		filePointerList.add(fileName);
		writeObject(fileName, t);
	}
	
	public T getObjectFromFile(int dataPosition) {
		int quotient = dataPosition / limit;
		int index = quotient == 0 ? 0 : quotient - 1;
		index = (dataPosition % limit == 0) ? index : index + 1;
		
		return readObject(index);
	}
	
	private void writeObject(final String fileName, final T t) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				ObjectOutputStream out = null;
				try {
					out = new ObjectOutputStream(new FileOutputStream(fileName));
					out.writeObject(t);
					out.flush();
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
	
	@SuppressWarnings("unchecked")
	private T readObject(final int index) {
		if (isAvailable.get(index) != null || isAvailable.get(index)) {
			ObjectInputStream in = null;
			try {
				do {
					in = new ObjectInputStream(new FileInputStream(filePointerList.get(index)));
				} while(in == null);
				return (T) in.readObject();
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
		}
		return null;
	}
	
	public int size() {
		return filePointerList.size();
	}
}
