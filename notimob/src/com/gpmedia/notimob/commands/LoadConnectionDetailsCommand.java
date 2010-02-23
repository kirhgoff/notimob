package com.gpmedia.notimob.commands;

import java.util.Map;

import com.gpmedia.notimob.Command;
import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.dao.ConnectionDAO;
import com.gpmedia.notimob.model.Connection;

public class LoadConnectionDetailsCommand implements Command {

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		String idString = parameters.getParameter(Fields.CONNECTION_ID);
		if (idString == null || idString.length() == 0) {
			System.out.println("WARNING: trying to request edit connection without an id");
			return;
		}
		long id = Long.valueOf(idString);
		Connection connection = ConnectionDAO.getByID (id);
		values.put (Placeholder.CONNECTION, connection);
	}

}
