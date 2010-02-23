package com.gpmedia.notimob.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    
    @Persistent
    private String username;

    @Persistent
    private String password;

    @Persistent
    private boolean isAdmin;
    
    @Persistent
    private String email;

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean value) {
		isAdmin = value;
	}

//	public void setKey(Key key) {
//		this.key = key;
//	}

	public Key getKey() {
		return key;
	}
	
	@Override
	public String toString() {
		return "User {key:" + getKey () + ", username:" + getUsername() + (isAdmin () ? ", admin" : "") + "}"; 
	}
	
}
