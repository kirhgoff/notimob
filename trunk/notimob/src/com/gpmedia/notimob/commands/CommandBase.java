package com.gpmedia.notimob.commands;

import java.util.Map;

public class CommandBase {
	protected void forwardToPage(Map<String, Object> values, String target,
			String message) {
		values.put(Placeholder.ERROR_MESSAGE, message);
		values.put(Fields.PAGE, target);
	}

}
