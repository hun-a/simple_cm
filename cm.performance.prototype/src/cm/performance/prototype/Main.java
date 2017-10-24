package cm.performance.prototype;

import cm.performance.prototype.controller.NewCM;
import cm.performance.prototype.controller.OriginalCM;

public class Main {
	public static void main(String ... args) {
		String sql = "SELECT * FROM table_1 limit 5000";
//		OriginalCM cm = new OriginalCM();
		NewCM cm = new NewCM();
		cm.run(sql);
		cm.print();
	}
}
