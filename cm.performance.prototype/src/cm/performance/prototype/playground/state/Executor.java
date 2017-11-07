package cm.performance.prototype.playground.state;

public class Executor {
	public static void main(String[] args) {
		final int MAX = 10;
		final RecordReaderManager manager = new RecordReaderManager();
		final RecordWriter writer = new RecordWriter();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// 1. fetch
				// 2. write
				for (int i = 1; i <= MAX; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					writer.write(manager, MAX, i);
				}
			}
		}).start();

		// 3. read
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!manager.isAvailable()) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(manager.getState());
				}
			}
		}).start();
	}
}
