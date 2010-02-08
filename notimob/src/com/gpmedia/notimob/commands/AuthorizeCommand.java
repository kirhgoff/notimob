package com.gpmedia.notimob.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.gpmedia.notimob.systems.AuthSystem;

public class AuthorizeCommand implements Command {
	//value names
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String PAGE = "page";
	private static final String PASSWORD = "password";
	//TODO change login to username for value names
	//page names
	private static final String LOGIN = "login";
	private static final String MAIN = "main";
	private static final String MAIN_UNLOGGED = "main-unlogged";
	private static final String REGISTRATION = "registration";
	
	private static final List<String> allowedPages = Arrays.asList(new String [] { 
		REGISTRATION, MAIN_UNLOGGED,  
	});

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		String page = (String) values.get(PAGE);
		String newPage = page;
		
		if (!AuthSystem.isLoggedIn()) {
			String login = parameters.getParameter(LOGIN);
			String password = parameters.getParameter(PASSWORD);
			
			// user is not authorized and provided his credentials
			if (login != null && !login.equals("") && password != null
					&& !password.equals("")) {
				try {
					AuthSystem.authorize(login, password);
					//authorized, can go inside, if was going to some other page
					//let him go, else correct page to forward him to main
					if (loginOrEmpty(page)) {
						newPage = MAIN; 
					}
				} catch (Exception e) {
					values.put(ERROR_MESSAGE, e.getMessage());
					newPage=LOGIN;
				}
			} else {
				//not logged in - can see only main for not logged
				if (page == null || page.equals("") || page.equals(MAIN))
					newPage = MAIN_UNLOGGED;
				else if (!allowedPages.contains(page)) {
					newPage=LOGIN; //if he tries to get somewhere - ask to login
				}
			}
		} else {
			// no need to login let him inside
			if (loginOrEmpty(page)) {
				newPage = MAIN; 
			}
		}
		values.put(PAGE, newPage);
	}

	private boolean loginOrEmpty(String page) {
		return page == null || page.equals (LOGIN) || page.equals ("");
	}
}
