package cm.performance.prototype;

import cm.performance.prototype.controller.OriginalCM;
import cm.performance.prototype.controller.Refactoring;
import cm.performance.prototype.event.renew.Timer;

public class Main {
	public static void main(String ... args) {
		Timer.start();
		String sql = "select rownum, a.* from db_class a, db_class b, db_class c, db_class d";

//		OriginalCM cm = new OriginalCM(500);
		Refactoring cm = new Refactoring(500);
		cm.run(sql);
	}
}
