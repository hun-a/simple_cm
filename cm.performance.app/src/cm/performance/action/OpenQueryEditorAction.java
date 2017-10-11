package cm.performance.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import com.cubrid.common.ui.query.editor.QueryEditorPart;
import com.cubrid.common.ui.query.editor.QueryUnit;

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
		IEditorPart editor = null;
		
		if (page != null) {
			IEditorInput input = new QueryUnit();
			try {
				editor = page.openEditor(input, QueryEditorPart.ID);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}

		if (editor == null) {
			return;
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
