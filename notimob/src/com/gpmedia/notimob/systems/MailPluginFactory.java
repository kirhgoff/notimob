package com.gpmedia.notimob.systems;

import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.commands.Fields;
import com.gpmedia.notimob.model.ConnectionDetails;

public class MailPluginFactory extends ConnectionDetailsFactory implements Fields{

	@Override
	public ConnectionDetails build(ParameterSource source) {
		MapAsSet parameters = new MapAsSet();
		
		ConnectionDetails mailConnectionDetails = new ConnectionDetails();
		parameters.put(MAIL_SERVER_HOST, source.getParameter(MAIL_SERVER_HOST));
		parameters.put(MAIL_SERVER_PORT, source.getParameter(MAIL_SERVER_PORT));
		mailConnectionDetails.setParameters(parameters.asSet());
		
		return mailConnectionDetails;
	}

}
