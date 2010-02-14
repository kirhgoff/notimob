package com.gpmedia.notimob;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gpmedia.notimob.systems.AuthSystem;
import com.gpmedia.notimob.systems.Renderer;

@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {
//	private Logger log = Logger.getLogger(DispatcherServlet.class.getName());
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException {
		doGet (request, response);
	}	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		setup(request, response);
		
		CommandProcessor processor = new CommandProcessor(new UnmodifiableRequestProxy (request));		
		Map<String, Object> values = processor.process ();
		String mainContent = Renderer.render(values);
		
		response.getWriter().println(mainContent);
	}

	private void setup(HttpServletRequest request, HttpServletResponse response) {
		//initialize all the systems
		AuthSystem.setCurrentSesssion (request.getSession());
		Renderer.init(getServletContext());
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
	}

}

