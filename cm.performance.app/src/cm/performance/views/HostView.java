package cm.performance.views;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.ViewPart;

import cm.performance.model.Host;
import cm.performance.model.HostsAdapterFactory;
import cm.performance.model.HostsEntry;
import cm.performance.model.HostsGroup;
import cm.performance.model.IHostsListener;
import cm.performance.model.Session;

public class HostView extends ViewPart {
	public static final String ID = "cm.performance.app.views.host";
	private IAdapterFactory adapterFactory = new HostsAdapterFactory();
	private TreeViewer treeViewer;
	private Session session;
	
	public HostView() {
		super();
	}

	public void createPartControl(Composite parent) {
		initializeSession();
		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		Platform.getAdapterManager().registerAdapters(adapterFactory, Host.class);
		getSite().setSelectionProvider(treeViewer);
		treeViewer.setLabelProvider(new WorkbenchLabelProvider());
		treeViewer.setContentProvider(new BaseWorkbenchContentProvider());
		treeViewer.setInput(session.getRoot());
		session.getRoot().addHostsListener(new IHostsListener() {
			public void hostsChanged(HostsGroup hosts, HostsEntry entry) {
				treeViewer.refresh();
			}
		});
	}

	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	
	private void initializeSession() {
		session = new Session();
		HostsGroup root = session.getRoot();
		HostsGroup defaultGroup = new HostsGroup(root, "default");
		root.addEntry(defaultGroup);
		defaultGroup.addEntry(new HostsEntry(
				defaultGroup, "localhost", 30000, "demodb", "public", ""));
	}
	
	public void dispose() {
		Platform.getAdapterManager().unregisterAdapters(adapterFactory);
		super.dispose();
	}
}
