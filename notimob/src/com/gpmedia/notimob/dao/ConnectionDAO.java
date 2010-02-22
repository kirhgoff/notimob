package com.gpmedia.notimob.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.KeyFactory;
import com.gpmedia.notimob.model.Connection;
import com.gpmedia.notimob.model.Plugin;
import com.gpmedia.notimob.model.User;


public class ConnectionDAO {

	public static Connection store(Connection connection) {
		//connection.setPlugin(KeyFactory.createKey(Plugin.class.getSimpleName(), plugin.getId ()));
		PersistenceManager pm = PMF.get().getPersistenceManager();
		connection.setPluginKey(KeyFactory.createKey(Plugin.class.getSimpleName(), connection.getPlugin().getId ()));
		connection.setUserKey(KeyFactory.createKey(User.class.getSimpleName(), connection.getUser().getId ()));
		try {
			connection = pm.makePersistent(connection);
		}
		finally {
			pm.close ();
		}
		
		return connection;
	}
	
	public static void removeAll() {
		UtilDAO.removeAll(Connection.class);
	}

	@SuppressWarnings("unchecked")
	public static List<Connection> findConnectionsForUser(User user) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String queryStr = "select from " + Connection.class.getName() + 
			" where userKey == :userKeyParam";
		List<Connection> connections = new ArrayList<Connection> ();
		try {
			connections = (List<Connection>) pm.newQuery(queryStr).execute(KeyFactory.createKeyString(User.class.getName(), user.getId()));
		}
		finally {
			pm.close ();
		}
		return connections;
	}
	
}
