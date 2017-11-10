package cm.performance.prototype.event.renew;

public class Main {
	private final static String QUERY_EDITOR_ID = "New Query 1";
	private final static int LOOP_COUNT = 1;
	private final static RecordManager<Integer> manager;
	
	static {
		manager = new RecordManager<>();
		manager.addRecordReaderListener(new RecordReaderListener() {
			
			@Override
			public void readEvent(RecordReaderEvent event) {
				Object obj = manager.readSpecificRecords(QUERY_EDITOR_ID, 0);
				System.out.println("**** read! ***: " + obj + "\tfile: "
						+ RecordInfo.getInstance().getFileNameByKeyAndIndex(QUERY_EDITOR_ID, 0));
				Timer.end();
				Timer.printElapsedTime();
			}
		});
	}
	
	public static void main(String[] args) {
		Timer.start();
		// init
		RecordInfo.getInstance().setNewInfo(QUERY_EDITOR_ID, 5000);
		
		// fetch and write
		for (int i = 0; i < LOOP_COUNT; i++) {
			for (int j = 0; j < 100; j++) {
				System.out.print("Test " + j + ": ");
				try {
					// fetch
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// write
				manager.write(new Integer(j), QUERY_EDITOR_ID, j);
			}
		}
		
		// read
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.remove(QUERY_EDITOR_ID);
	}
}
