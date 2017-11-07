package cm.performance.prototype.playground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cubrid.common.ui.spi.table.CellValue;

public class Util {
	public static List<Map<String, CellValue>> initList(int maxSize) {
		List<Map<String, CellValue>> list = new ArrayList<>();
		
		for (int j = 0; j < maxSize; j++) {
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
			list.add(map);
		}
		
		return list;
	}
	
	public static String getClassName(Class<?> clz) {
		String[] names = clz.getName().split("\\.");
		return names[names.length - 1];
	}
}
