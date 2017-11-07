package cm.performance.prototype.test.event;

import javax.swing.event.EventListenerList;

public class CustomComponent {
	protected EventListenerList listenerList;
	
	public CustomComponent() {
		listenerList = new EventListenerList();
	}
	
	public void addCustomEventListener(CustomEventListener listener) {
		listenerList.add(CustomEventListener.class, listener);
	}
	
	public void removeCustomEventListener(CustomEventListener listener) {
		listenerList.remove(CustomEventListener.class, listener);
	}
	
	private void fireCustomeEvent(CustomEvent event) {
		Object[] listeners = listenerList.getListenerList();
		
		for (int i = listeners.length - 2; i >= 0; i-=2) {
			if (listeners[i] == CustomEventListener.class) {
				((CustomEventListener) listeners[i + 1]).customEvent(event);
			}
		}
	}
	
	public void eventOccurred() {
		fireCustomeEvent(new CustomEvent(this));
	}
}
