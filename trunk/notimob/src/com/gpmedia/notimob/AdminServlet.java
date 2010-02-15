package com.gpmedia.notimob;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gpmedia.notimob.dao.ConnectionDAO;
import com.gpmedia.notimob.dao.PluginDAO;
import com.gpmedia.notimob.dao.UserDAO;
import com.gpmedia.notimob.model.Builder;
import com.gpmedia.notimob.model.Connection;
import com.gpmedia.notimob.model.Plugin;
import com.gpmedia.notimob.model.User;
import com.gpmedia.notimob.systems.Renderer;

@SuppressWarnings("serial")
public class AdminServlet extends NotimobServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		setup(request, response);

		String result;

		try {
			HashMap<String, Object> values = new HashMap<String, Object>();
			values.put("page", "admin");

			String command = request.getParameter("command");
			if ("checkThis".equals(command))
				values.put("content", checkThis());
			if ("resetData".equals(command))
				values.put("content", resetData());

			result = Renderer.render(values);
		} catch (Exception e) {
			result = Utils.printStackTrace(e);
		}

		response.getWriter().write(result);
	}

	private String checkThis() {
		createSampleData();
		testSampleData();

		return "Done";
	}

	private void testSampleData() {
	}

	private void createSampleData() {

		// Plugin plugin = new Plugin ();
		// plugin.setIcon ("/img/vkontakte.gif");
		// plugin.setLink ("http://vkontakte.ru");
		// plugin.setTitle("Vkontakte.ru");
		// plugin.setAlias("vkontakte");
		// plugin = PluginDAO.store (plugin);

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
			editForm("page-connection-pop3.tpl").
			instance();
		plugin = PluginDAO.store(plugin);
		
		User user = new Builder<User>(new User()).
			username ("test").
			password ("test").
			instance();
		UserDAO.store (user);
		
		return "Data has been reset succesfully";
	}

}
