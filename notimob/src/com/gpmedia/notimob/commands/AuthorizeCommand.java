package com.gpmedia.notimob.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.model.User;
import com.gpmedia.notimob.systems.AuthSystem;

public class AuthorizeCommand implements Command {
	
	private static final List<String> allowedPages = Arrays.asList(new String [] { 
		Pages.REGISTRATION, Pages.MAIN_UNLOGGED,  
	});

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		String page = (String) values.get(Fields.PAGE);
		String newPage = page;
		
		User user = AuthSystem.getCurrentUser();
		if (user == null) {
			String login = parameters.getParameter(Fields.USERNAME);
			String password = parameters.getParameter(Fields.PASSWORD);
			
			// user is not authorized and provided his credentials
			if (login != null && !login.equals("") && password != null
					&& !password.equals("")) {
				try {
					User authorizedUser = AuthSystem.authorize(login, password);
					values.put(ModelNames.CURRENT_USER, authorizedUser);
					//authorized, can go inside, if was going to some other page
					//let him go, else correct page to forward him to main
					if (loginOrEmpty(page)) {
						newPage = Pages.MAIN; 
					}
				} catch (Exception e) {
					values.put(ModelNames.ERROR_MESSAGE, e.getMessage());
					newPage=Pages.LOGIN;
				}
			} else {
				//not logged in - can see only main for not logged
				if (page == null || page.equals("") || page.equals(Pages.MAIN))
					newPage = Pages.MAIN_UNLOGGED;
				else if (!allowedPages.contains(page)) {
					newPage=Pages.LOGIN; //if he tries to get somewhere - ask to login
				}
			}
		} else {
			values.put(ModelNames.CURRENT_USER, user);
			// no need to login let him inside
			if (loginOrEmpty(page)) {
				newPage = Pages.MAIN; 
			}
		}
		values.put(Fields.PAGE, newPage);
	}

	private boolean loginOrEmpty(String page) {
		return page == null || page.equals (Pages.LOGIN) || page.equals ("");
	}
}
