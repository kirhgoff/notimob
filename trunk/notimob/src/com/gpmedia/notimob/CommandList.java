package com.gpmedia.notimob;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CommandList implements Command {
	private List<Command> commands = new ArrayList<Command>();

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		for (Command command : commands) {
			command.invoke(values, parameters);
		}
	}

	public void add(Command command) {
		commands.add(command);
	}
}
