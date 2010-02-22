package com.gpmedia.notimob.systems;

import java.util.HashMap;
import java.util.Map;

import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.dao.PluginDAO;
import com.gpmedia.notimob.model.Connection;
import com.gpmedia.notimob.model.ConnectionDetails;
import com.gpmedia.notimob.model.Plugin;

public class ConnectionSystem {
	private static Map<String, ConnectionDetailsFactory> parsers = new HashMap<String, ConnectionDetailsFactory>();
	static {
		parsers.put("vkontakte", new GenericPluginFactory ());
		parsers.put("mailplugin", new MailPluginFactory ());
	}
	

	public static void createConnection (long pluginID, String username, String password, ParameterSource parameters) {
		Plugin plugin = PluginDAO.findByID(pluginID);
		
		ConnectionDetailsFactory connectionDetailsFactory = parsers.get(plugin.getAlias());
		ConnectionDetails details = connectionDetailsFactory.build(parameters);
		
		Connection connection = new Connection ();
		connection.setUsername(username);
		connection.setPassword(password);
		connection.setPlugin(plugin);
		connection.setDetails (details);
	}

}
