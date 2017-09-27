package com.cubrid.common.ui.common.navigator.dnd;

import com.cubrid.common.ui.spi.model.ICubridNode;

public interface CubridDnDNodeHandler {
	/**
	 * Handle the node to be DND.
	 * 
	 * @param dragNode the drag node
	 * @param dropNode the drop node
	 * @param insertBefore insert into the drop node before or after
	 * @param dropOperation the drop operation type <code>DND.DROP_COPY</code>
	 *        <code>DND.DROP_MOVE</code>
	 * @return boolean whether to handle with the drop
	 */
	public boolean handle(ICubridNode dragNode, ICubridNode dropNode,
			boolean insertBefore, int dropOperation);

}