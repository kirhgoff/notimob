package com.gpmedia.notimob.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gpmedia.notimob.CommandProcessor;
import com.gpmedia.notimob.UnmodifiableRequestProxy;
import com.gpmedia.notimob.systems.Renderer;

@SuppressWarnings("serial")
public class DispatcherServlet extends NotimobServlet {
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

}

