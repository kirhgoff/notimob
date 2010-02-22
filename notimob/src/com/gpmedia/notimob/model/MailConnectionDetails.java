package com.gpmedia.notimob.model;

import javax.jdo.annotations.Persistent;

public class MailConnectionDetails extends ConnectionDetails {
	
	@Persistent
	private String email;
	
	@Persistent
	private String mailServerHost;
	
	@Persistent
	private String mailServerPort;
	
	@Persistent
	private String mailServerLimitTimeInDays;
}
