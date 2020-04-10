package com.ms.bugzilla.entity;

public class AuthenticateBean {
	
	public AuthenticateBean(String message) {
		super();
		this.message = message;
	}

	private String message;

	public String getUsername() {
		return message;
	}

	public void setUsername(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "AuthenticateBean [message=" + message + "]";
	}

}
