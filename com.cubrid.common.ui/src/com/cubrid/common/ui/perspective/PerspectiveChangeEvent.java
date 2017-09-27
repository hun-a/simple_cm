package com.cubrid.common.ui.perspective;

public class PerspectiveChangeEvent {
	private String sourceId;
	private String targetId;

	/**
	 * @param sourceId
	 * @param targetId
	 */
	public PerspectiveChangeEvent(String sourceId, String targetId) {
		this.sourceId = sourceId;
		this.targetId = targetId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

}