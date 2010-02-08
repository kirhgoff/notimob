package com.gpmedia.notimob;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder {
	private Map<String, Object> data = new HashMap<String, Object> ();
	
	public MapBuilder() {
	}
	
	public MapBuilder put (String key, Object value) {
		data.put(key, value);
		return this;
	}

	public Map<String, Object> instance() {
		return data;
	}
}
