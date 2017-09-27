package com.cubrid.common.ui.common.sqlrunner.part;

import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.cubrid.common.core.util.StringUtil;
import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;

public class RunSQLFileEditorInput implements IEditorInput {
	private final List<String> fileList;
	private final CubridDatabase database;
	
	private int maxThreadSize = 1;
	private int commitCount = 1000;
	private String charset = "UTF-8";
	private String logFolderPath = Platform.getInstanceLocation().getURL().getPath();

	/**
	 * The constructor
	 * @param database
	 * @param fileList
	 * @param charset
	 * @param maxThreadSize
	 * @param commitCount
	 * @param logFolderPath
	 */
	public RunSQLFileEditorInput(CubridDatabase database, List<String> fileList, String charset, int maxThreadSize,int commitCount, String logFolderPath) {
		this.database = database;
		this.fileList = fileList;
		this.charset = charset;
		this.maxThreadSize = maxThreadSize;
		this.commitCount = commitCount;
		if(!StringUtil.isEmpty(logFolderPath)) {
			this.logFolderPath = logFolderPath;
		}
	}
	
	/**
	 * The constructor
	 * @param database
	 * @param fileList
	 */
	public RunSQLFileEditorInput(CubridDatabase database, List<String> fileList) {
		this.database = database;
		this.fileList = fileList;
	}

	public boolean exists() {
		return false;
	}

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter) {
		return null;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public String getName() {
		DatabaseInfo dbInfo = database.getDatabaseInfo();
		if (dbInfo == null) {
			return "";
		}
		return database.getName() + "@" + dbInfo.getBrokerIP();
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return this.getName();
	}

	public CubridDatabase getDatabase() {
		return database;
	}

	/**
	 * Get Max Thread Size
	 * 
	 * @return the maxThreadSize
	 */
	public int getMaxThreadSize() {
		return maxThreadSize;
	}

	/**
	 * Get Commit Count
	 * 
	 * @return the commitCount
	 */
	public int getCommitCount() {
		return commitCount;
	}

	/**
	 * Get Charset
	 * 
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * Get Log Folder Path
	 * 
	 * @return the logFolderPath
	 */
	public String getLogFolderPath() {
		return logFolderPath;
	}

	/**
	 * Get file List
	 * @return the fileList
	 */
	public List<String> getFileList() {
		return fileList;
	}
}
