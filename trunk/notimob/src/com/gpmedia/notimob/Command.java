package com.gpmedia.notimob;

import java.util.Map;


public interface Command {
	void invoke (Map<String, Object> values, ParameterSource parameters);
}
