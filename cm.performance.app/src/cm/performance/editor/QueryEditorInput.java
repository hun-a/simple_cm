package cm.performance.editor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import cm.performance.model.HostsEntry;

public class QueryEditorInput implements IEditorInput {
	private HostsEntry entry;
	private String participant;
	
	public QueryEditorInput(HostsEntry entry) {
		super();
		Assert.isNotNull(entry);
		this.entry = entry;
		this.participant = entry.getDb() + "@" + entry.getServer();
	}	

	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getToolTipText() {
		return participant;
	}
	
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getName() {
		return participant;
	}
	
	public HostsEntry getEntry() {
		return entry;
	}
	
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}
}
