package com.gpmedia.notimob.model;

import java.lang.reflect.Method;

import org.apache.velocity.util.StringUtils;

public class Builder<T> {
	private T object;

	public Builder (T object) {
		this.object = object;
	}
	
	public Builder<T> assignField (String name, Object value) {
		try {
			String methodName = "set" + StringUtils.capitalizeFirstLetter(name);
			Method method = object.getClass().getMethod(methodName, new Class [] {value.getClass()});
			method.invoke (object, new Object [] {value});
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
		return this;
	}
	
	public Builder <T> username (String username) {
		return assignField ("username", username);
	}
	
	public Builder <T> password (String password) {
		return assignField ("password", password);
	}
	
	public Builder <T> plugin (Plugin plugin) {
		return assignField ("plugin", plugin);
	}

	public Builder <T> user (User user) {
		return assignField ("user", user);
	}

	public Builder<T> icon(String value) {
		return assignField ("icon", value);
	}
	
	public Builder<T> link(String value) {
		return assignField ("link", value);
	}

	public Builder<T> alias(String value) {
		return assignField ("alias", value);
	}

	public Builder<T> title(String value) {
		return assignField ("title", value);
	}
	
	public Builder<T> editForm(String value) {
		return assignField ("editForm", value);
	}

	public T instance() {
		return object;
	}
	
}
