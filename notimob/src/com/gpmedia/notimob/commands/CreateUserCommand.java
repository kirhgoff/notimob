package com.gpmedia.notimob.commands;

import java.util.Map;

import com.gpmedia.notimob.Command;
import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.systems.UserSystem;

public class CreateUserCommand extends CommandBase implements Command {

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		String login = parameters.getParameter(Fields.NEW_LOGIN);
		String password = parameters.getParameter(Fields.NEW_PASSWORD);
		String email = parameters.getParameter(Fields.EMAIL);
		
		try {
			UserSystem.createUser (login, password, email);
		}
		catch (Exception e) {
			forwardToPage(values, Pages.REGISTRATION, e.getMessage());
		}
	}

}
