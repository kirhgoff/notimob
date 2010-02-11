package com.gpmedia.notimob.systems;

import javax.servlet.http.HttpSession;

import com.gpmedia.notimob.dao.UserDAO;

public class AuthSystem {
	//hacky hacky hacky hack - but we live in single thread
	private static HttpSession session;

	public static boolean isLoggedIn() {
		//potential security whole
		if (session == null) throw new IllegalStateException("No session saved!");
		Object loggedIn =  session.getAttribute("loggedIn");
		if (loggedIn == null) return false;
		else return ((Boolean) loggedIn);
	}
	
	public static boolean authorize(String login, String password) {
		if (UserDAO.login (login, password)) {
			session.setAttribute("loggedIn", true);
			return true;
		}
		else 
			throw new RuntimeException ("неправильный пароль");
	}
	
	public static void setCurrentSesssion(HttpSession session) {
		AuthSystem.session = session;
	}

	public static void logOff() {
		session.setAttribute("loggedIn", false);
	}

	

}
