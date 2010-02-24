package com.gpmedia.notimob.dao;

import java.util.ArrayList;
import java.util.Iterator;
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
	public static List<Connection> findConnectionsForUser(User user) {
		//TODO details are empty
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(Connection.class);
        query.setFilter("userKey == userKeyParam");
        query.declareParameters(Key.class.getName() + " userKeyParam");
        
    	List<Connection> connections = new ArrayList<Connection> ();        
    	try {
    		List<Connection> data = (List<Connection>) query.execute(user.getKey());
        	for (Iterator iterator = data.iterator(); iterator.hasNext();) {
				Connection connection = (Connection) iterator.next();
				connections.add (connection);
				connection.setPlugin((Plugin) pm.getObjectById(Plugin.class, connection.getPluginKey()));
				connection.setUser(user);
			}
        } 
        finally {
            pm.close();
        }
        return connections;
	}

	public static Connection getByID(long id) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Connection connection = new Connection ();
    	try {
    		connection = pm.getObjectById(Connection.class, id);
			connection.setPlugin((Plugin) pm.getObjectById(Plugin.class, connection.getPluginKey()));
			connection.setUser((User) pm.getObjectById(User.class, connection.getUserKey()));
        } 
        finally {
            pm.close();
        }
        return connection;
	}

	public static void update(Connection connection) {
		//actually its more efficient to get object and update its fields,
		// then to close pm and everything will be saved
		//but fuck the efficiency
		store (connection);
	}

	public static void removeByID(long id) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
    	try {
    		Connection connection = getByID(id); //how to optimize?
    		pm.deletePersistent(connection);
        } 
        finally {
            pm.close();
        }
	}	
}
