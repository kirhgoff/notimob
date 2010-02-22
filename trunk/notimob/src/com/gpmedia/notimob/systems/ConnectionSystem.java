package com.gpmedia.notimob.systems;

import com.gpmedia.notimob.dao.ConnectionDAO;
import com.gpmedia.notimob.model.Connection;
import com.gpmedia.notimob.model.ConnectionDetails;
import com.gpmedia.notimob.model.Plugin;
import com.gpmedia.notimob.model.User;

public class ConnectionSystem {
	

	public static Connection createConnection (User user, Plugin plugin, String username, String password, ConnectionDetails details) {
		
		Connection connection = new Connection ();
		connection.setUsername(username);
		connection.setPassword(password);
		connection.setPlugin(plugin);
		connection.setUser (user);
		connection.setDetails (details);
		
		connection = ConnectionDAO.store(connection);
		return connection;
	}

}
