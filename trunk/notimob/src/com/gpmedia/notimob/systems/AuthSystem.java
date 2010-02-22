package com.gpmedia.notimob.systems;

import javax.servlet.http.HttpSession;

import com.gpmedia.notimob.dao.UserDAO;
import com.gpmedia.notimob.model.User;

public class AuthSystem {
	//hacky hacky hacky hack - but we live in single thread
	private static HttpSession session;

	//TODO
	public static User getCurrentUser() {
		//potential security whole
		if (session == null) throw new IllegalStateException("No session saved!");
		String currentUser =  (String) session.getAttribute("currentUser");
		User user = UserDAO.findByName (currentUser);
		return user;
	}
	
	//TODO 
	public static User authorize(String login, String password) {
		User user = UserDAO.login (login, password);
		if (user != null) {
			session.setAttribute("currentUser", login);
			return user;
		}
		else 
			throw new RuntimeException ("неправильный логин/пароль");
	}
	
	public static void setCurrentSesssion(HttpSession session) {
		AuthSystem.session = session;
	}

	public static void logOff() {
		session.setAttribute("currentUser", null);
	}

	

}
