package com.hrmsdashboard.dto;

public class ServerStatusResponse {

	private String serverName;
	private boolean up;

	public ServerStatusResponse(String serverName, boolean up) {
		this.serverName = serverName;
		this.up = up;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

}
