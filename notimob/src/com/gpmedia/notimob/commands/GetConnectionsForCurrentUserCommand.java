package com.gpmedia.notimob.commands;

import java.util.List;
import java.util.Map;

import com.gpmedia.notimob.Command;
import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.dao.ConnectionDAO;
import com.gpmedia.notimob.model.Connection;
import com.gpmedia.notimob.model.User;

public class GetConnectionsForCurrentUserCommand implements Command {

	@Override
	public void invoke(Map<String, Object> values, ParameterSource parameters) {
		List<Connection> connections = ConnectionDAO.findConnectionsForUser((User) values.get(Placeholder.CURRENT_USER));
		values.put(Placeholder.CONNECTIONS, connections);
	}

}
