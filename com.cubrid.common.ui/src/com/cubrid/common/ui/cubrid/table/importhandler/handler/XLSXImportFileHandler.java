package com.cubrid.common.ui.cubrid.table.importhandler.handler;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.ui.cubrid.table.control.XlsxRowNumberHandler;
import com.cubrid.common.ui.cubrid.table.importhandler.ImportFileDescription;
import com.cubrid.common.ui.cubrid.table.importhandler.ImportFileHandler;

public class XLSXImportFileHandler implements
ImportFileHandler {

	private static final Logger LOGGER = LogUtil.getLogger(XLSXImportFileHandler.class);
	private final String fileName;
	private ImportFileDescription importFileDescription = null;
	private InputStream workbookSteam = null;
	private SharedStringsTable sharedStringTable = null;
	private XSSFReader reader;

	/**
	 * The constructor
	 *
	 * @param fileName
	 */
	public XLSXImportFileHandler(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Get the source file information
	 *
	 * @return ImportFileDescription
	 * @throws InvocationTargetException Exception in process
	 * @throws InterruptedException The exception
	 */
	public ImportFileDescription getSourceFileInfo() throws InvocationTargetException,
	InterruptedException { // FIXME move this logic to core module
		synchronized (this) {
			if (importFileDescription == null) {
				final XlsxRowNumberHandler xlsxRowNumberHandler = new XlsxRowNumberHandler(
						fileName);
				IRunnableWithProgress runnable = new IRunnableWithProgress() {
					public void run(final IProgressMonitor monitor) {
						monitor.beginTask("", IProgressMonitor.UNKNOWN);
						Thread thread = new Thread("Monitoring cancel") { //$NON-NLS-1$
							public void run() {
								while (monitor != null && !monitor.isCanceled()
										&& !xlsxRowNumberHandler.isEnd()) {
									try {
										sleep(100);
									} catch (InterruptedException e) {
										LOGGER.error(e.getMessage(), e);
									}
								}
								if (monitor != null && monitor.isCanceled()) {
									xlsxRowNumberHandler.setCancel(true);
									monitor.done();
								}
							}
						};
						thread.start();
						xlsxRowNumberHandler.process();
						monitor.done();
					}
				};

				PlatformUI.getWorkbench().getProgressService().busyCursorWhile(
						runnable);

				List<Integer> itemsNumberOfSheets = null;
				int totalRowCount = 0;
				List<String> colsLst = xlsxRowNumberHandler.getHeadInfo();
				if (xlsxRowNumberHandler.isCancel()) {
					throw new InterruptedException();
				} else {
					totalRowCount = xlsxRowNumberHandler.getNumberOfAllRow();
					itemsNumberOfSheets = xlsxRowNumberHandler.getItemsNumberOfSheets();
				}
				int sheetNum = 0;
				if (itemsNumberOfSheets != null) {
					sheetNum = itemsNumberOfSheets.size();
				}
				importFileDescription = new ImportFileDescription(
						totalRowCount, sheetNum, colsLst);
				importFileDescription.setItemsNumberOfSheets(itemsNumberOfSheets);
			}
			return importFileDescription;
		}
	}

	/**
	 *
	 * Get the sheets
	 *
	 * @return List<InputStream>
	 * @throws IOException The exception
	 * @throws OpenXML4JException The exception
	 */
	public List<InputStream> getSheets() throws IOException, OpenXML4JException { // FIXME move this logic to core module
		synchronized (this) {
			if (reader == null) {
				try {
					workbookSteam = new BufferedInputStream(
							new FileInputStream(fileName));
					OPCPackage pkg = OPCPackage.open(workbookSteam);
					reader = new XSSFReader(pkg);
				} catch (OutOfMemoryError error) {
					throw new RuntimeException(error);
				}
			}
			sharedStringTable = reader.getSharedStringsTable();
			Iterator<InputStream> sheetsIt = reader.getSheetsData();
			List<InputStream> sheets = new ArrayList<InputStream>();
			while (sheetsIt.hasNext()) {
				sheets.add(sheetsIt.next());
			}
			return sheets;
		}
	}

	/**
	 *
	 * Get the shared string table
	 *
	 * @return SharedStringsTable
	 * @throws IOException The exception
	 * @throws OpenXML4JException The exception
	 */
	public SharedStringsTable getSharedStringsTable() throws IOException,
	OpenXML4JException { // FIXME move this logic to core module
		synchronized (this) {
			if (sharedStringTable == null) {
				getSheets();
			}
			return sharedStringTable;
		}
	}

	/**
	 *
	 * Close the workbook stream
	 *
	 */
	public void dispose() {
		synchronized (this) { // FIXME move this logic to core module
			try {
				if (workbookSteam != null) {
					workbookSteam.close();
				}
			} catch (Exception e) {
				LOGGER.error("", e);
			}
			workbookSteam = null;
			reader = null;
			importFileDescription = null;
		}
	}

}
