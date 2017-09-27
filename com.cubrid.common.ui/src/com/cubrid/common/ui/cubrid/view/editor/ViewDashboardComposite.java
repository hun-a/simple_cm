package com.cubrid.common.ui.cubrid.view.editor;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

import com.cubrid.common.core.common.model.DBAttribute;
import com.cubrid.common.core.common.model.SchemaInfo;
import com.cubrid.common.ui.query.Messages;
import com.cubrid.cubridmanager.core.cubrid.table.model.DataType;

public class ViewDashboardComposite extends Composite {


	private final CTabFolder tabFolder;
	private TableViewer columnTableView;
	
	private CTabItem tabItem ; 

	public ViewDashboardComposite(CTabFolder parent,int style) {
		super(parent, style);
		this.tabFolder = parent;
		GridLayout tLayout = new GridLayout(1, true);
		tLayout.verticalSpacing = 0;
		tLayout.horizontalSpacing = 0;
		tLayout.marginWidth = 0;
		tLayout.marginHeight = 0;

		setLayout(tLayout);
	}

	/**
	 * Create the SQL history composite
	 */
	public void initialize() {
		Composite tableComp = new Composite(tabFolder, SWT.NONE);
		{
			tableComp.setLayoutData(new GridData(GridData.FILL_BOTH));
			tableComp.setLayout(new GridLayout());
		}
		createColumnsTable(tableComp);
		
		tabItem = new ViewsDetailInfoCTabItem(tabFolder, SWT.NONE, this);
		tabItem.setControl(tableComp);
		tabItem.setShowClose(true);
		tabFolder.setSelection(tabItem);
		
	}
	
	public void setInput(SchemaInfo schema) {
		tabItem.setText(schema == null ? "" : schema.getClassname());
		columnTableView.setInput(schema.getAttributes());
		columnTableView.refresh();
		pack();
	}
	
	/**
	 * Create the column information table
	 * 
	 */
	private void createColumnsTable(Composite topComposite) {
		columnTableView = new TableViewer(topComposite, SWT.V_SCROLL
				| SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.FULL_SELECTION);
		
		final TableColumn nameColumn = new TableColumn(columnTableView.getTable(), SWT.NONE);
		nameColumn.setAlignment(SWT.LEFT);
		nameColumn.setText(Messages.tblColViewName);
		
		final TableColumn typeColumn = new TableColumn(columnTableView.getTable(), SWT.NONE);
		typeColumn.setAlignment(SWT.LEFT);
		typeColumn.setText(Messages.tblColViewDataType);
		
		columnTableView.getTable().setLinesVisible(true);
		columnTableView.getTable().setHeaderVisible(true);
		columnTableView.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
			
		columnTableView.setContentProvider(new ViewsColumnViewerContentProvider());
		columnTableView.setLabelProvider(new ViewsColumnViewerLabelProvider());
	}

	/**
	 * getColumnTableView columnTableView
	 * @return
	 */
	public TableViewer getColumnTableView() {
		return columnTableView;
	}

	/*
	 * A self CTabItem class ,can store ViewsDetailInfoViewInfoComposite
	 */
	public class ViewsDetailInfoCTabItem extends CTabItem {
		private ViewDashboardComposite viewInfoComposite;
		public ViewsDetailInfoCTabItem(CTabFolder parent, int style, ViewDashboardComposite viewInfoComposite) {
			super(parent, style);
			this.viewInfoComposite = viewInfoComposite;
		}
		public ViewDashboardComposite getViewInfoComposite() {
			return viewInfoComposite;
		}
		public void setViewInfoComposite(
				ViewDashboardComposite viewInfoComposite) {
			this.viewInfoComposite = viewInfoComposite;
		}
	}
	
	public void pack () {
		for (int i = 0; i < columnTableView.getTable().getColumnCount(); i++) {
			TableColumn column = columnTableView.getTable().getColumn(i);
			column.pack();
			if (column.getWidth() > 400) {
				column.setWidth(400);
			}
			if (column.getWidth() < 200) {
				column.setWidth(200);
			}
		}
	}
	
	/**
	 * view table content provider
	 * @author fulei
	 *
	 */
	public class ViewsColumnViewerContentProvider implements
	IStructuredContentProvider {
		/**
		 * getElements
		 *
		 * @param inputElement Object
		 * @return Object[]
		 */
		@SuppressWarnings("unchecked")
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof List) {
				List<DBAttribute> list = (List<DBAttribute>) inputElement;
				DBAttribute[] nodeArr = new DBAttribute[list.size()];
				return list.toArray(nodeArr);
			}
		
			return new Object[]{};
		}
		
		/**
		 * dispose
		 */
		public void dispose() {
		}
		
		/**
		 * inputChanged
		 *
		 * @param viewer Viewer
		 * @param oldInput Object
		 * @param newInput Object
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// do nothing
		}
	}
	
	/**
	 * view table label provider
	 * @author Administrator
	 *
	 */
	public class ViewsColumnViewerLabelProvider extends LabelProvider implements
	ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof DBAttribute) {
				DBAttribute attribute = (DBAttribute)element;
				if (attribute != null) {
					switch (columnIndex) {
						case 0 : return attribute.getName();
						case 1 : return DataType.getShownType(attribute.getType());
					}
				}
			}
			return null;
		}
	}
	
}
