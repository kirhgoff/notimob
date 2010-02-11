package com.gpmedia.notimob.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CommandProcessor {
	private static final Logger log = Logger.getLogger(CommandProcessor.class
			.getName());
	public static final String PAGE = "page";
	public static final String COMMAND = "command";

	private Map<String, Command> commands = new HashMap<String, Command>();
	private List<Command> defaultCommands = new ArrayList<Command>();
	private Map<String, Command> pageDefaultCommands = new HashMap<String, Command>();

	private final ParameterSource parameters;
	private String currentPage;
	private String initialCommand;

	public CommandProcessor(ParameterSource parameters) {
		currentPage = parameters.getParameter("page");
		initialCommand = parameters.getParameter("command");

		log.info("processing the request: page=" + getCurrentPage()
				+ ", command=" + initialCommand);
		this.parameters = parameters;

		initCommands();
	}

	public void initCommands() {
		// Commands which will be executed for every page
		defaultCommands.add(new AuthorizeCommand());
		commands.put("logout", new LogoutCommand ());
	}

	public Map<String, Object> process() {
		Map<String, Object> values = new HashMap<String, Object>();
		//enrich parameters - still not clear what to use - request or values
		values.put("page", getCurrentPage());
		// first run default commands that runs for every page
		for (Iterator<Command> iterator = defaultCommands.iterator(); iterator
				.hasNext();) {
			Command defaultCommand = iterator.next();
			defaultCommand.invoke(values, parameters);
		}

		// invoke chain of page default commands
		// use composite here
		Command pageDefaultCommand = pageDefaultCommands.get(getCurrentPage());
		if (pageDefaultCommand != null)
			pageDefaultCommand.invoke(values, parameters);

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
		return values;
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
