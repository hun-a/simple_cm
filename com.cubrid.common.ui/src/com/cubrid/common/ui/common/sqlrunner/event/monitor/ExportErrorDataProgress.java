package com.cubrid.common.ui.common.sqlrunner.event.monitor;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.ui.common.Messages;
import com.cubrid.common.ui.common.sqlrunner.model.SqlRunnerFailed;

public class ExportErrorDataProgress implements IRunnableWithProgress {
	private static final Logger LOGGER = LogUtil.getLogger(ExportErrorDataProgress.class);
	private boolean success = false;

	private final String filePath;
	private final String charset;
	private final Map<String, List<SqlRunnerFailed>> failedListMap;
	private String errMsg;

	public ExportErrorDataProgress(Map<String, List<SqlRunnerFailed>> failedListMap, String filePath, String fileName, String charset) {
		this.filePath = filePath + "error_" + fileName + ".xls";
		this.charset = charset;
		this.failedListMap = failedListMap;

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException { // FIXME logic code move to core module
		WritableWorkbook wwb = null;
		try {
			WorkbookSettings workbookSettings = new WorkbookSettings();
			workbookSettings.setEncoding(charset);
			wwb = Workbook.createWorkbook(new File(filePath), workbookSettings);
			int index = 0;
			for(Entry<String, List<SqlRunnerFailed>> entry : failedListMap.entrySet()) {
				WritableSheet ws = wwb.createSheet(entry.getKey(), index ++ );
				WritableCellFormat normalCellStyle = getNormalCell();
				WritableCellFormat titleCellStyle = getTitleCell();
				jxl.write.Label header = new jxl.write.Label(0, 0, Messages.failedSQLlineNumber, titleCellStyle);
				ws.addCell(header);
				jxl.write.Label header1 = new jxl.write.Label(1, 0, Messages.failedSQL, titleCellStyle);
				ws.addCell(header1);
				jxl.write.Label header2 = new jxl.write.Label(2, 0, Messages.failedErrorMessage, titleCellStyle);
				ws.addCell(header2);
				ws.setColumnView(1, 100);
				ws.setColumnView(2, 80);
				for (int j = 0; j < entry.getValue().size(); j ++) {
					SqlRunnerFailed failedInfo = entry.getValue().get(j);
					int row = j + 1;
					jxl.write.Number lineNmuber = new jxl.write.Number(0, row, failedInfo.getLineIndex(), normalCellStyle);
					ws.addCell(lineNmuber);
					jxl.write.Label sql = new jxl.write.Label(1, row, failedInfo.getSql(), normalCellStyle);
					ws.addCell(sql);
					jxl.write.Label errMessage = new jxl.write.Label(2, row, failedInfo.getErrorMessage(), normalCellStyle);
					ws.addCell(errMessage);
				}
			}
			wwb.write();
			success = true;
		} catch (Exception e) {
			LOGGER.error("write excel error", e);
			errMsg = e.getMessage();
		} finally {
			if (wwb != null) {
				try {
					wwb.close();
				} catch (Exception e) {
					LOGGER.error("close excel error", e);
				}
			}
		}

	}

	/**
	 * export to excel
	 * @return
	 */
	public boolean export() {
		Display display = Display.getDefault();
		display.syncExec(new Runnable() {
			public void run() {
				try {
					new ProgressMonitorDialog(null).run(true, false,
							ExportErrorDataProgress.this);
				} catch (Exception e) {
					LOGGER.error("", e);
				}
			}
		});

		return success;
	}

	/**
	 * getNormalCell
	 * @return WritableCellFormat
	 */
	public static WritableCellFormat getNormalCell(){ // FIXME logic code move to core module
		WritableFont font = new  WritableFont(WritableFont.TIMES, 12);
		WritableCellFormat format = new  WritableCellFormat(font);
		try {
			format.setAlignment(jxl.format.Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);
			format.setWrap(true);
		} catch (WriteException e) {
			LOGGER.error("", e);
		}
		return format;
	}

	/**
	 * getNormalCell
	 * @return WritableCellFormat
	 */
	public static WritableCellFormat getTitleCell(){
		WritableFont font = new WritableFont(WritableFont.TIMES, 12);
		WritableCellFormat format = new WritableCellFormat(font);
		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);
			format.setWrap(true);
		} catch (WriteException e) {
			LOGGER.error("", e);
		}
		return format;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getErrMsg() {
		return errMsg;
	}
}
