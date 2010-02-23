package com.gpmedia.notimob.systems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.gpmedia.notimob.model.Pair;

//Not synchronized
public class MapAsSet {

	private Map<String, String> map = new HashMap<String, String> ();

	public void put(String string, String string2) {
		map.put (string, string2);
	}

	public Set<Pair> asSet() {
		Set<Pair> set = new HashSet<Pair> ();
		for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			set.add(new Pair (key, map.get(key)));
		}
		return set;
	}
}
