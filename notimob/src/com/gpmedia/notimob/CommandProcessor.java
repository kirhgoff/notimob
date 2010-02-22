package com.gpmedia.notimob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.gpmedia.notimob.commands.AuthorizeCommand;
import com.gpmedia.notimob.commands.Command;
import com.gpmedia.notimob.commands.CreateUserCommand;
import com.gpmedia.notimob.commands.GetPluginListCommand;
import com.gpmedia.notimob.commands.ListBuilder;
import com.gpmedia.notimob.commands.LogoutCommand;

public class CommandProcessor {
	private static final Logger log = Logger.getLogger(CommandProcessor.class
			.getName());
	public static final String PAGE = "page";
	public static final String COMMAND = "command";

	private Map<String, Command> commands = new HashMap<String, Command>();
	private List<Command> defaultCommands = new ArrayList<Command>();
	private Map<String, List<Command>> pageDefaultCommands = new HashMap<String, List<Command>>();	

	private final ParameterSource parameters;
	private String currentPage;
	private String initialCommand;

	public void initCommands() {
		// Commands which will be executed for every page
		defaultCommands.add(new AuthorizeCommand());
		//defaultCommands.add(new SetCurrentUserCommand());
		
		pageDefaultCommands.put("choose-connection", new ListBuilder ()
			.add (new GetPluginListCommand ()).list ());
		
		commands.put("logout", new LogoutCommand ());
		commands.put("create-user", new CreateUserCommand ());
		
	}
	
	public CommandProcessor(ParameterSource parameters) {
		currentPage = parameters.getParameter("page");
		initialCommand = parameters.getParameter("command");

		log.info("processing the request: page=" + getCurrentPage()
				+ ", command=" + initialCommand);
		this.parameters = parameters;

		initCommands();
	}

	public Map<String, Object> process() {
		Map<String, Object> values = new HashMap<String, Object>();
		//enrich parameters - still not clear what to use - request or values
		values.put("page", getCurrentPage());
		
		invokeDefaultCommands(values);
		invokePageDefaultCommands(values);
		invokeSpecifiedCommand(values);

		return values;
	}

	private void invokeSpecifiedCommand(Map<String, Object> values) {
		// get the specified command and run it
		String commandName = getInitialCommand();
		if (commandName != null) {
			Command command = commands.get(commandName);
			if (command == null) {
				System.out.println("ERROR: command not registered "
						+ commandName);
			} else {
				command.invoke(values, parameters);
			}
		}
	}

	private void invokePageDefaultCommands(Map<String, Object> values) {
		// invoke chain of page default commands
		// use composite here
		List<Command> commandsForPage = pageDefaultCommands.get(getCurrentPage());
		if (commands != null) {
			for (Command command: commandsForPage) 
				command.invoke(values, parameters);
		}
	}

	private void invokeDefaultCommands(Map<String, Object> values) {
		// first run default commands that runs for every page
		for (Iterator<Command> iterator = defaultCommands.iterator(); iterator
				.hasNext();) {
			Command defaultCommand = iterator.next();
			defaultCommand.invoke(values, parameters);
		}
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public String getInitialCommand() {
		return initialCommand;
	}

}
