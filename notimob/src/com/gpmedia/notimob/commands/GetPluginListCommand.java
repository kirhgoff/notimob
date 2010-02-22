package com.gpmedia.notimob.commands;

import java.util.List;
import java.util.Map;

import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.dao.PluginDAO;
import com.gpmedia.notimob.model.Plugin;

public class GetPluginListCommand implements Command {

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		List<Plugin> plugins = PluginDAO.findAll ();
		values.put(ModelNames.PLUGINS, plugins);
	}

}
