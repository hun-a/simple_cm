package com.cubrid.common.ui.query.format;

import org.cubrid.sqlformatter.SqlFormatManager;
import org.cubrid.sqlformatter.SqlFormatOptions;
import org.eclipse.jface.text.formatter.IFormattingStrategy;

import com.cubrid.common.core.util.StringUtil;
import com.cubrid.common.ui.query.editor.IDatabaseProvider;
import com.cubrid.common.ui.spi.persist.QueryOptions;
import com.cubrid.cubridmanager.core.common.model.ServerInfo;

public class SqlFormattingStrategy implements
IFormattingStrategy {

	private final IDatabaseProvider databaseProvider;

	public SqlFormattingStrategy() {
		databaseProvider = null;
	}

	public SqlFormattingStrategy(IDatabaseProvider databaseProvider) {
		this.databaseProvider = databaseProvider;
	}

	/**
	 * @see org.eclipse.jface.text.formatter.IFormattingStrategy#format(java.lang.String,
	 *      boolean, java.lang.String, int[])
	 * @param content the initial string to be formatted
	 * @param isLineStart indicates whether the beginning of content is a line
	 *        start in its document
	 * @param indentation the indentation string to be used
	 * @param positions the character positions to be updated
	 * @return the formatted string
	 */
	public String format(String content, boolean isLineStart,
			String indentation, int[] positions) {
		return format(content);
	}

	/**
	 * Format the SQL content
	 *
	 * @param content String
	 * @return String
	 */
	public String format(String content) {
		if (StringUtil.isEmpty(content)) {
			return "";
		}

		ServerInfo serverInfo = databaseProvider == null ? null : databaseProvider.getServerInfo();
		boolean isLowerCase = QueryOptions.getKeywordLowercase(serverInfo);
		SqlFormatOptions options = new SqlFormatOptions();
		options.setUpperCaseKeyword(!isLowerCase);

		SqlFormatManager formattingManager = new SqlFormatManager();
		return formattingManager.format(content, options);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.formatter.IFormattingStrategy#formatterStarts(java.lang.String)
	 */
	@Override
	public void formatterStarts(String initialIndentation) {
		//empty
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.formatter.IFormattingStrategy#formatterStops()
	 */
	@Override
	public void formatterStops() {
		//empty
	}

}
