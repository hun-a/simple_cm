package cm.performance.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import cm.performance.editor.QueryEditor;
import cm.performance.editor.QueryEditorInput;
import cm.performance.model.HostsEntry;

public class OpenQueryEditorAction extends Action
		implements IWorkbenchAction, ISelectionListener {
	public final static String ID = "cm.performance.action.open";
	private final IWorkbenchWindow window;
	private IStructuredSelection selection;
	
	public OpenQueryEditorAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&Open");
		setToolTipText("Open the Query Editor");
		window.getSelectionService().addSelectionListener(this);
	}

	public void run() {
		Object item = selection.getFirstElement();
		HostsEntry entry = (HostsEntry) item;
		try {
			entry.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		IWorkbenchPage page = window.getActivePage();
		QueryEditorInput input = new QueryEditorInput(entry);
		
		try {
			page.openEditor(input, QueryEditor.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming instanceof IStructuredSelection) {
			selection = (IStructuredSelection) incoming;
			setEnabled(selection.size() == 1
					&& selection.getFirstElement() instanceof HostsEntry);
		} else {
			setEnabled(false);
		}
	}
}
