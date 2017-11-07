package cm.performance.prototype.playground.state;

public class RecordReaderTest {
	public static void main(String[] args) {
		RecordReaderManager manager = new RecordReaderManager();
		
		// after store one file...
		manager.readAvailable(1);
		
		// fetching all data was done.
		manager.done(2);
	}
}
