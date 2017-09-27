package com.cubrid.common.ui.spi.model;

public class CubridGroupNode extends
DefaultCubridNode implements
Cloneable {

	public CubridGroupNode(String id, String label, String iconPath) {
		super(id, label, iconPath);
		this.setType(NodeType.GROUP);
	}

	public boolean isContainer() {
		return true;
	}

	/**
	 * Clone this object
	 * 
	 * @return Object
	 * @throws CloneNotSupportedException the exception
	 */
	public CubridGroupNode clone() throws CloneNotSupportedException {
		return (CubridGroupNode)super.clone();
	}

	/**
	 * Add child object to this node
	 * 
	 * @param obj the ICubridNode object
	 */
	public void addChild(ICubridNode obj) {
		if (obj != null && !isContained(obj)) {
			obj.setParent(this);
			childList.add(obj);
		}
	}

	/**
	 * Remove child object from this node,and don't fire the event.
	 * 
	 * @param obj the ICubridNode object
	 */
	public void removeChild(ICubridNode obj) {
		if (obj != null) {
			childList.remove(obj);
		}
	}

	/**
	 * Add child object to this node
	 * 
	 * @param obj the ICubridNode object
	 * @param index the insert index of node.
	 */
	public void addChild(ICubridNode obj, int index) {
		if (obj != null && !isContained(obj)) {
			obj.setParent(this);
			childList.add(index, obj);
		}
	}

}
