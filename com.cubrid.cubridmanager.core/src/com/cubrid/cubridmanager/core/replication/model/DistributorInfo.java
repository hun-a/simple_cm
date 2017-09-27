package com.cubrid.cubridmanager.core.replication.model;

public class DistributorInfo {

	private String distDbName;
	private String distDbPath;
	private String agentPort;
	private String trailLogPath;
	private String errorLogPath;
	private String copyLogPath;
	private String delayTimeLogSize;
	private boolean isRestartReplWhenError;
	private boolean isAgentActive = false;

	public String getDistDbName() {
		return distDbName;
	}

	public void setDistDbName(String distDbName) {
		this.distDbName = distDbName;
	}

	public String getDistDbPath() {
		return distDbPath;
	}

	public void setDistDbPath(String distDbPath) {
		this.distDbPath = distDbPath;
	}

	public String getAgentPort() {
		return agentPort;
	}

	public void setAgentPort(String agentPort) {
		this.agentPort = agentPort;
	}

	public String getTrailLogPath() {
		return trailLogPath;
	}

	public void setTrailLogPath(String trailLogPath) {
		this.trailLogPath = trailLogPath;
	}

	public String getErrorLogPath() {
		return errorLogPath;
	}

	public void setErrorLogPath(String errorLogPath) {
		this.errorLogPath = errorLogPath;
	}

	public String getCopyLogPath() {
		return copyLogPath;
	}

	public void setCopyLogPath(String copyLogPath) {
		this.copyLogPath = copyLogPath;
	}

	public String getDelayTimeLogSize() {
		return delayTimeLogSize;
	}

	public void setDelayTimeLogSize(String delayTimeLogSize) {
		this.delayTimeLogSize = delayTimeLogSize;
	}

	public boolean isRestartReplWhenError() {
		return isRestartReplWhenError;
	}

	public void setRestartReplWhenError(boolean isRestartReplWhenError) {
		this.isRestartReplWhenError = isRestartReplWhenError;
	}

	public boolean isAgentActive() {
		return isAgentActive;
	}

	public void setAgentActive(boolean isAgentActive) {
		this.isAgentActive = isAgentActive;
	}

}
