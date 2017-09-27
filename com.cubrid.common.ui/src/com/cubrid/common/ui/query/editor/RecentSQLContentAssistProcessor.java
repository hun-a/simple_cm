package com.cubrid.common.ui.query.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.swt.graphics.Image;
import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.core.util.StringUtil;
import com.cubrid.common.ui.query.Messages;
import com.cubrid.common.ui.query.control.jface.text.contentassist.CompletionProposal;
import com.cubrid.common.ui.query.control.jface.text.contentassist.ContextInformationValidator;
import com.cubrid.common.ui.query.control.jface.text.contentassist.ICompletionProposal;
import com.cubrid.common.ui.query.control.jface.text.contentassist.IContentAssistProcessor;
import com.cubrid.common.ui.query.control.jface.text.contentassist.IContextInformation;
import com.cubrid.common.ui.query.control.jface.text.contentassist.IContextInformationValidator;
import com.cubrid.common.ui.query.format.SqlFormattingStrategy;
import com.cubrid.common.ui.spi.persist.RecentlyUsedSQLContentPersistUtils;

public class RecentSQLContentAssistProcessor implements IContentAssistProcessor {
	public static final String ID = RecentSQLContentAssistProcessor.class.getName();
	private static final Logger LOGGER = LogUtil.getLogger(RecentSQLContentAssistProcessor.class);
	private final IContextInformationValidator contextInfoValidator;
	private final IDatabaseProvider databaseProvider;
	private final SqlFormattingStrategy formater;
	private String lastError;
	private boolean running = false;

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public RecentSQLContentAssistProcessor(IDatabaseProvider databaseProvider) {
		super();
		contextInfoValidator = new ContextInformationValidator(this);
		this.databaseProvider = databaseProvider;
		this.formater = new SqlFormattingStrategy(databaseProvider);
	}

	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
		IDocument document = viewer.getDocument();
		int currOffset = offset - 1;

		try {
			String currWord = "";
			char currChar;
			while (currOffset >= 0 && !isStatementSeparator(currChar = document.getChar(currOffset))) {
				currWord = Character.toUpperCase(currChar) + currWord;
				currOffset--;
			}

			//if currWord is "" display all;
			boolean allFlag = false;
			if (StringUtil.isEmpty(currWord)) {
				allFlag = true;
			}

			List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
			ICompletionProposal proposal = null;
			for (String sql : RecentlyUsedSQLContentPersistUtils.getRecentlyUsedSQLContentsById(databaseProvider.getDatabase())) {
				if (allFlag) {
					proposal = buildOneProposal(sql, currWord, offset - currWord.length(), null);
					proposals.add(proposal);
				} else if (StringUtil.startsWithIgnoreCase(currWord, sql)) {
					proposal = buildOneProposal(sql, currWord, offset - currWord.length(), null);
					proposals.add(proposal);
				}
			}

			return proposals.toArray(new ICompletionProposal[proposals.size()]);
		} catch (Exception e) {
			LOGGER.error("", e);
			return null;
		} finally {
			setRunning(false);
		}
	}

	private CompletionProposal buildOneProposal(String sql,
		String replacedWord, int offset, Image image) {
		String name = "";
		if (sql.length() > 60) {
			name = sql.substring(0, 57);
			name += "...";
		} else {
			name = sql;
		}
		
		name = name.replaceAll(StringUtil.NEWLINE, "");
		name = name.replaceAll("\\s", " ");
		
		String formattedSQL = null;
		try {
			formattedSQL = formater.format(sql);
		} catch (Exception ex) {
			formattedSQL = sql;
		}
		
		return new CompletionProposal(formattedSQL, offset, replacedWord.length(), formattedSQL.length(),
				image, name, null, formattedSQL);
	}

	/**
	 * Return whether the previous character is statement separator
	 *
	 * @param ch char
	 * @return boolean
	 */
	private boolean isStatementSeparator(char ch) {
		return ch == ICharacterScanner.EOF || ch == '\r' || ch == '\n' || ch == '\t' || ch == ';';
	}

	public ICompletionProposal[] computeSecondProposals(ITextViewer viewer,
			int offset, ICompletionProposal selectedProposal) {
		return new ICompletionProposal[]{};
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		lastError = Messages.noContext;
		return new IContextInformation[]{};
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		return new char[]{};
	}

	public char[] getContextInformationAutoActivationCharacters() {
		return new char[]{};
	}

	public String getErrorMessage() {
		return lastError;
	}

	public IContextInformationValidator getContextInformationValidator() {
		return contextInfoValidator;
	}
}
