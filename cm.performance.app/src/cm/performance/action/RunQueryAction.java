package cm.performance.action;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import cm.performace.jdbc.CUBRIDRunner;
import cm.performance.editor.QueryEditor;

public class RunQueryAction extends Action
		implements IWorkbenchAction {
	public final static String ID = "cm.performance.action.runQuery";
	private final IWorkbenchWindow window;
	
	public RunQueryAction(IWorkbenchWindow window) {
		this.window = window;
		setId(ID);
		setText("&Run Query");
		setToolTipText("Run the query");
	}
	
	public void dispose() {
	}
	
	public void run() {
		QueryEditor editor = null;
		IEditorReference[] refs = window.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getEditorReferences();
		for (int i = 0; i < refs.length; i++) {
			if (refs[i].getId().equals(QueryEditor.ID)) {
				editor = (QueryEditor) refs[i].getEditor(true);
				break;
			}
		}
		
		if (editor == null || editor.getEntry().getConnection() == null) {
			return;
		}
		
		String sql = editor.getSql();
		if (sql.equals("")) {
			return;
		}
		
		StringBuilder result = new StringBuilder();
		editor.clearTableView();
		try {
			ResultSet rs = CUBRIDRunner.select(editor.getEntry().getConnection(), sql);
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				result.append(meta.getColumnName(i) + "\t");
			}
			result.append("\n");
			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					result.append(rs.getObject(i) + "\t");
				}
				result.append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		editor.appendResultsToTableView(result.toString());
	}

}
