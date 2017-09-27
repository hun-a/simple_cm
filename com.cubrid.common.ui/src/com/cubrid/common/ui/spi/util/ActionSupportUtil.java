package com.cubrid.common.ui.spi.util;

import org.slf4j.Logger;

import com.cubrid.common.core.common.model.SerialInfo;
import com.cubrid.common.core.util.LogUtil;
import com.cubrid.common.core.util.StringUtil;
import com.cubrid.common.ui.spi.model.CubridDatabase;
import com.cubrid.common.ui.spi.model.CubridServer;
import com.cubrid.common.ui.spi.model.ISchemaNode;
import com.cubrid.common.ui.spi.model.NodeType;
import com.cubrid.cubridmanager.core.common.model.DbRunningType;
import com.cubrid.cubridmanager.core.common.model.ServerInfo;
import com.cubrid.cubridmanager.core.common.model.ServerUserInfo;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;
import com.cubrid.cubridmanager.core.cubrid.database.model.DbCreateAuthType;
import com.cubrid.cubridmanager.core.cubrid.sp.model.SPInfo;
import com.cubrid.cubridmanager.core.cubrid.user.model.DbUserInfo;

public final class ActionSupportUtil {

	private static final Logger LOGGER = LogUtil.getLogger(ActionSupportUtil.class);

	private ActionSupportUtil() {
	}

