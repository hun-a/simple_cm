package com.cubrid.cubridmanager.core.cubrid.trigger.task;

import java.util.Locale;

import com.cubrid.common.core.common.model.Trigger;
import com.cubrid.cubridmanager.core.common.model.ServerInfo;
import com.cubrid.cubridmanager.core.common.socket.SocketTask;
import com.cubrid.cubridmanager.core.utils.ModelUtil.TriggerActionTime;
import com.cubrid.cubridmanager.core.utils.ModelUtil.TriggerConditionTime;
import com.cubrid.cubridmanager.core.utils.ModelUtil.TriggerEvent;
import com.cubrid.cubridmanager.core.utils.ModelUtil.TriggerStatus;

public class AddTriggerTask extends
SocketTask {
	private final static String[] SEND_MSG_ITEMS = new String[]{"task",
		"token", "dbname", "triggername", "conditiontime", "eventtype",
		"action", "eventtarget", "actiontime", "status", "priority" };
	private final static String CR = "\r";
	private final static String NL = "\n";

	public AddTriggerTask(ServerInfo serverInfo) {
		super("addtrigger", serverInfo, AddTriggerTask.SEND_MSG_ITEMS);
	}

	/**
	 * Set the key "dbname" in request message
	 * 
	 * @param dbname String
	 */
	public void setDbName(String dbname) {
		this.setMsgItem("dbname", dbname);
	}

	/**
	 * Set the key "triggername" in request message
	 * 
	 * @param triggerName String
	 */
	public void setTriggerName(String triggerName) {
		this.setMsgItem("triggername", triggerName);
	}

	/**
	 * Set the key "conditiontime" in request message
	 * 
	 * @param conditionTime TriggerConditionTime
	 */
	public void setConditionTime(TriggerConditionTime conditionTime) {
		this.setMsgItem("conditiontime", conditionTime.getText());
	}

	/**
	 * Set the key "condition" in request message
	 * 
	 * @param condition String
	 */
	public void setCondition(String condition) {
		String triggerCondition = condition.trim();
		if (triggerCondition.length() > 0) {
			if (triggerCondition.toLowerCase(Locale.getDefault()).startsWith("if ")) {
				triggerCondition = triggerCondition.substring(3);
			}
			this.setMsgItem("condition", triggerCondition);
		} else {
			this.setMsgItem("condition", condition);
		}
	}

	/**
	 * Set the key "eventtype" in request message
	 * 
	 * @param eventType TriggerEvent
	 */
	public void setEventType(TriggerEvent eventType) {
		this.setMsgItem("eventtype", eventType.getText());
	}

	/**
	 * Set the key "action" in request message
	 * 
	 * @param action TriggerAction
	 * @param addtionalInformation String
	 */
	public void setAction(Trigger.TriggerAction action, String addtionalInformation) {
		switch (action) {
		case REJECT:
			this.setMsgItem("action", action.getText());
			break;
		case INVALIDATE_TRANSACTION:
			this.setMsgItem("action", action.getText());
			break;
		case OTHER_STATEMENT:
			this.setMsgItem("action", addtionalInformation);
			break;
		case PRINT:
			String message = addtionalInformation;
			message = message.replaceAll(CR, "");
			message = message.replaceAll(NL, " ");
			message = message.replaceAll("'", "''");
			this.setMsgItem("action", action.getText() + " '" + message + "'");
			break;
		default:
			throw new IllegalArgumentException("not supported TriggerAction");
		}
	}

	/**
	 * Set the key "eventtarget" in request message,which indicate a class in
	 * database
	 * 
	 * @param eventTarget String
	 */
	public void setEventTarget(String eventTarget) {
		this.setMsgItem("eventtarget", eventTarget);
	}

	/**
	 * Set the key "actiontime" in request message
	 * 
	 * @param actionTime TriggerActionTime
	 */
	public void setActionTime(TriggerActionTime actionTime) {
		this.setMsgItem("actiontime", actionTime.getText());
	}

	/**
	 * Set the key "status" in request message
	 * 
	 * @param status TriggerStatus
	 */
	public void setStatus(TriggerStatus status) {
		this.setMsgItem("status", status.getText());
	}

	/**
	 * Set the key "priority" in request message
	 * 
	 * @param priority String
	 */
	public void setPriority(String priority) {
		this.setMsgItem("priority", Trigger.formatPriority(priority));
	}
}
