package cm.performance.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;

import cm.performance.dialog.AddHostDialog;
import cm.performance.model.HostsEntry;
import cm.performance.model.HostsGroup;

public class AddHostAction  extends Action implements ISelectionListener,
		ActionFactory.IWorkbenchAction{
	public final static String ID = "cm.performance.action.addHost";
	private final IWorkbenchWindow window;
	private IStructuredSelection selection;
	
	public AddHostAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&Add Host...");
		setToolTipText("Add a host to connect your database");
		window.getSelectionService().addSelectionListener(this);
	}
	
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			selection = (IStructuredSelection) incoming;
			setEnabled(selection.size() == 1
					&& selection.getFirstElement() instanceof HostsGroup);
		} else {
			// Other selections, for example containing text or of other kinds.
			setEnabled(false);
		}
	}

	public void run() {
		AddHostDialog d = new AddHostDialog(window.getShell());
		int code = d.open();
		
		if (code == Window.OK) {
			Object item = selection.getFirstElement();
			HostsGroup group = (HostsGroup) item;
			HostsEntry entry = new HostsEntry(
					group, d.getServer(), d.getPort(),
					d.getDb(), d.getUser(), d.getPassword(),
					d.getJdbcDriverPath());
			group.addEntry(entry);
		}
	}
}
