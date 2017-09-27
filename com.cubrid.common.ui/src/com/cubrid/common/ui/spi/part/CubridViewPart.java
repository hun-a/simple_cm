package com.cubrid.common.ui.spi.part;

import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.cubrid.common.ui.spi.CubridNodeManager;
import com.cubrid.common.ui.spi.LayoutManager;
import com.cubrid.common.ui.spi.event.ICubridNodeChangedListener;
import com.cubrid.common.ui.spi.model.ICubridNode;

public abstract class CubridViewPart extends
ViewPart implements
ICubridNodeChangedListener {

	protected ICubridNode cubridNode = null;

	/**
	 * Initializes this view with the given view site.
	 * 
	 * @param site the view site
	 * @exception PartInitException if this view was not initialized
	 *            successfully
	 */
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		setCubridNode(LayoutManager.getInstance().getCurrentSelectedNode());
		CubridNodeManager.getInstance().addCubridNodeChangeListener(this);
		String title = this.getPartName();
		if (null != cubridNode) {
			String serverName = cubridNode.getServer().getLabel();
			String port = cubridNode.getServer().getMonPort();
			if (title != null) {
				this.setPartName(title + " - " + cubridNode.getLabel() + "@"
						+ serverName + ":" + port);
			}
		}
	}

	/**
	 * 
	 * Get the CUBRID node of this view part
	 * 
	 * @return the CUBRID node
	 */
	public ICubridNode getCubridNode() {
		return this.cubridNode;
	}

	/**
	 * 
	 * Set cubrid node
	 * 
	 * @param cubridNode the ICubridNode object
	 */
	public void setCubridNode(ICubridNode cubridNode) {
		this.cubridNode = cubridNode;
	}

	/**
	 * Dispose the resource
	 */
	public void dispose() {
		CubridNodeManager.getInstance().removeCubridNodeChangeListener(this);
	}

	/**
	 * Call this method when this viewpart is focus
	 */
	public void setFocus() {
		if (null != cubridNode) {
			LayoutManager.getInstance().getTitleLineContrItem().changeTitleForViewOrEditPart(
					cubridNode, this);
			LayoutManager.getInstance().getStatusLineContrItem().changeStuatusLineForViewOrEditPart(
					cubridNode, this);
		}
	}
}
