package com.exam.helper;

public class UserNotFoundException extends Exception {
	public UserNotFoundException() {
		super("User with this Username not found in Database!! try again");
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String msg) {
		super(msg);
	}
}
