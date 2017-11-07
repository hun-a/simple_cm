package cm.performance.prototype.playground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cubrid.common.ui.spi.table.CellValue;

public class FinalTest {
	private final int MAX_SIZE = 100;
	private List<Map<String, CellValue>> list;
	
	public static void main(String ... args) {
		FinalTest test = new FinalTest();
		
		test.test();
	}

	public void test() {
		list = Util.initList(MAX_SIZE);
		
		finalMethod((List<Map<String, CellValue>>)((ArrayList) list).clone(), "ClonedList");
		finalMethod(list, "JustFinal");
		modifyList();
	}
	
	private void finalMethod(final List<Map<String, CellValue>> list, final String testName) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (list.size() >= 0) {
					System.out.format("Info] %s: Not modified... %d\n", testName, list.size());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.format("Info] %s: List is Empty! %d\n", testName, list.size());
			}
		}).start();
	}
	
	private void modifyList() {
		int count = MAX_SIZE;
		while (true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count >= 0) {
				list.remove(--count);
				System.out.println("Info] this.list is modified.");
			}
		}
	}
}
