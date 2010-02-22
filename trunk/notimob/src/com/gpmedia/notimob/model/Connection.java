package com.gpmedia.notimob.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Connection {
    @SuppressWarnings("unused")
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private Key pluginKey;
    
    @NotPersistent
    private Plugin plugin; //will be assigned by DAO
    
    @Persistent
    private String username;

    @Persistent
    private String password;

    @Persistent    
	private ConnectionDetails details;
    
    public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}

	public Plugin getPlugin() {
		return plugin;
	}

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

	public void setPluginKey(Key pluginKey) {
		this.pluginKey = pluginKey;
	}

	public Key getPluginKey() {
		return pluginKey;
	}

	public void setDetails(ConnectionDetails details) {
		this.details = details;
	}
    
}
