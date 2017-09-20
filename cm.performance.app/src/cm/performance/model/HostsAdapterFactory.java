package cm.performance.model;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;


public class HostsAdapterFactory implements IAdapterFactory {
	private IWorkbenchAdapter groupAdapter = new IWorkbenchAdapter() {
		
		public Object getParent(Object o) {
			return ((HostsGroup) o).getParent();
		}
		
		public String getLabel(Object o) {
			HostsGroup group = (HostsGroup) o;
			int available = 0;
			Host[] entries = group.getEntries();
			for (int i = 0; i < entries.length; i++) {
				Host host = entries[i];
				if (host instanceof HostsEntry) {
					if (((HostsEntry) host).getPresence()
							!= Presence.DISCONNECT) {
						available++;
					}
				}
			}
			return ((HostsGroup) o).getServer()
					+ " (" + available + "/" + entries.length + ")";
		}
		
		public ImageDescriptor getImageDescriptor(Object object) {
			return null;
		}
		
		public Object[] getChildren(Object o) {
			return ((HostsGroup) o).getEntries();
		}
	};
	
	private IWorkbenchAdapter entryAdapter = new IWorkbenchAdapter() {
		
		public Object getParent(Object o) {
			// TODO Auto-generated method stub
			return ((HostsEntry) o).getParent();
		}
		
		public String getLabel(Object o) {
			HostsEntry entry = (HostsEntry) o;
			return entry.getDb() + "@" + entry.getServer();
		}
		
		public ImageDescriptor getImageDescriptor(Object object) {
			return null;
		}
		
		public Object[] getChildren(Object o) {
			return new Object[0];
		}
	};

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IWorkbenchAdapter.class
				&& adaptableObject instanceof HostsGroup) {
			return groupAdapter;
		}
		if (adapterType == IWorkbenchAdapter.class
				&& adaptableObject instanceof HostsEntry) {
			return entryAdapter;
		}
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[] { IWorkbenchAdapter.class };
	}
	
}
