package cm.performance.editor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import cm.performace.jdbc.CUBRIDUtils;
import cm.performance.model.HostsEntry;


public class QueryEditor extends EditorPart {
	public static final String ID = "cm.performance.app.editors.queryEditor";
	private final String NAME = "Query Editor";
	private Text tableViewer;
	private StyledText userInputArea;
	private HostsEntry entry;

	public QueryEditor() {
	}
	
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		setPartName(NAME);
		entry = ((QueryEditorInput) input).getEntry();
	}
	
	public void createPartControl(Composite parent) {
		Composite queryEditor = new Composite(parent, SWT.NONE);
		FillLayout layout = new FillLayout(SWT.VERTICAL);
		queryEditor.setLayout(layout);
		
		userInputArea = new StyledText(queryEditor, SWT.BORDER | SWT.MULTI | SWT.WRAP);
		tableViewer = new Text(queryEditor, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		tableViewer.setEditable(false);
	}
	
	public void setFocus() {
		if (userInputArea != null && !userInputArea.isDisposed()) {
			userInputArea.setFocus();
		}
	}

	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public HostsEntry getEntry() {
		return entry;
	}
	
	public String getSql() {
		return userInputArea.getText();
	}
	
	public void appendResultsToTableView(String result) {
		tableViewer.append(result);
		tableViewer.setSelection(0);
	}
	
	public void clearTableView() {
		tableViewer.setText("");
	}
	
	public void dispose() {
		CUBRIDUtils.close(entry.getConnection());
	}
}
