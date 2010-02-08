package com.gpmedia.notimob.systems;

import javax.servlet.http.HttpSession;

public class AuthSystem {

	private final HttpSession session;

	public AuthSystem (HttpSession httpSession) {
		this.session = httpSession;
	}
	
	public boolean isLoggedIn() {
		
		return false;
	}
	
	public boolean authorize(String login, String password) {
		if ("test".equals (login) && "test".equals(password)) {
			return true;
		}
		else 
			throw new RuntimeException ("Incorrect password");
	}

	

}
