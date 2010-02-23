package com.gpmedia.notimob.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.gpmedia.notimob.model.Connection;
import com.gpmedia.notimob.model.Plugin;
import com.gpmedia.notimob.model.User;


public class ConnectionDAO {

	public static Connection store(Connection connection) {
		//connection.setPlugin(KeyFactory.createKey(Plugin.class.getSimpleName(), plugin.getId ()));
		PersistenceManager pm = PMF.get().getPersistenceManager();
		connection.setPluginKey(connection.getPlugin().getKey ());
		connection.setUserKey(connection.getUser().getKey ());
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
	private List<Connection> find(User user) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(Plugin.class);
        query.setUnique(true);
        query.setFilter("userKey == userKeyParam");
        query.declareParameters("Key userKeyParam");
        
    	List<Connection> connections;        
    	try {
        	connections = (List<Connection>) query.execute(user.getKey());
        } 
        finally {
            pm.close();
        }
        return connections;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Connection> findConnectionsForUser(User user) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String queryStr = "select from " + Connection.class.getName() + 
			" where userKey == :userKeyParam";
		List<Connection> connections = new ArrayList<Connection> ();
		try {
			connections = (List<Connection>) pm.newQuery(queryStr).execute(user.getKey ());
		}
		finally {
			pm.close ();
		}
		return connections;
	}
	
}
