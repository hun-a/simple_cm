package com.cubrid.common.ui.er;

import org.eclipse.osgi.util.NLS;

import com.cubrid.common.ui.CommonUIPlugin;

public class Messages extends
NLS {
	static {
		NLS.initializeMessages(CommonUIPlugin.PLUGIN_ID
				+ ".er.Messages", Messages.class);
	}

	public static String okBTN;
	public static String cancelBTN;
	public static String titleChoose;

	public static String errNoDatabase;
	public static String msgDBNotLogin;
	public static String msgChooseSaveFileType;
	public static String titleExport;
	public static String msgExportSuccess;
	public static String errExportData;
	public static String errOldNewValueBothNull;

	//check name
	public static String errInvalidName;
	public static String errEmptyColumn;
	public static String errColumnFormat;
	public static String errColumnType;

	//action name
	public static String actionRenameTableName;
	public static String actionAddTablesName;
	public static String btnCompareDDL;
	public static String btnTipSyncComments;
	public static String btnTipGenerateSyncCommentsSQL;
	public static String actionDeleteColumnName;
	public static String actionAddColumnName;
	public static String actionDeleteTable;
	public static String actionDeleteRelationship;
	public static String actionDelete;
	public static String actionZoomIn;
	public static String actionZoomOut;
	public static String errExportNoTable;
	public static String cannotDragSystemTable;

	public static String errorAddTables;

	public static String btnTipCannotChangeDB;
	public static String btnTipOpenFile;
	public static String btnTipSaveFile;
	public static String btnTipSaveAsFile;
	public static String btnTipExportSQLData;
	public static String btnAutoLayout;
	public static String btnTipConnection;
	public static String btnTipCreateTable;

	public static String msgConfirmDelete;
	public static String msgConfirmDeleteTableList;
	public static String msgConfirmDeleteColumnList;
	public static String msgConfirmDeleteLineCount;
	public static String msgConfirmLoadERFile;
	public static String titleImportSchemaData;
	public static String msgImportSchemaData;
	public static String errExistTables;
	public static String cannotBeBuiltFK;

	//add column
	public static String keyTable;
	public static String keyView;
	public static String titleAddColumn;
	public static String msgAddColumn;
	public static String lbAddColumnName;
	public static String lbDataType;
	public static String errInvalidColumnName;
	public static String errExistColumnName;
	public static String errColumnDataType;

	//add/edit/delete fk
	public static String errAddFKExist;
	public static String errAddFKNoPK;
	public static String actionEditTableName;
	public static String errFKColumnMatch;

	public static String errFKcolumnSize;
	public static String errNotExistRefedCol;
	public static String errNotExistRefCol;
	public static String errNotExistColInTable;
	public static String errDonotMatchDataType;

	public static String errChangeColNameInFK;
	public static String errChangeColTypeInFK;
	public static String errCancelExistedPK;
	public static String errAddNewPK;
	public static String errDeleteColInFK;
	public static String errDeleteColByRefed;
	public static String errDragTableSource;
	public static String errNotExistDraggedTable;

	public static String errColumnName;
	public static String errMatchDefaultValue;

	//search
	public static String tipSearchTableName;

	//default column name relationship(between logic model and physical model) keys
	public static String colNameMapKey_name;
	public static String colNameMapKey_role;
	public static String colNameMapKey_birthday;
	public static String colNameMapKey_country;
	public static String colNameMapKey_city;
	public static String colNameMapKey_area;
	public static String colNameMapKey_time;
	public static String colNameMapKey_gender;
	public static String colNameMapKey_age;

	//physical and logic map relationship
	public static String physicalModeltItemName;
	public static String logicalModeltItemName;
	public static String errMultiRelationship;
	public static String btnNameSetPhysicalLogicalMap;
	public static String namePhysicalLogicalMapDlg;
	public static String titlePhysicalLogicalMapDlg;
	public static String msgPhysicalLogicalMapDlg;
	public static String lblMsgPhysicalLogicalExample;
	public static String msgConfirmChangePhysicalLogicalMap;
	public static String lblTableName;
	public static String lblTableColumnName;
	public static String lblTableColumnDataType;
	public static String tblcolumnPhysical;
	public static String tblcolumnLogical;
	public static String btnDelItem;
	public static String errEmptyName;
	public static String lblPhysicalTableName;
	public static String lblLogicalTableName;
	public static String tabItemNameModelRelation;
	public static String msgConfirmImportRelationMap;
	public static String errDuplicateDataType;
	public static String cannotSyncComments;
	public static String msgConfirmSyncComments;
	public static String msgSuccessSyncComments;
	public static String errSyncComments;
	public static String edittabNameInsertSQL;
	public static String edittabNameUpdateSQL;
	public static String edittabNameAlterSQL;
	public static String msgNoComments;
}
