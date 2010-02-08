package com.gpmedia.notimob.commands;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class AuthorizeCommandTest {

	@Test
	public void testUseCase() throws Exception {
		final Map<String, String> parameters = new HashMap<String, String>();
		
		CommandProcessor processor = new CommandProcessor(new ParameterSource() {
			@Override
			public String getParameter(String name) {
				return parameters.get(name);
			}
		}); 

		parameters.put("page", "login");
		parameters.put("login", "neya");
		parameters.put("password", "nepravilny");
		Map<String, Object> values = processor.process();
		Assert.assertEquals ("Incorrect password", values.get("errorMessage"));
		Assert.assertEquals ("login", values.get("page"));
		
		parameters.put("login", "test");
		parameters.put("password", "test");
		values = processor.process();
		Assert.assertEquals (null, values.get("errorMessage"));
		Assert.assertEquals ("main", values.get("page"));
		
		parameters.put("login", null);
		parameters.put("password", null);
		values = processor.process();
		Assert.assertEquals (null, values.get("errorMessage"));
		Assert.assertEquals ("login", values.get("page"));

		parameters.put("login", "");
		parameters.put("password", "");
		values = processor.process();
		Assert.assertEquals (null, values.get("errorMessage"));
		Assert.assertEquals ("login", values.get("page"));
	}
}
