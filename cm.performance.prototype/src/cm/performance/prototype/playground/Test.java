package cm.performance.prototype.playground;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.cubrid.common.ui.spi.table.CellValue;

public class Test {
	public static void main(String[] args) {
		Test test = new Test();
		test.test();
	}
	
	public void test() {
		
		Map<String, CellValue> map = new HashMap<>();
		for (int i = 1; i <= 10; i++) {
			CellValue value = new CellValue();
			value.setFileCharset("UTF-8");
			int showValue = 100 + i;
			value.setValue(showValue);
			value.setShowValue(Integer.toString(showValue));
			value.setHasLoadAll(true);
			
			map.put(Integer.toString(i), value);
		}
		
		String fileName = writeObject(map);
		readObject(fileName);
	}
	
	private String writeObject(Object obj) {
		String fileName = Long.toString(Calendar.getInstance().getTimeInMillis());
		
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream out = new ObjectOutputStream(fos)) {
			out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
	
	private void readObject(String fileName) {
		try (FileInputStream is = new FileInputStream(fileName);
				ObjectInputStream in = new ObjectInputStream(is)) {
			Map<String, CellValue> obj = (Map<String, CellValue>) in.readObject();
			Iterator<String> keys = obj.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				System.out.println(obj.get(key).getShowValue());
			}
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
