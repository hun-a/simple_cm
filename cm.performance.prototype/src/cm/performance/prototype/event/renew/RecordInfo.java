package cm.performance.prototype.event.renew;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RecordInfo {
	private Map<String, Map<Integer, String>> info;

	private RecordInfo() {
		info = new HashMap<String, Map<Integer, String>>(); 
	}

	private static class Singleton {
		private final static RecordInfo instance = new RecordInfo();
	}

	public static RecordInfo getInstance() {
		return Singleton.instance;
	}
	
	public void setNewInfo(String key, int recordCount) {
		setKey(key);
		info.get(key).put(-1, Integer.toString(recordCount));
	}
	
	private void setKey(String key) {
		if (info.get(key) == null) {
			info.put(key, new HashMap<Integer, String>());
		}
	}

	public String getFileNameByKeyAndIndex(String key, int index) {
		return info.get(key).get(index);
	}

	public void setFileName(String key, int index, String value) {
		info.get(key).put(index, value);
	}

	public String[] getKeys() {
		return (String[]) info.keySet().toArray();
	}

	public void remove(String key) {
		for (Map.Entry<Integer, String> element: info.get(key).entrySet()) {
			new File(element.getValue()).delete();
		}

		info.remove(key);
	}
}
