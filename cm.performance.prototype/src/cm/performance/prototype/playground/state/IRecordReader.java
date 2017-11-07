package cm.performance.prototype.playground.state;

public interface IRecordReader {
	public IRecordReader notReady(int id);
	public IRecordReader readAvailable(int id);
	public IRecordReader done(int id);
}
