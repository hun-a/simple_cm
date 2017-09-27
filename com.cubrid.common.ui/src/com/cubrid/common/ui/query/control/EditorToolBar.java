package com.cubrid.common.ui.query.control;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.cubrid.common.ui.query.editor.QueryEditorPart;
import com.cubrid.common.ui.spi.action.ActionManager;
import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.common.ui.spi.util.CommonUITool;

public final class EditorToolBar extends ToolBar {
	
	private CLabel selectDbLabel;
	private Button dropDownButton;
	private final DatabaseNavigatorMenu dbMenu;
	private int SELECTDBLABEL_LENTH = 180;
	private int SELECTDBBUTTON_LENTH = 20;

	/**
	 * Create the composite
	 * 
	 * @param parent Composite
	 * @param editor QueryEditorPart
	 */
	public EditorToolBar(Composite parent, QueryEditorPart editor) {
		super(parent, SWT.WRAP | SWT.FLAT);
		CreateSelectItem(parent);
		dbMenu = loadDbNavigatorMenu();
		dbMenu.setEditor(editor);
		init(parent);
	}

	private void CreateSelectItem(Composite parent) {
		ToolItem selectDbItem = new ToolItem(this, SWT.SEPARATOR);
		Composite comp = createDropDownComp();
		selectDbItem.setControl(comp);
		selectDbItem.setWidth(SELECTDBLABEL_LENTH + SELECTDBBUTTON_LENTH);
		new ToolItem(this, SWT.SEPARATOR | SWT.VERTICAL);
	}

	private void init(Composite parent) {
		dbMenu.setParent(parent);
		dbMenu.setSelectDbLabel(selectDbLabel);
		dbMenu.loadDatabaseMenu();
		//dbMenu.selectMenuItem(dbMenu.getNullDbMenuItem());

			dropDownButton.addListener(SWT.Selection, new Listener() {
				
				public void handleEvent(Event event) {
					handleSelectionEvent();
				}
			});
			selectDbLabel.addListener(SWT.MouseUp, new Listener() {
				
				public void handleEvent(Event event) {
					handleSelectionEvent();
				}
			});
	}

	/**
	 * 
	 * Load the database selection pop-up menu from extension points
	 * 
	 */
	public static DatabaseNavigatorMenu loadDbNavigatorMenu() {
		return ActionManager.getInstance().getMenuProvider().getDatabaseNavigatorMenu();
	}

	/**
	 * Handle selection event.
	 * 
	 */
	private void handleSelectionEvent() {
		Rectangle rect = selectDbLabel.getBounds();
		Point pt = new Point(rect.x, rect.y + rect.height);
		pt = selectDbLabel.toDisplay(pt);
		dbMenu.loadDatabaseMenu();
		dbMenu.getDbSelectionMenu().setLocation(pt);
		dbMenu.getDbSelectionMenu().setVisible(true);
	}

	/**
	 * create drop down composite
	 * 
	 * @return comp composite
	 */
	private Composite createDropDownComp() {
		Composite comp = new Composite(this, SWT.NONE);
		final GridLayout gdLayout = new GridLayout(2, false);
		gdLayout.marginHeight = 0;
		gdLayout.marginWidth = 0;
		gdLayout.horizontalSpacing = -1;
		gdLayout.verticalSpacing = 0;
		comp.setLayout(gdLayout);
		comp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		selectDbLabel = new CLabel(comp, SWT.CENTER | SWT.SHADOW_OUT);
		selectDbLabel.setLayoutData(CommonUITool.createGridData(1, 1, SELECTDBLABEL_LENTH, -1));
		selectDbLabel.setText(DatabaseNavigatorMenu.NO_DATABASE_SELECTED_LABEL);
		dropDownButton = new Button(comp, SWT.FLAT | SWT.ARROW | SWT.DOWN);
		dropDownButton.setLayoutData(CommonUITool.createGridData(1, 1, SELECTDBBUTTON_LENTH, -1));
		return comp;
	}

	/**
	 * when tree node in navigation view change, refresh the database list
	 */
	public void refresh() {
		dbMenu.refresh();
	}

	/**
	 * @see org.eclipse.swt.widgets.ToolBar#checkSubclass()
	 */
	
	protected void checkSubclass() {
		// do not check subclass
	}

	/**
	 * set the database
	 * 
	 * @param database CubridDatabase
	 */
	public void setDatabase(CubridDatabase database) {
		dbMenu.setDatabase(database);
	}

	/**
	 * get selected database
	 * 
	 * @return dbSelectd
	 */
	public CubridDatabase getSelectedDb() {
		return (CubridDatabase)dbMenu.getSelectedDb();
	}

	public CubridDatabase[] getDatabaseOnMenu() {
		List<CubridDatabase> databases = new ArrayList<CubridDatabase>();
		for (MenuItem item : dbMenu.getDbSelectionMenu().getItems()) {
			if (item.getStyle() != SWT.RADIO)
				continue;

			if (!item.getEnabled())
				continue;
			databases.add(((DatabaseMenuItem)item).getDatabase());
		}
		return databases.toArray(new CubridDatabase[0]);
	}

	/**
	 * inject custom operation when database changed
	 * 
	 * @param listener Listener
	 */
	public void addDatabaseChangedListener(Listener listener) {
		dbMenu.addDatabaseChangedListener(listener);
	}

	/**
	 * if no database selected
	 * 
	 * @return boolean
	 */
	public boolean isNull() {
		return dbMenu.isNull();
	}

}