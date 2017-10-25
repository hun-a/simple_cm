package cm.performance.prototype.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventHandler {
	private static final int MAX_THREAD_POOL = 5;
	private static List<EventListener> listeners = new CopyOnWriteArrayList<EventListener>();
	
	public static synchronized void addListener(EventListener eventListener) {
		if (listeners.indexOf(eventListener) == -1) {
			listeners.add(eventListener);
		}
	}
	
	public static synchronized void removeListener(EventListener eventListener) {
		if (listeners.indexOf(eventListener) != -1) {
			listeners.remove(eventListener);
		}
	}
	
	public static synchronized void callEvent(final Class<?> caller, final String name, boolean doAsync) {
		if (doAsync) {
			callEventAsAsync(caller, name);
		} else {
			callEventAsSync(caller, name);
		}
	}
	
	private static synchronized void callEventAsAsync(final Class<?> caller, final String name) {
		ExecutorService executor = Executors.newFixedThreadPool(MAX_THREAD_POOL);
		
		for (final EventListener listener: listeners) {
			executor.execute(new Runnable() {
				public void run() {
					listener.read(name);
				}
			});
		}
		
		executor.shutdown();
	}
	
	private static synchronized void callEventAsSync(final Class<?> caller, final String name) {
		for (final EventListener listener: listeners) {
			listener.read(name);
		}
	}
}
