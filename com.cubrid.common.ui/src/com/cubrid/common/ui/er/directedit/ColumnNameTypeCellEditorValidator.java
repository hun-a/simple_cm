package com.cubrid.common.ui.er.directedit;

import org.eclipse.jface.viewers.ICellEditorValidator;
import org.slf4j.Logger;

import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.ui.er.ERException;
import com.cubrid.common.ui.er.ValidationMessageHandler;
import com.cubrid.common.ui.er.model.ERTableColumn;

public class ColumnNameTypeCellEditorValidator implements ICellEditorValidator {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogUtil
			.getLogger(ColumnNameTypeCellEditorValidator.class);
	private final ValidationMessageHandler messageHandler;
	private ColumnLabelHandler lableHandler;

	public ColumnNameTypeCellEditorValidator(
			ValidationMessageHandler validationMessageHandler,
			ERTableColumn erColumn) {
		this.messageHandler = validationMessageHandler;
		lableHandler = new ColumnLabelHandler(erColumn);
	}

	public String isValid(Object value) {
		try {
			lableHandler.checkFormat((String) value);
			lableHandler.setLatestData((String) value);
			return unsetMessageText();
		} catch (ERException e) {
			return setMessageText(e.getMessage());
		}
	}

	private String unsetMessageText() {
		messageHandler.reset();
		return null;
	}

	private String setMessageText(String text) {
		messageHandler.setMessageText(text);
		return text;
	}
}
