package com.cubrid.common.ui.query.editor;

import java.sql.SQLException;
import java.util.Vector;

import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.ui.query.action.QueryConnectionAction;
import com.cubrid.common.ui.query.control.CombinedQueryEditorComposite;
import com.cubrid.common.ui.query.control.QueryExecuter;
import com.cubrid.common.ui.query.control.QueryResultComposite;
import com.cubrid.common.ui.query.control.tunemode.TuneModeModel;
import com.cubrid.common.ui.query.control.tunemode.TuneModeResultWindow;
import com.cubrid.common.ui.spi.persist.RecentlyUsedSQLDetailPersistUtils;
import com.cubrid.cubridmanager.core.common.jdbc.DBConnection;

public class QueryEditorResult {
	private static final Logger LOGGER = LogUtil.getLogger(QueryEditorResult.class);

	private CombinedQueryEditorComposite combinedQueryComposite;
	private DBConnection connection;
	private boolean isAutocommit;
	private QueryEditorPart queryEditorPart;

	private TuneModeResultWindow tuneModeView;

	public QueryEditorResult(QueryEditorPart queryEditorPart) {
		this.queryEditorPart = queryEditorPart;
		this.combinedQueryComposite = queryEditorPart.getCombinedQueryComposite();
		this.connection = queryEditorPart.getConnection();
	}

	public void showTableResult(final String logs, final String noSelectSql,
			final int cntResults, final boolean hasModifyQuery,
			final boolean isIsolationHigher, final boolean resolvedTransaction,
			final TuneModeModel tuneModeModel, final boolean multi,
			final Vector<QueryExecuter> curResult, final Boolean isRunning,
			final boolean collectExecStats) {

		RecentlyUsedSQLDetailPersistUtils.save(queryEditorPart.getDatabase());

		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				CTabFolder queryResultTabFolder = combinedQueryComposite.getQueryResultComp().getQueryResultTabFolder();
				QueryResultComposite queryResultComposite = null;
				if (multi) {
					queryResultComposite = combinedQueryComposite.getQueryResultComp();
				} else if (queryResultTabFolder != null
						&& !queryResultTabFolder.isDisposed()) {
					queryResultComposite = combinedQueryComposite.getQueryResultComp();
					queryResultComposite.disposeAllResult();
					if (cntResults < 1 && logs.trim().length() <= 0) {
						queryResultComposite.makeEmptyResult();
					} else {
						if (logs.trim().length() > 0) {
							queryResultComposite.makeLogResult(noSelectSql, logs);
						}
						for (int j = 0; j < curResult.size(); j++) {
							queryResultComposite.makeSingleQueryResult((QueryExecuter) curResult.get(j));
						}
					}

					if (!isAutocommit && resolvedTransaction) {
						queryEditorPart.setHaveActiveTransaction(false);
					} else if (hasModifyQuery || isIsolationHigher) {
						queryEditorPart.setHaveActiveTransaction(true);
					} else {
						try {
							if (connection.hasConnection()) {
								QueryConnectionAction.queryAction(connection,
										QueryConnectionAction.TYPE.COMMIT);
							}
						} catch (SQLException event) {
							LOGGER.error("", event);
						}
						queryEditorPart.setHaveActiveTransaction(false);
					}
					if (queryResultTabFolder.getItemCount() > 0) {
						queryResultTabFolder.setSelection(queryResultTabFolder.getItemCount() - 1);
					}

					queryEditorPart.getAutoCommitItem().setEnabled(true);
					queryEditorPart.getQueryPlanItem().setEnabled(true);
					queryEditorPart.getSetPstmtParaItem().setEnabled(true);
				}
				if (queryEditorPart.getRunningCount().decrementAndGet() == 0) {
					queryResultComposite.setCanDispose(true);
					queryEditorPart.setIsRunning(false);
				}
				if (collectExecStats && tuneModeModel != null) {
					displayTuneModeResult(tuneModeModel);
				}
			}
		});
	}

	public void displayTuneModeResult(final TuneModeModel tuneModeModel) {
		if (tuneModeView == null || tuneModeView.isDisposed()) {
			createTuneModeView();
		}
		tuneModeView.setParentEditorName(queryEditorPart.getPartName());
		tuneModeView.showResult(tuneModeModel);
	}

	private void createTuneModeView() {
		tuneModeView = new TuneModeResultWindow(queryEditorPart);
		tuneModeView.open();
	}

	public void showTuneModeResult() {
		tuneModeView.show();
	}

	public void hideTuneModeResult() {
	}
}
