package com.gpmedia.notimob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.gpmedia.notimob.commands.AddConnectionCommand;
import com.gpmedia.notimob.commands.AuthorizeCommand;
import com.gpmedia.notimob.commands.CreateUserCommand;
import com.gpmedia.notimob.commands.DeleteConnectionCommand;
import com.gpmedia.notimob.commands.Fields;
import com.gpmedia.notimob.commands.GetConnectionsForCurrentUserCommand;
import com.gpmedia.notimob.commands.GetPluginListCommand;
import com.gpmedia.notimob.commands.LoadConnectionDetailsCommand;
import com.gpmedia.notimob.commands.LoadPluginDetailsCommand;
import com.gpmedia.notimob.commands.LogoutCommand;
import com.gpmedia.notimob.commands.Pages;
import com.gpmedia.notimob.commands.UpdateConnectionCommand;

public class CommandProcessor {
	private static final Logger log = Logger.getLogger(CommandProcessor.class
			.getName());

	private Map<String, Command> commands = new HashMap<String, Command>();
	private List<Command> defaultCommands = new ArrayList<Command>();
	private Map<String, Command> pageDefaultCommands = new HashMap<String, Command>();	

	private final ParameterSource parameters;
	private String currentPage;
	private String initialCommand;

	public void initCommands() {
		// Commands which will be executed for every page
		defaultCommands.add(new AuthorizeCommand());
		//defaultCommands.add(new SetCurrentUserCommand());
		
		//----------------------------------------------------
		pageDefaultCommands.put(Pages.CHOOSE_CONNECTION, new GetPluginListCommand ());
		pageDefaultCommands.put(Pages.ADD_CONNECTION, new LoadPluginDetailsCommand ());		
		pageDefaultCommands.put(Pages.EDIT_CONNECTION, new LoadConnectionDetailsCommand ()); //TODO
		pageDefaultCommands.put(Pages.PREFERENCES, new GetConnectionsForCurrentUserCommand ());		
		
		CommandList mainCommands = new CommandList ();
		mainCommands.add(new GetConnectionsForCurrentUserCommand ());
		//could be more
		pageDefaultCommands.put(Pages.MAIN, mainCommands);
		
		//-------------------------------------------------------
		commands.put(Commands.LOGOUT, new LogoutCommand ());
		commands.put(Commands.DELETE_CONNECTION, new DeleteConnectionCommand ());  //TODO
		commands.put(Commands.UPDATE_CONNECTION, new UpdateConnectionCommand ()); //TODO
		commands.put(Commands.ADD_CONNECTION, new AddConnectionCommand ()); 
		commands.put(Commands.CREATE_USER, new CreateUserCommand ());
		
	}
	
	public CommandProcessor(ParameterSource parameters) {
		currentPage = parameters.getParameter(Fields.PAGE);
		initialCommand = parameters.getParameter(Fields.COMMAND);

		log.info("processing the request: page=" + currentPage
				+ ", command=" + initialCommand);
		this.parameters = parameters;

		initCommands();
	}

	public Map<String, Object> process() {
		Map<String, Object> values = new HashMap<String, Object>();
		//enrich parameters - still not clear what to use - request or values
		values.put(Fields.PAGE, currentPage);
		
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
		Command commandsForPage = pageDefaultCommands.get(values.get(Fields.PAGE));
		if (commandsForPage != null) { //it could happen that some page dont have default commands
				commandsForPage.invoke(values, parameters);
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

	public String getInitialCommand() {
		return initialCommand;
	}

}
