package com.cubrid.common.ui.cubrid.serial;

import org.eclipse.osgi.util.NLS;

import com.cubrid.common.ui.CommonUIPlugin;

public class Messages extends
NLS {

	static {
		NLS.initializeMessages(CommonUIPlugin.PLUGIN_ID
				+ ".cubrid.serial.Messages", Messages.class);
	}
	public static String msgConfirmDelSerial;
	public static String titleCreateSerialDialog;
	public static String msgCreateSerialDialog;
	public static String lblSerialName;
	public static String lblSerialDescription;
	public static String lblStartValue;
	public static String lblCurrentValue;
	public static String lblIncrementValue;
	public static String lblMinValue;
	public static String lblMaxValue;
	public static String btnNoMinValue;
	public static String btnNoMaxValue;
	public static String lblCacheCount;
	public static String btnNoCache;
	public static String btnCycle;
	public static String titleEditSerialDialog;
	public static String msgEditSerialDialog;
	public static String errSerialName;
	public static String errSerialNameLength;
	public static String errStartValue;
	public static String msgStartValue;
	public static String msgCurrentValue;
	public static String errIncrementValue;
	public static String errMinValue;
	public static String errMaxValue;
	public static String errSerialExist;
	public static String errValue;
	public static String errDiffValue;
	public static String errCacheCount;
	public static String grpGeneral;
	public static String grpSqlScript;

	public static String delSerialTaskName;
	public static String loadSerialTaskName;
	public static String createSerialTaskName;
	public static String editSerialTaskName;

	public static String errNameNotExist;

	//serial dashboard
	public static String serialsDetailInfoPartTitle;
	public static String serialsDetailInfoPartTableNameCol;
	public static String serialsDetailInfoPartTableCurValCol;
	public static String serialsDetailInfoPartTableIncreValCol;
	public static String serialsDetailInfoPartTableMinValCol;
	public static String serialsDetailInfoPartTableMaxValCol;
	public static String serialsDetailInfoPartTableCacheNumCol;
	public static String serialsDetailInfoPartTableCycleCol;
	public static String serialsDetailInfoPartCreateSerialBtn;
	public static String serialsDetailInfoPartEditSerialBtn;
	public static String serialsDetailInfoPartDropSerialBtn;

	public static String errSerialNoSelection;
}