package com.gpmedia.notimob.dao;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.gpmedia.notimob.model.User;

public class UserDAO {

	public static void store(User user) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(user);
		}
		finally {
			pm.close ();
		}
	}

	public static boolean exists(String username) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(User.class);
        query.setUnique(true);
        query.setFilter("username == usernameParam");
        query.declareParameters("String usernameParam");
        
        User existent = null;
        try {
    		//data = (DataSet) pm.getObjectById(DataSet.class, key);
        	existent = (User) query.execute(username);
        } 
        finally {
            pm.close();
        }
        
        return (existent != null);
	}

	public static boolean login(String username, String password) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(User.class);
        query.setUnique(true);
        query.setFilter("username == usernameParam && password == passwordParam");
        query.declareParameters("String usernameParam, String passwordParam");
        
        User result = null;
        try {
    		//data = (DataSet) pm.getObjectById(DataSet.class, key);
        	result = (User) query.execute(username, password);
        } 
        finally {
            pm.close();
        }
        
        return (result != null);
	}

}
