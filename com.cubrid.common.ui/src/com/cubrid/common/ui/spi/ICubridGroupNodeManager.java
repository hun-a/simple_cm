package com.cubrid.common.ui.spi;

import java.util.List;

import com.cubrid.common.ui.spi.model.CubridGroupNode;
import com.cubrid.common.ui.spi.model.ICubridNode;

public interface ICubridGroupNodeManager {

	/**
	 * A prototype of default node,don't change any attribute of it,please use
	 * Object.clone to get a new default group node.
	 */
	public static final CubridGroupNode DEFAULT_GROUP_NODE = new CubridGroupNode(
			"Default Group", "Default Group", "icons/navigator/group.png");

	/**
	 * Get all group nodes save at local.
	 * 
	 * @return all group nodes.
	 */
	public List<CubridGroupNode> getAllGroupNodes();

	/**
	 * Add a new group node to list. The default group node only contain the
	 * items which has no parent group.
	 * 
	 * @param group new group node.
	 */
	public void addGroupNode(CubridGroupNode group);

	/**
	 * Save all group node.
	 * 
	 */
	public void saveAllGroupNode();

	/**
	 * Get all group items just like hosts or connections.
	 * 
	 * @return the group items of all.
	 */
	public List<ICubridNode> getAllGroupItems();

	/**
	 * Get the group's item by item's name
	 * 
	 * @param name item's name
	 * @return Group item
	 */
	public ICubridNode getGroupItemByItemName(String name);

	/**
	 * get the group object by group id
	 * 
	 * @param id group id
	 * @return Group node.
	 */
	public CubridGroupNode getGroupById(String id);

	/**
	 * get the group object by group name
	 * 
	 * @param name group name
	 * @return Group node.
	 */
	public CubridGroupNode getGroupByName(String name);

	/**
	 * get the group object by group name
	 * 
	 * @param nodeList the group node list
	 * @param name group name
	 * @return Group node.
	 */
	public CubridGroupNode getGroupByName(List<CubridGroupNode> nodeList,
			String name);

	/**
	 * Remove group by id
	 * 
	 * @param groupId group id or group name
	 */
	public void removeGroup(String groupId);

	/**
	 * Reorder the groups by input string array.
	 * 
	 * @param orderedName the ordered group names.
	 */
	public void reorderGroup(String[] orderedName);

	/**
	 * Change the item position in the items list.
	 * 
	 * @param node node to be change.
	 * @param index position
	 */
	public void changeItemPosition(ICubridNode node, int index);

	/**
	 * Get the default group of the group list.
	 * 
	 * @return default group.
	 */
	public CubridGroupNode getDefaultGroup();

	/**
	 * Retrieves whether the parameter is default group.
	 * 
	 * @param group that need to be compare.
	 * @return true:is default;
	 */
	public boolean isDefaultGroup(CubridGroupNode group);
}