	/**
	 * Return whether support the multi or single selection in the same database
	 * 
	 * @param obj Object
	 * @param supportedNodeTypes The supported node type
	 * @param isSameType boolean
	 * @return boolean
	 */
	public static boolean isSupportMultiSelection(Object obj,
			String[] supportedNodeTypes, boolean isSameType) {
		if (obj instanceof ISchemaNode) {
			ISchemaNode schemaNode = (ISchemaNode) obj;
			CubridDatabase database = schemaNode.getDatabase();
			if (database == null || !database.isLogined()
					|| database.getRunningType() != DbRunningType.CS) {
				return false;
			}
			if (supportedNodeTypes == null || supportedNodeTypes.length == 0) {
				return true;
			} else {
				for (String nodeType : supportedNodeTypes) {
					if (nodeType.equals(schemaNode.getType())) {
						return true;
					}
				}
			}
		} else if (obj instanceof Object[]) {
			Object[] objArr = (Object[]) obj;
			if (objArr.length == 0) {
				return false;
			}
			CubridDatabase database = null;
			String type = null;

			for (Object object : objArr) {
				//Check every object whether support
				if (!isSupportMultiSelection(object, supportedNodeTypes,
						isSameType)) {
					return false;
				}
				//Check whether their type are same
				ISchemaNode node = (ISchemaNode) object;
				if (type == null) {
					type = node.getType();
				}
				if (isSameType && !type.equals(node.getType())) {
					return false;
				}
				//Check whether they are in the same database
				CubridDatabase db = node.getDatabase();
				if (database == null) {
					database = db;
				} else if (!database.getId().equals(db.getId())) {
					return false;
				}
			}
			//Check the database whether login and running
			if (database == null || !database.isLogined()
					|| database.getRunningType() != DbRunningType.CS) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Return whether support the single selection
	 * 
	 * @param obj Object
	 * @param supportedNodeTypes The supported node type
	 * @return boolean
	 */
	public static boolean isSupportSingleSelection(Object obj,
			String[] supportedNodeTypes) {
		if (obj == null) {
			return false;
		}
		Object selectedObj = obj;
		if (obj instanceof Object[]) {
			Object[] objArr = (Object[]) obj;
			if (objArr.length == 1) {
				selectedObj = objArr[0];
			} else {
				return false;
			}
		}
		if (selectedObj instanceof ISchemaNode) {
			ISchemaNode schemaNode = (ISchemaNode) selectedObj;
			CubridDatabase database = schemaNode.getDatabase();
			if (database == null || !database.isLogined()
					|| database.getRunningType() != DbRunningType.CS) {
				return false;
			}
			if (supportedNodeTypes == null || supportedNodeTypes.length == 0) {
				return true;
			} else {
				for (String nodeType : supportedNodeTypes) {
					if (nodeType.equals(schemaNode.getType())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * Return whether support the single selection and the selection type is
	 * equal <code>type</code> and the selection owner is equal to logined
	 * database user
	 * 
	 * @param obj Object
	 * @param type String
	 * @return boolean
	 */
	public static boolean isSupportSinSelCheckDbUser(Object obj, String type) {
		if (obj == null) {
			return false;
		}
		Object selectedObj = obj;
		if (obj instanceof Object[]) {
			Object[] objArr = (Object[]) obj;
			if (objArr.length == 1) {
				selectedObj = objArr[0];
			} else {
				return false;
			}
		}
		if (!(selectedObj instanceof ISchemaNode)) {
			return false;
		}
		ISchemaNode node = (ISchemaNode) selectedObj;
		if (type.equals(node.getType())) {
			ISchemaNode schemaNode = (ISchemaNode) selectedObj;
			CubridDatabase database = schemaNode.getDatabase();
			if (isNotRunningAndNotLogin(database)) {
				return false;
			}

			DatabaseInfo dbInfo = database.getDatabaseInfo();
			if (dbInfo == null) {
				LOGGER.error("The database.getDatabaseInfo() is a null.");
				return false;
			}

			DbUserInfo userInfo = dbInfo.getAuthLoginedDbUserInfo();
			if (userInfo == null) {
				LOGGER.error("The dbInfo.getAuthLoginedDbUserInfo() is a null.");
				return false;
			}

			if (userInfo.isDbaAuthority()) {
				return true;
			}

			boolean isSameUser = StringUtil.isEqualIgnoreCase(userInfo.getName(), getOwner(schemaNode));
			if (isSameUser) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * Get schema owner
	 * 
	 * @param node ISchemaNode
	 * @return String
	 */
	private static String getOwner(ISchemaNode node) {
		String type = node.getType();
		if (NodeType.SERIAL.equals(type)) {
			SerialInfo serialInfo = (SerialInfo) node.getAdapter(SerialInfo.class);
			return serialInfo == null ? null : serialInfo.getOwner();
		} else if (NodeType.STORED_PROCEDURE_FUNCTION.equals(type)
				|| NodeType.STORED_PROCEDURE_PROCEDURE.equals(type)) {
			SPInfo spInfo = (SPInfo) node.getAdapter(SPInfo.class);
			return spInfo == null ? null : spInfo.getOwner();
		}
		return null;
	}

	/**
	 * 
	 * Return whether support the multi selection and all selection type is
	 * equal <code>type</code> and the all selection owner is equal to logined
	 * database user
	 * 
	 * @param obj Object
	 * @param type String
	 * @return boolean
	 */
	public static boolean isSupportMultiSelCheckDbUser(Object obj, String type) {
		if (obj instanceof ISchemaNode) {
			ISchemaNode schemaNode = (ISchemaNode) obj;
			if (!type.equals(schemaNode.getType())) {
				return false;
			}

			CubridDatabase database = schemaNode.getDatabase();
			if (isNotRunningAndNotLogin(database)) {
				return false;
			}

			DatabaseInfo dbInfo = database.getDatabaseInfo();
			if (dbInfo == null) {
				LOGGER.error("The database.getDatabaseInfo() is a null.");
				return false;
			}

			DbUserInfo userInfo = dbInfo.getAuthLoginedDbUserInfo();
			if (userInfo == null) {
				LOGGER.error("The dbInfo.getAuthLoginedDbUserInfo() is a null.");
				return false;
			}

			if (userInfo.isDbaAuthority()) {
				return true;
			}

			boolean isSameUser = StringUtil.isEqualIgnoreCase(userInfo.getName(), getOwner(schemaNode));
			if (isSameUser) {
				return true;
			}
		} else if (obj instanceof Object[]) {
			Object[] objArr = (Object[]) obj;
			if (objArr.length == 0) {
				return false;
			}
			CubridDatabase database = null;
			for (Object object : objArr) {
				//Check every object whether support
				if (!isSupportMultiSelCheckDbUser(object, type)) {
					return false;
				}
				//Check whether they are in the same database
				ISchemaNode node = (ISchemaNode) object;
				CubridDatabase db = node.getDatabase();
				if (database == null) {
					database = db;
				} else if (!database.getId().equals(db.getId())) {
					return false;
				}
			}

			//Check the database whether login and running
			if (isNotRunningAndNotLogin(database)) {
				return false;
			}
			return true;
		}
		return false;
	}

	private static boolean isNotRunningAndNotLogin(CubridDatabase database) {
		if (database == null || !database.isLogined() || database.getRunningType() != DbRunningType.CS) {
			return true;
		}

		return false;
	}

	/**
	 * Whether having cubrid manager's administrative permissions
	 *
	 * @param obj ISchemaNode or ISchemaNode[]
	 * @return boolean
	 */
	public static boolean hasAdminPermission(Object obj) {
		if (!(obj instanceof ISchemaNode)) {
			return false;
		}

		ISchemaNode node = (ISchemaNode) obj;
		CubridServer server = node.getServer();
		if (server == null) {
			return false;
		}

		ServerInfo serverInfo = server.getServerInfo();
		if (serverInfo == null) {
			return false;
		}

		if (!serverInfo.isConnected()) {
			return false;
		}

		ServerUserInfo serverUserInfo = serverInfo.getLoginedUserInfo();
		if (serverUserInfo == null) {
			return false;
		}

		if (serverUserInfo.getDbCreateAuthType() == DbCreateAuthType.AUTH_ADMIN) {
			return true;
		}

		CubridDatabase database = node.getDatabase();
		if (database == null) {
			return false;
		}

		DbUserInfo dbUserInfo = database.getDatabaseInfo().getAuthLoginedDbUserInfo();
		if (dbUserInfo != null && dbUserInfo.isDbaAuthority() && database.isLogined()) {
			return true;
		}

		return false;
	}

	/**
	 * Whether having cubrid manager's administrative permissions on stop state
	 *
	 * @param obj ISchemaNode or ISchemaNode[]
	 * @return boolean
	 */
	public static boolean hasAdminPermissionOnStopState(Object obj) {
		if (!ActionSupportUtil.hasAdminPermission(obj)) {
			return false;
		}

		ISchemaNode node = (ISchemaNode) obj;
		CubridDatabase database = node.getDatabase();
		if (database == null) {
			return false;
		}

		if (database.getRunningType() != DbRunningType.STANDALONE) {
			return false;
		}

		return true;
	}

	/**
	 * Whether having cubrid manager's administrative permissions on running state
	 *
	 * @param obj ISchemaNode or ISchemaNode[]
	 * @return boolean
	 */
	public static boolean hasAdminPermissionOnRunningState(Object obj) {
		if (!ActionSupportUtil.hasAdminPermission(obj)) {
			return false;
		}

		ISchemaNode node = (ISchemaNode) obj;
		CubridDatabase database = node.getDatabase();
		if (database == null) {
			return false;
		}

		if (database.getRunningType() != DbRunningType.CS) {
			return false;
		}

		return true;
	}
}
