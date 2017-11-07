package cm.performance.prototype.playground.state;

import cm.performance.prototype.playground.Util;

public class NotReady implements IRecordReader {
	@Override
	public IRecordReader notReady(int id) {
		System.out.format("%d: couldn't change [%s -> %s]\n", id, Util.getClassName(this.getClass()), Util.getClassName(this.getClass()));
		return this;
	}

	@Override
	public IRecordReader readAvailable(int id) {
		System.out.format("%d: changed to readAvailable() [%s -> %s]\n", id, Util.getClassName(this.getClass()), Util.getClassName(ReadAvailable.class));
		return new ReadAvailable();
	}

	@Override
	public IRecordReader done(int id) {
		System.out.format("%d: changed to done() [%s -> %s]\n", id, Util.getClassName(this.getClass()), Util.getClassName(Done.class));
		return new Done();
	}

}
