package com.gpmedia.notimob.commands;

import java.util.Map;

import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.systems.AuthSystem;

public class LogoutCommand implements Command {

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		//TODO we need to clear all the session data here
		AuthSystem.logOff ();
		values.put("page", "login");
	}

}
