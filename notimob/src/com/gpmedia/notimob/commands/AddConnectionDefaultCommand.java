package com.gpmedia.notimob.commands;

import java.util.Map;

import com.gpmedia.notimob.Command;
import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.model.Plugin;

public class AddConnectionDefaultCommand implements Command{

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		Plugin plugin = new Plugin ();
		plugin.setEditForm("page-default.html");
		plugin.setTitle("Title of plugin");
		values.put("plugin", plugin);
	}

}
