package com.gpmedia.notimob.dao;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

public class UtilDAO {

	//TODO make template
	public static <T> void removeAll(Class<T> clazz) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			//pm.currentTransaction().begin();
			Extent<T> extent = (Extent<T>) pm.getExtent(clazz);
			for (T record: extent) {
				pm.deletePersistent(record);
			}
			//pm.currentTransaction().commit();
		}
		catch (Exception e) {
			//pm.currentTransaction().rollback();
			throw new RuntimeException(e);
		}
		finally {
			pm.close ();
		}
	}
	
}
