package com.gpmedia.notimob.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Pair {
    @SuppressWarnings("unused")
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
	private final String mapKey;
    
    @Persistent
	private final String value;

	public Pair(String key, String value) {
		if (key == null || value == null) throw new NullPointerException ();
		this.mapKey = key;
		this.value = value;
	}

	public String getMapKey() {
		return mapKey;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pair) {
			Pair another = (Pair) obj;
			return mapKey.equals(another.getMapKey()) && value.equals(another.getValue());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return mapKey.hashCode()  + 7 * value.hashCode();
	}
	
	@Override
	public String toString() {
		return mapKey + ":" + value;
	}

}
