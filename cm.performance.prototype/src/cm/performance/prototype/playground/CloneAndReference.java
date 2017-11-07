package cm.performance.prototype.playground;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cubrid.common.ui.spi.table.CellValue;

public class CloneAndReference {
	
	public static void main(String[] args) {
		CloneAndReference test = new CloneAndReference();
		test.test();
	}
	
	public void test() {
		List<Map<String, CellValue>> list = Util.initList(100);
		List<Map<String, CellValue>> clonedList = (List<Map<String, CellValue>>) ((ArrayList<Map<String, CellValue>>) list).clone();
		
		System.out.format("list and clonedList is equal? %b\n", list == clonedList);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.format("%d: is equal? %b\n", i, list.get(i) == clonedList.get(i));
		}
		
		System.out.println("before: " + list.get(10).get("1").getShowValue());
		clonedList.get(10).get("1").setShowValue("modified");
		System.out.println("after : " + list.get(10).get("1").getShowValue());
		
	}
}
