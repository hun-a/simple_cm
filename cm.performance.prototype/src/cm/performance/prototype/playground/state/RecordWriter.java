package cm.performance.prototype.playground.state;

public class RecordWriter {

	public static void main(String[] args) throws InterruptedException {
		RecordReaderManager manager = new RecordReaderManager();
		RecordWriter writer = new RecordWriter();
		
		final int MAX = 10;
		
		for (int i = 1; i <= MAX; i++) {
			Thread.sleep(10);
			writer.write(manager, MAX, i);
		}
		
		System.out.println(manager);
	}

	public void write(final RecordReaderManager manager, final int max, final int count) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (manager) {
					if (max == count) {
						manager.done(count);
					} else {
						manager.readAvailable(count);
					}
					manager.add(count);
				}
			}
		}).start();
	}
}
