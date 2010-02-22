package com.gpmedia.notimob.commands;

import java.util.ArrayList;
import java.util.List;


public class ListBuilder {
	private List<Command> commands = new ArrayList <Command> ();
	
	public ListBuilder add(Command command) {
		commands.add(command);
		return this;
	}

	public List<Command> list() {
		return commands;
	}

}
