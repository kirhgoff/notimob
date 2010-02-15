package com.gpmedia.notimob.dao;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.KeyFactory;
import com.gpmedia.notimob.model.Connection;
import com.gpmedia.notimob.model.Plugin;


public class ConnectionDAO {

	public static Connection store(Connection connection) {
		//connection.setPlugin(KeyFactory.createKey(Plugin.class.getSimpleName(), plugin.getId ()));
		PersistenceManager pm = PMF.get().getPersistenceManager();
		connection.setPluginKey(KeyFactory.createKey(Plugin.class.getSimpleName(), connection.getPlugin().getId ()));
		connection = pm.makePersistent(connection);
		pm.close ();
		return connection;
	}
	
	public static void removeAll() {
		UtilDAO.removeAll(Connection.class);
	}
	
}
