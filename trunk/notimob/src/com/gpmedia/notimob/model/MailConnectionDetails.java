package com.gpmedia.notimob.model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class MailConnectionDetails extends ConnectionDetails {
	
	@Persistent
	private String mailServerHost;
	
	@Persistent
	private Integer mailServerPort;

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerPort(Integer mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public Integer getMailServerPort() {
		return mailServerPort;
	}
}
