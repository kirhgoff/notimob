package com.gpmedia.notimob.commands;

import java.util.ArrayList;
import java.util.List;

import com.gpmedia.notimob.Command;


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
