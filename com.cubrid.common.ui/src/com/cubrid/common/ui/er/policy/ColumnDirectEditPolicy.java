package com.cubrid.common.ui.er.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import com.cubrid.common.ui.er.commands.ModifyColumnLabelCommand;
import com.cubrid.common.ui.er.model.ERTableColumn;
import com.cubrid.common.ui.er.part.ColumnPart;

public class ColumnDirectEditPolicy extends
DirectEditPolicy {
	private String oldValue;

	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {

		ERTableColumn column = (ERTableColumn) getHost().getModel();
		ModifyColumnLabelCommand cmd = new ModifyColumnLabelCommand(column);
		CellEditor cellEditor = request.getCellEditor();
		cmd.changeNameType((String) cellEditor.getValue());
		return cmd;
	}

	@Override
	protected void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		ColumnPart columnPart = (ColumnPart) getHost();
		columnPart.handleNameChange(value);
	}

	@Override
	protected void storeOldEditValue(DirectEditRequest request) {
		CellEditor cellEditor = request.getCellEditor();
		oldValue = (String) cellEditor.getValue();
	}

	@Override
	protected void revertOldEditValue(DirectEditRequest request) {
		CellEditor cellEditor = request.getCellEditor();
		cellEditor.setValue(oldValue);
		ColumnPart columnPart = (ColumnPart) getHost();
		columnPart.revertNameChange(oldValue);

	}
}