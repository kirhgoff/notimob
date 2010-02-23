package com.gpmedia.notimob.commands;

import java.util.Map;

import com.gpmedia.notimob.Command;
import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.dao.ConnectionDAO;
import com.gpmedia.notimob.model.Connection;

public class UpdateConnectionCommand extends CommandBase implements Command {

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		String connectionIDString = parameters.getParameter(Fields.CONNECTION_ID);
		String username = parameters.getParameter(Fields.USERNAME);
		String password = parameters.getParameter(Fields.PASSWORD);
		
		if (connectionIDString == null || connectionIDString.length() == 0) {
			forwardToPage(values, Pages.EDIT_CONNECTION, "Invalid request");
			return;
		}
		
		if (username == null || username.length() == 0){
			forwardToPage(values, Pages.EDIT_CONNECTION, "Вы не ввели имя");
			return;
		}
		
		if (password == null || password.length() == 0){
			forwardToPage(values, Pages.EDIT_CONNECTION, "Вы не ввели пароль");
			return;
		}
		
		long connectionID = Long.valueOf(connectionIDString);
		Connection connection = ConnectionDAO.getByID(connectionID);
		connection.setUsername(username);
		connection.setPassword(password);
		ConnectionDAO.update (connection);
	}
}
