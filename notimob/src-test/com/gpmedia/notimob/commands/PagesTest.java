package com.gpmedia.notimob.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.apphosting.api.ApiProxy;
import com.gpmedia.notimob.CommandProcessor;
import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.dao.UserDAO;
import com.gpmedia.notimob.model.Builder;
import com.gpmedia.notimob.model.User;
import com.gpmedia.notimob.systems.AuthSystem;

public class PagesTest {

	
//    private final LocalServiceTestHelper helper =
//        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() { 
        ApiProxy.setEnvironmentForCurrentThread(new TestEnvironment());
        //ApiProxy.setDelegate(new ApiProxyLocalImpl(new File(".")){});
    	
        //helper.setUp();
    }

    @After
    public void tearDown() {
        //helper.tearDown();
    }

	
    @Test
	public void testLogin() throws Exception {
		String username = "test123";
		String password = "test123";
		//assertFalse (AuthSystem.authorize(username, password));
		
		User user = new Builder<User>(new User ())
			.username(username)
			.password(password)
			.instance();
		
		UserDAO.store(user);
		
		//assertTrue (AuthSystem.authorize(username, password));
	}
    
    @Ignore
	@Test
	public void testAuthorize() throws Exception {
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
	
	@Test
	public void testChooseConnection() throws Exception {
		//go to page choose-connection
		//check that plugins is not empty
	}
	
	@Test
	public void testAddConnection() throws Exception {
		//go to add connection page
		//plugin_id should be specified
		//check: plugin is loaded and set up up
	}
	
	@Test
	public void testConnectionAdded() throws Exception {
		//command is add-connection
		//connection details are specified
		//new connection is added for current user
	}
}
