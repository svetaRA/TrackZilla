package com.ms.bugzilla.exeption;

import com.ms.bugzilla.entity.User;

public class UserNameNotfoundException extends RuntimeException {
	public UserNameNotfoundException(String exception, String string, String username) {
		super(exception);
		// TODO Auto-generated constructor stub
	}
} 
