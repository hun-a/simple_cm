package cm.performance.prototype.event.renew;

public class Timer {
	private static long START;
	private static long END;
	
	public static void start() {
		START = System.currentTimeMillis();
	}
	
	public static void end() {
		END = System.currentTimeMillis();
	}
	
	public static void printElapsedTime() {
		System.out.println("** INFO **  elapsed time: " + (END - START) / 1000 + "sec");
	}
}
