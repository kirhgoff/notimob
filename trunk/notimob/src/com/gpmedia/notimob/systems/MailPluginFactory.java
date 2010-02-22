package com.gpmedia.notimob.systems;

import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.commands.Fields;
import com.gpmedia.notimob.model.ConnectionDetails;
import com.gpmedia.notimob.model.MailConnectionDetails;

public class MailPluginFactory extends ConnectionDetailsFactory implements Fields{

	@Override
	public ConnectionDetails build(ParameterSource parameters) {
		MailConnectionDetails mailConnectionDetails = new MailConnectionDetails();
		mailConnectionDetails.setMailServerHost(parameters.getParameter(MAIL_SERVER_HOST));

		String portString = parameters.getParameter(MAIL_SERVER_PORT);
		mailConnectionDetails.setMailServerPort(Integer.valueOf(portString));
		
		return mailConnectionDetails;
	}

}
