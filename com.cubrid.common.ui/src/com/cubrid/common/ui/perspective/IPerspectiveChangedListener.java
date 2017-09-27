package com.cubrid.common.ui.perspective;

public interface IPerspectiveChangedListener {
	public void showPerspective(PerspectiveChangeEvent event);

	public void hidePerspectiveHide(PerspectiveChangeEvent event);

	public void perspectiveChanged(PerspectiveChangeEvent event);

	public String getPerspectiveId();
}