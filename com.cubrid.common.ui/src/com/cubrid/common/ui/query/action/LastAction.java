/*
 * Copyright (C) 2009 Search Solution Corporation. All rights reserved by Search
 * Solution.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: -
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. - Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. - Neither the name of the <ORGANIZATION> nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package com.cubrid.common.ui.query.action;

import org.eclipse.jface.action.Action;

import com.cubrid.common.ui.CommonUIPlugin;
import com.cubrid.common.ui.query.Messages;
import com.cubrid.common.ui.query.control.QueryExecuter;
import com.cubrid.common.ui.query.control.QueryInfo;
import com.cubrid.common.ui.spi.util.CommonUITool;

/**
 * 
 * last page action in query editor result
 * 
 * @author pangqiren
 * 
 */
public class LastAction extends
		Action {
	private final QueryExecuter executer;

	/**
	 * The constructor
	 * 
	 * @param result
	 */
	public LastAction(QueryExecuter result) {
		super(Messages.qedit_lastpage);
		setId("LastAction");
		setImageDescriptor(CommonUIPlugin.getImageDescriptor("icons/queryeditor/query_page_last.png"));
		setDisabledImageDescriptor(CommonUIPlugin.getImageDescriptor("icons/queryeditor/query_page_last_disabled.png"));
		setToolTipText(Messages.qedit_lastpage);
		this.executer = result;
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		if (executer.isModifiedResult()) {
			CommonUITool.openErrorBox(Messages.errModifiedNotMoving);
			return;
		}

		if (executer.tblResult != null) {
			executer.tblResult.forceFocus();
		}

		QueryInfo queryInfo = executer.getQueryInfo();
		queryInfo.setCurrentPage(queryInfo.getPages());
		executer.makeItem();
		executer.updateActions();
	}
}
