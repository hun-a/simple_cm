package cm.performance.prototype.playground.state;

import java.util.ArrayList;
import java.util.List;

public class RecordReaderManager {
	private IRecordReader state;
	private List<Integer> availableList;
	
	public RecordReaderManager() {
		this.state = new NotReady();
		availableList = new ArrayList<>();
	}
	
	public RecordReaderManager(IRecordReader state) {
		this.state = state;
		availableList = new ArrayList<>();
	}
	
	public void notReady(int id) {
		this.state = state.notReady(id);
	}
	
	public void readAvailable(int id) {
		this.state = state.readAvailable(id);
	}
	
	public void done(int id) {
		this.state = state.done(id);
	}
	
	public void add(int id) {
		availableList.add(id);
	}
	
	public void display() {
		for (int i: availableList) {
			System.out.print(i + "\t");
		}
		System.out.println();
	}
	
	public int size() {
		return availableList.size(); 
	}
	
	public State getState() {
		if (state.getClass() == Done.class) {
			return State.DONE;
		} else if (state.getClass() == ReadAvailable.class) {
			return State.AVAILABLE;
		} else {
			return State.NOT_READY;
		}
	}
	
	public boolean isDone() {
		return this.state.getClass() == Done.class;
	}
	
	public boolean isAvailable() {
		return this.state.getClass() == ReadAvailable.class;
	}
	
	@Override
	public String toString() {
		return this.state.getClass().getName();
	}
}

enum State {
	NOT_READY, AVAILABLE, DONE
}