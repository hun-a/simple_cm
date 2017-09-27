package com.cubrid.common.ui.spi.event;

import java.util.EventObject;

import com.cubrid.common.ui.spi.model.ICubridNode;

public class CubridNodeChangedEvent extends	
		EventObject {

	private static final long serialVersionUID = 5598187704879595777L;

	private transient ICubridNode cubridNode;

	private CubridNodeChangedEventType type;

	/**
	 * The constructor
	 * 
	 * @param source
	 */
	public CubridNodeChangedEvent(Object source) {
		super(source);
	}

	/**
	 * The constructor
	 * 
	 * @param cubridNode
	 * @param type
	 */
	public CubridNodeChangedEvent(ICubridNode cubridNode,
			CubridNodeChangedEventType type) {
		this(cubridNode);
		this.cubridNode = cubridNode;
		this.type = type;
	}

	/**
	 * 
	 * Get CubridNode object
	 * 
	 * @return the ICubridNode object
	 */
	public ICubridNode getCubridNode() {
		return cubridNode;
	}

	/**
	 * 
	 * Get envent type
	 * 
	 * @return the CubridNodeChangedEventType object
	 */
	public CubridNodeChangedEventType getType() {
		return type;
	}

}
