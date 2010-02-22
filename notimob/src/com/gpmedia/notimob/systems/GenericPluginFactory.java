package com.gpmedia.notimob.systems;

import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.model.ConnectionDetails;

//TODO get rid of these stupid factories
public class GenericPluginFactory extends ConnectionDetailsFactory {

	@Override
	public ConnectionDetails build(ParameterSource parameters) {
		return new ConnectionDetails ();
	}

}
