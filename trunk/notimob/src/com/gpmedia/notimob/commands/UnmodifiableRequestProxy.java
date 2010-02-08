package com.gpmedia.notimob.commands;

import javax.servlet.http.HttpServletRequest;

public class UnmodifiableRequestProxy implements ParameterSource {
	//private static final Logger log = Logger.getLogger(UnmodifiableRequestProxy.class.getName());
	
	private HttpServletRequest request;
	
	public UnmodifiableRequestProxy (HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		return request.getParameter(name);
	}

}
