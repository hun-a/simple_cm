package cm.performance.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddHostDialog extends Dialog {
	private Text serverText;
	private Text portText;
	private Text dbText;
	private Text userText;
	private Text passwordText;
	private Text jdbcDriverPathText;
	
	private String server;
	private int port;
	private String db;
	private String user;
	private String password;
	private String jdbcDriverPath;
	
	public AddHostDialog(Shell parentShell) {
		super(parentShell);
	}
	
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Add Host");
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);

		Label serverLabel = new Label(composite, SWT.NONE);
		serverLabel.setText("&Server:");
		GridData labelGridData = new GridData(GridData.END, GridData.CENTER,
				false, false); 
		serverLabel.setLayoutData(labelGridData);

		serverText = new Text(composite, SWT.BORDER);
		GridData textGridData = new GridData(GridData.FILL, GridData.FILL,
				true, false);
		textGridData.horizontalSpan = 2;
		textGridData.widthHint = 150;
		serverText.setLayoutData(textGridData);
		
		Label portLabel = new Label(composite, SWT.NONE);
		portLabel.setText("&Port:");
		portLabel.setLayoutData(labelGridData);
		
		portText = new Text(composite, SWT.BORDER);
		portText.setLayoutData(textGridData);
		
		Label dbLabel = new Label(composite, SWT.NONE);
		dbLabel.setText("&Db:");
		dbLabel.setLayoutData(labelGridData);
		
		dbText = new Text(composite, SWT.BORDER);
		dbText.setLayoutData(textGridData);
		
		Label userLabel = new Label(composite, SWT.NONE);
		userLabel.setText("&User:");
		userLabel.setLayoutData(labelGridData);
		
		userText = new Text(composite, SWT.BORDER);
		userText.setLayoutData(textGridData);
		
		Label passwordLabel = new Label(composite, SWT.NONE);
		passwordLabel.setText("&Password:");
		passwordLabel.setLayoutData(labelGridData);
		
		passwordText = new Text(composite, SWT.BORDER);
		passwordText.setLayoutData(textGridData);
		
		Label jdbcDriverPathLabel = new Label(composite, SWT.NONE);
		jdbcDriverPathLabel.setText("&JDBC path:");
		jdbcDriverPathLabel.setLayoutData(labelGridData);
		
		jdbcDriverPathText = new Text(composite, SWT.BORDER);
		jdbcDriverPathText.setEnabled(false);
		GridData jdbcGridData = new GridData(GridData.END, GridData.CENTER,
				false, false);
		jdbcGridData.widthHint = 120;
		jdbcDriverPathText.setLayoutData(jdbcGridData);
		
		Button jdbcDriverPathButton = new Button(composite, SWT.NONE);
		jdbcDriverPathButton.setText("file");
		jdbcDriverPathButton.pack();
		jdbcDriverPathButton.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				FileDialog d = new FileDialog(getShell(), SWT.OPEN);
				d.setFilterExtensions(new String[] {"*.jar"});
				jdbcDriverPath = d.open();
				jdbcDriverPathText.setText(jdbcDriverPath);
			}
		});
		
		return composite;
	}
	
	protected void okPressed() {
		if (portText.getText().equals("")) {
			MessageDialog.openError(getShell(), "Invalid Port", "Port must not be blank.");
			return;
		}
		server = serverText.getText();
		port = Integer.parseInt(portText.getText());
		db = dbText.getText();
		user = userText.getText();
		password = passwordText.getText();
		
		if (!server.equals("") && (port > 0 && port <= 65535)
				&& !db.equals("") && !user.equals("")
				&& !jdbcDriverPath.equals("")) {
			super.okPressed();
		} else {
			MessageDialog.openError(getShell(), "Invalid Input", "Try again.");
			return;
		}
	}
	
	public String getServer() {
		return server;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getDb() {
		return db;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getJdbcDriverPath() {
		return jdbcDriverPath;
	}
}
