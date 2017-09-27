package com.cubrid.common.ui.er.layout;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;

import com.cubrid.common.ui.er.model.ERSchema;
import com.cubrid.common.ui.er.part.SchemaDiagramPart;
import com.cubrid.common.ui.er.policy.SchemaXYLayoutPolicy;

public class DelegatingLayoutManager implements LayoutManager {
	private final SchemaDiagramPart diagram;
	private LayoutManager activeLayoutManager;
	private final ERGraphLayoutManager erGraphLayoutManager;
	private final GraphXYLayout xyLayoutManager;

	public DelegatingLayoutManager(SchemaDiagramPart diagram) {
		this.diagram = diagram;
		this.erGraphLayoutManager = new ERGraphLayoutManager(diagram);
		this.xyLayoutManager = new GraphXYLayout(diagram);
		this.activeLayoutManager = this.erGraphLayoutManager;
	}

	public void layout(IFigure container) {
		ERSchema erSchema = diagram.getSchema();
		if (erSchema.isLayoutManualDesired()) {
			if (activeLayoutManager != xyLayoutManager) {
				if (erSchema.isLayoutManualAllowed()) {
					setLayoutManager(container, xyLayoutManager);
					activeLayoutManager.layout(container);
				} else {
					if (diagram.setTableFigureBounds(true)) {
						setLayoutManager(container, xyLayoutManager);
						activeLayoutManager.layout(container);
					} else {
						activeLayoutManager.layout(container);
						setLayoutManager(container, xyLayoutManager);
					}
				}
			} else {
				setLayoutManager(container, xyLayoutManager);
				activeLayoutManager.layout(container);
			}
		} else {
			setLayoutManager(container, erGraphLayoutManager);
			activeLayoutManager.layout(container);
		}
	}

	public Object getConstraint(IFigure child) {
		return activeLayoutManager.getConstraint(child);
	}

	public Dimension getMinimumSize(IFigure container, int wHint, int hHint) {
		return activeLayoutManager.getMinimumSize(container, wHint, hHint);
	}

	public Dimension getPreferredSize(IFigure container, int wHint, int hHint) {
		return activeLayoutManager.getPreferredSize(container, wHint, hHint);
	}

	public void invalidate() {
		activeLayoutManager.invalidate();
	}

	public void remove(IFigure child) {
		activeLayoutManager.remove(child);
	}

	public void setConstraint(IFigure child, Object constraint) {
		activeLayoutManager.setConstraint(child, constraint);
	}

	public void setXYLayoutConstraint(IFigure child, Rectangle constraint) {
		xyLayoutManager.setConstraint(child, constraint);
	}

	private void setLayoutManager(IFigure container, LayoutManager layoutManager) {
		container.setLayoutManager(layoutManager);
		this.activeLayoutManager = layoutManager;
		if (layoutManager == xyLayoutManager) {
			diagram.installEditPolicy(EditPolicy.LAYOUT_ROLE,
					new SchemaXYLayoutPolicy());
		} else {
			diagram.installEditPolicy(EditPolicy.LAYOUT_ROLE, null);
		}
	}

	public LayoutManager getActiveLayoutManager() {
		return activeLayoutManager;
	}
}