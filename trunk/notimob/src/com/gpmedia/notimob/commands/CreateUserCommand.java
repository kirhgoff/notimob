package com.gpmedia.notimob.commands;

import java.util.Map;

import com.gpmedia.notimob.systems.UserSystem;

public class CreateUserCommand implements Command {

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		String login = parameters.getParameter("newLogin");
		String password = parameters.getParameter("newPassword");
		String email = parameters.getParameter("email");
		
		try {
			UserSystem.createUser (login, password, email);
		}
		catch (Exception e) {
			values.put("page", "registration");
			values.put("errorMessage", e.getMessage());
		}
	}

}
