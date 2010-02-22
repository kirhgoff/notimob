package com.gpmedia.notimob.commands;

import java.util.Map;

import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.dao.PluginDAO;
import com.gpmedia.notimob.model.Plugin;

public class LoadPluginDetailsCommand implements Command {

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		String pluginAlias = parameters.getParameter(ModelNames.PLUGIN);
		if (pluginAlias == null) {
			values.put(ModelNames.ERROR_MESSAGE, "Invalid request");
			return;
		}
		
		Plugin plugin = PluginDAO.findByAlias(pluginAlias);
		values.put (ModelNames.PLUGIN, plugin);
	}

}
