package com.gpmedia.notimob.commands;

import java.util.Map;

import com.gpmedia.notimob.Command;
import com.gpmedia.notimob.ParameterSource;

public class AddConnectionCommand implements Command {

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		String pluginIDString = parameters.getParameter("plugin_id");
		if (pluginIDString == null) {
			values.put(Placeholder.ERROR_MESSAGE, "Invalid request");
		}
		
		long pluginID = Long.valueOf(pluginIDString);
		
		//TODO add checks
		String username = parameters.getParameter(Fields.USERNAME);
		String password = parameters.getParameter(Fields.PASSWORD);

	}

}
