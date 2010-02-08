package com.gpmedia.notimob.commands;

import java.util.Map;

public interface Command {
	void invoke (Map<String, Object> values, ParameterSource parameters);
}
