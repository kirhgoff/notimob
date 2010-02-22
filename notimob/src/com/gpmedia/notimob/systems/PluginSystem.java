package com.gpmedia.notimob.systems;

import java.util.HashMap;
import java.util.Map;

import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.model.ConnectionDetails;
import com.gpmedia.notimob.model.Plugin;

public class PluginSystem {
	private static Map<String, ConnectionDetailsFactory> parsers = new HashMap<String, ConnectionDetailsFactory>();
	static {
		parsers.put("vkontakte", new GenericPluginFactory ());
		parsers.put("mailplugin", new MailPluginFactory ());
	}

	public static ConnectionDetails createConnectionDetails(
			Plugin plugin, ParameterSource source) {
		
		ConnectionDetailsFactory connectionDetailsFactory = parsers.get(plugin.getAlias());
		return connectionDetailsFactory.build(source);
	}

}
