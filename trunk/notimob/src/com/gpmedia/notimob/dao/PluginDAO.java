package com.gpmedia.notimob.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.gpmedia.notimob.model.Plugin;

public class PluginDAO {
	private final static Logger log = Logger.getLogger(PluginDAO.class.getName());
	
	public static Plugin store(Plugin plugin) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		plugin = pm.makePersistent(plugin);
		pm.close ();
		return plugin;
	}
	
	@SuppressWarnings("unchecked")
	public static Plugin findByAlias (String pluginAlias) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query query = pm.newQuery(Plugin.class);
        //query.setUnique(true); - return back
        query.setFilter("alias == aliasParam");
        query.declareParameters("String aliasParam");
        
        Plugin plugin = null;
        try {
        	List<Plugin> plugins = (List<Plugin>) query.execute(pluginAlias);
        	if (plugins.size() != 0) {
        		plugin = plugins.get(0);
        		log.warning("Several plugins detected for alias " + pluginAlias);
        	} 
        } 
        finally {
            pm.close();
        }
        
        return plugin;
	}
	
	public static void removeAll() {
		UtilDAO.removeAll(Plugin.class);
	}

	public static List<Plugin> findAll() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Plugin> plugins = new ArrayList<Plugin> ();
		try {
			Extent<Plugin> extent = (Extent<Plugin>) pm.getExtent(Plugin.class);
			for (Plugin record: extent) {
				plugins.add (record);
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			pm.close ();
		}
		return plugins;
	}

	public static Plugin findByID(long pluginID) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
