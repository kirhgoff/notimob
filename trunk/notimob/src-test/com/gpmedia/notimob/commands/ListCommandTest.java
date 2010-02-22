package com.gpmedia.notimob.commands;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.gpmedia.notimob.ParameterSource;

public class ListCommandTest {
	@Test
	public void testListCommand() throws Exception {
		Map<String, Object> values = new HashMap<String, Object>();
		CommandList commandList = new CommandList();
		commandList.add (new Command() {
			public void invoke(Map<String, Object> values, ParameterSource parameters) {
				values.put("test", "first");
			}
		});
		commandList.add (new Command() {
			public void invoke(Map<String, Object> values, ParameterSource parameters) {
				values.put("test", "second");
			}
		});
		((Command)commandList).invoke (values, null);
		Assert.assertEquals ("second", values.get("test"));
	}
}
