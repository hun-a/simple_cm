package com.cubrid.common.ui.cubrid.procedure;

import org.eclipse.osgi.util.NLS;

import com.cubrid.common.ui.CommonUIPlugin;

public class Messages extends
NLS {

	static {
		NLS.initializeMessages(CommonUIPlugin.PLUGIN_ID
				+ ".cubrid.procedure.Messages", Messages.class);
	}
	public static String errInput;
	// add Function params
	public static String titleAddFuncParamDialog;
	public static String msgEditFuncParamDialog;
	public static String titleEditFuncParamDialog;
	public static String lblParameterName;
	public static String lblParameterDescription;
	public static String lblSqlType;
	public static String lblSpecialJavaType;
	public static String lblJavaType;
	public static String lblParamModel;
	public static String msgAddFuncParamDialog;
	public static String errInputParameterName;
	public static String errInputParameterNameLength;
	public static String errInputParameterNameValid;
	public static String errInputParameterNameDuplicate;
	public static String errInputSqlType;
	public static String msgSelectJavaConfirm;
	public static String msgSelectSpecialJavaConfirm;
	public static String errValidJavaFunctionName;
	// Edit function
	public static String tabItemFuncSetting;
	public static String tabItemSQLScript;
	public static String lblFunctionName;
	public static String lblFunctionDescription;
	public static String tblColFunctionParamName;
	public static String tblColFunctionParamType;
	public static String tblColFunctionJavaParamType;
	public static String tblColFunctionModel;
	public static String tblColFunctionMemo;
	public static String lblJavaFunctionName;
	public static String lblReturnJavaType;
	public static String titleEditFunctionDialog;
	public static String msgEditFunctionDialog;
	public static String titleAddFunctionDialog;
	public static String msgAddFunctionDialog;
	public static String lblReturnSQLType;
	public static String btnAddParameter;
	public static String btnEditParameter;
	public static String btnDropParameter;
	public static String btnUpParameter;
	public static String btnDownParameter;
	public static String titleSuccess;
	public static String msgEditFunctionSuccess;
	public static String msgAddFunctionSuccess;
	public static String errInputFunctionName;
	public static String errInputFunctionNameLength;
	public static String errInputJavaFunctionName;
	public static String errInputSelectSqlType;
	public static String errInputSpecialJavaType;
	public static String errInputSelectJavaType;
	public static String msgVoidReturnType;
	// Edit procedure
	public static String tabItemProcSetting;
	public static String lblProcedureName;
	public static String lblProcedureDescription;
	public static String tabItemProcSQLScript;
	public static String tblColProcedureParamName;
	public static String tblColProcedureParamType;
	public static String tblColProcedureJavaParamType;
	public static String tblColProcedureModel;
	public static String tblColProcedureMemo;
	public static String titleEditProcedureDialog;
	public static String msgEditProcedureDialog;
	public static String titleAddProcedureDialog;
	public static String msgAddProcedureDialog;
	public static String msgEditProcedureSuccess;
	public static String msgAddProcedureSuccess;
	public static String errInputProcedureName;
	public static String errInputJavaProcedureName;
	//delete function
	public static String msgSureDropFunction;
	public static String errSelectFunction;
	public static String msgSureDropProcedure;
	public static String errSelectProcedure;
	public static String msgDeleteProcedureSuccess;
	public static String msgDeleteFunctionSuccess;
	public static String errDuplicateName;
	public static String errNotExistName;
}