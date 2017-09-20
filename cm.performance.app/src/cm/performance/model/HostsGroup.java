package cm.performance.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;

public class HostsGroup extends Host {
	private List entries;

	private HostsGroup parent;

	private String server;

	private ListenerList listeners;

	public HostsGroup(HostsGroup parent, String server) {
		this.server = server;
		this.parent = parent;
	}

	public String getServer() {
		return server;
	}

	public HostsGroup getParent() {
		return parent;
	}

	public void addEntry(Host entry) {
		if (entries == null)
			entries = new ArrayList(5);
		entries.add(entry);
		fireHostsChanged(null);
	}

	public void removeEntry(Host entry) {
		if (entries != null) {
			entries.remove(entry);
			if (entries.isEmpty())
				entries = null;
		}
		fireHostsChanged(null);
	}

	public Host[] getEntries() {
		if (entries != null)
			return (Host[]) entries.toArray(new Host[entries.size()]);
		return new Host[0];
	}

	public void addHostsListener(IHostsListener listener) {
		if (parent != null)
			parent.addHostsListener(listener);
		else {
			if (listeners == null)
				listeners = new ListenerList();
			listeners.add(listener);
		}
	}

	public void removeHostsListener(IHostsListener listener) {
		if (parent != null)
			parent.removeHostsListener(listener);
		else {
			if (listeners != null) {
				listeners.remove(listener);
				if (listeners.isEmpty())
					listeners = null;
			}
		}
	}

	protected void fireHostsChanged(HostsEntry entry) {
		if (parent != null)
			parent.fireHostsChanged(entry);
		else {
			if (listeners == null)
				return;
			Object[] rls = listeners.getListeners();
			for (int i = 0; i < rls.length; i++) {
				IHostsListener listener = (IHostsListener) rls[i];
				listener.hostsChanged(this, entry);
			}
		}
	}
}