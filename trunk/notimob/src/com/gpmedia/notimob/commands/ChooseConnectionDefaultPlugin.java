package com.gpmedia.notimob.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.model.Plugin;

public class ChooseConnectionDefaultPlugin implements Command {

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		Plugin plugin = null;
		
		List<Plugin> plugins = new ArrayList<Plugin> ();
		
		plugin = new Plugin ();
		plugin.setIcon ("/img/vkontakte.gif");
		plugin.setLink ("http://vkontakte.ru");
		plugin.setTitle("Vkontakte.ru");
		plugin.setAlias("vkontakte");
		plugins.add (plugin);
		
		plugin = new Plugin ();
		plugin.setIcon("/img/mail_icon.gif");
		plugin.setLink("pop3");
		plugin.setTitle("Эл. почта по POP3");
		plugin.setAlias("mailplugin");
		plugin.setEditForm("page-connection-pop3.tpl");
		plugins.add (plugin);

		values.put("plugins", plugins);
	}

}
