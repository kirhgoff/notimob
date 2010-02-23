package com.gpmedia.notimob.commands;

import java.util.Map;

import com.gpmedia.notimob.Command;
import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.dao.PluginDAO;
import com.gpmedia.notimob.model.ConnectionDetails;
import com.gpmedia.notimob.model.Plugin;
import com.gpmedia.notimob.model.User;
import com.gpmedia.notimob.systems.ConnectionSystem;
import com.gpmedia.notimob.systems.PluginSystem;

public class AddConnectionCommand implements Command {

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		String pluginAlias = parameters.getParameter("plugin_alias");
		if (pluginAlias == null) {
			values.put(Placeholder.ERROR_MESSAGE, "Invalid request");
		}
		//TODO add checks
		String username = parameters.getParameter(Fields.USERNAME);
		String password = parameters.getParameter(Fields.PASSWORD);

		User user = (User) values.get(Placeholder.CURRENT_USER);
		Plugin plugin = PluginDAO.findByAlias(pluginAlias);
		
		ConnectionDetails details = PluginSystem.createConnectionDetails (plugin, parameters);
		ConnectionSystem.createConnection(user, plugin, username, password, details);
	}

}
