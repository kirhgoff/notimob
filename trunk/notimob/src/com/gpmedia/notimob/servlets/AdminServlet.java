package com.gpmedia.notimob.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gpmedia.notimob.ParameterSource;
import com.gpmedia.notimob.Utils;
import com.gpmedia.notimob.commands.Fields;
import com.gpmedia.notimob.dao.ConnectionDAO;
import com.gpmedia.notimob.dao.PluginDAO;
import com.gpmedia.notimob.dao.UserDAO;
import com.gpmedia.notimob.model.Builder;
import com.gpmedia.notimob.model.Connection;
import com.gpmedia.notimob.model.ConnectionDetails;
import com.gpmedia.notimob.model.Plugin;
import com.gpmedia.notimob.model.User;
import com.gpmedia.notimob.systems.ConnectionSystem;
import com.gpmedia.notimob.systems.PluginSystem;
import com.gpmedia.notimob.systems.Renderer;
import com.gpmedia.notimob.systems.UserSystem;

@SuppressWarnings("serial")
public class AdminServlet extends NotimobServlet {
	
	private static Logger logger = Logger.getLogger(AdminServlet.class.getName());
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		setup(request, response);
//		User currentUser = AuthSystem.getCurrentUser();
//		if (currentUser == null || currentUser.isAdmin())
//			throw new RuntimeException("Go away hacky hacker");
		
		String result;
		HashMap<String, Object> values = new HashMap<String, Object>();
		values.put("page", "admin");

		try {

			String command = request.getParameter("command");
			if ("checkThis".equals(command))
				values.put("content", checkThis());
			if ("resetData".equals(command))
				values.put("content", resetData());

		} catch (Exception e) {
			values.put("content", Utils.printStackTrace(e));
		}

		result = Renderer.render(values);
		response.getWriter().write(result);
	}

	private String checkThis() {
		resetData();
		checkConnectionDetails();
		return "Done";
	}

	private void checkConnectionDetails() {
		
		final Map<String, String> parameters = new HashMap<String, String>();
		
		ParameterSource source = new ParameterSource() {
			@Override
			public String getParameter(String name) {
				return parameters.get(name);
			}
		}; 
		
		User user = UserDAO.findByName("nicoz");
		Plugin genericPlugin = PluginDAO.findByAlias("vkontakte");
		Plugin mailPlugin = PluginDAO.findByAlias("mailplugin");
		
		ConnectionDetails details = PluginSystem.createConnectionDetails (genericPlugin, source);
		ConnectionSystem.createConnection(user, genericPlugin, "generic", "generic_p", details);
		
		parameters.put(Fields.MAIL_SERVER_HOST, "mail.ru");
		parameters.put(Fields.MAIL_SERVER_PORT, "110");
		
		details = PluginSystem.createConnectionDetails (mailPlugin, source);
		ConnectionSystem.createConnection(user, mailPlugin, "mail", "mail_p", details);
		
		List<Connection> connections = ConnectionDAO.findConnectionsForUser(user);

		if (connections.size() == 0) throw new AssertionError("Size should not be null");
		logger.info ("there should not be " + connections.size() + " connections");
		for (Connection connection: connections) {
			logger.info ("connection: " + connection);
		}
	}

	public void createSampleData() {

		Plugin plugin = PluginDAO.findByAlias("vkontakte");

		Connection connection = new Connection();
		connection.setPlugin(plugin);
		connection.setUsername("testuser");
		connection.setPassword("testpassword");
		ConnectionDAO.store(connection);

		connection = new Connection();
		connection.setPlugin(plugin);
		connection.setUsername("testuser2");
		connection.setPassword("testpassword2");
		ConnectionDAO.store(connection);
	}

	private String resetData() {
		ConnectionDAO.removeAll();
		PluginDAO.removeAll();
		//TODO remove only admin users not to harm live people
		UserDAO.removeAll();

		Plugin plugin = new Builder<Plugin>(new Plugin()).
			icon("/img/vkontakte.gif").
			link("http://vkontakte.ru").
			title("Vkontakte.ru").
			alias("vkontakte").
			instance();
		plugin = PluginDAO.store(plugin);
		
		plugin = new Builder<Plugin>(new Plugin()).
			icon ("/img/mail_icon.gif").
			link("pop3").
			title("Эл. почта по POP3").
			alias("mailplugin").
			editForm("page-connection-pop3.html").
			instance();
		plugin = PluginDAO.store(plugin);

		UserSystem.createAdmin("kirhgoff");
		UserSystem.createAdmin("bams");
		UserSystem.createAdmin("nicoz");		

		return "Data has been reset succesfully";
	}

}
