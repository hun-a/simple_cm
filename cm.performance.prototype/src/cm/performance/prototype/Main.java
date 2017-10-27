package cm.performance.prototype;

import cm.performance.prototype.controller.NewCM;
import cm.performance.prototype.controller.OriginalCM;

public class Main {
	public static void main(String ... args) {
		String sql = "select * from db_class a, db_class b, db_class c, db_class d";

		long start = System.nanoTime();
//		OriginalCM cm = new OriginalCM();
		NewCM cm = new NewCM();
		cm.run(sql);
		cm.print();
		System.out.println("\n\nelapsed time: " + (System.nanoTime() - start) / 1_000_000 + "ms");
	}
}
