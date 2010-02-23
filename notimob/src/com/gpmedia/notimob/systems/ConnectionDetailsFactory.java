package com.gpmedia.notimob.systems;

import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.model.ConnectionDetails;

public abstract class ConnectionDetailsFactory {
	
	public abstract ConnectionDetails build (ParameterSource parameters);
	
}
