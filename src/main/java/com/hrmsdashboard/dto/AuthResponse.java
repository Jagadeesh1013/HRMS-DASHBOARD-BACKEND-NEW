package com.hrmsdashboard.dto;

public class AuthResponse {

	private boolean success;
	private UserDTO user;
	private String token;

	// Constructors
	public AuthResponse() {
	}

	public AuthResponse(boolean success, UserDTO user, String token) {
		this.success = success;
		this.user = user;
		this.token = token;
	}

	// Getters & Setters
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
