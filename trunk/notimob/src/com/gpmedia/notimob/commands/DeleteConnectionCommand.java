package com.gpmedia.notimob.commands;

import java.util.Map;

import com.gpmedia.notimob.Command;
import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.dao.ConnectionDAO;

public class DeleteConnectionCommand extends CommandBase implements Command {

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		String connectionIDString = parameters.getParameter(Fields.CONNECTION_ID);
		
		if (connectionIDString == null || connectionIDString.length() == 0) {
			forwardToPage(values, Pages.EDIT_CONNECTION, "Invalid request");
			return;
		}
		
		long connectionID = Long.valueOf(connectionIDString);
		ConnectionDAO.removeByID (connectionID);
	}

}
