package com.gpmedia.notimob.commands;

import java.util.Map;

import com.gpmedia.notimob.ParameterSource;

public interface Command {
	void invoke (Map<String, Object> values, ParameterSource parameters);
}
