package com.gpmedia.notimob;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.gpmedia.notimob.commands.CommandProcessor;
import com.gpmedia.notimob.commands.UnmodifiableRequestProxy;
import com.gpmedia.notimob.systems.AuthSystem;

@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {
//	private Logger log = Logger.getLogger(DispatcherServlet.class.getName());
	
	private static Map<String, String> pageToTemplate = new HashMap<String, String>();
	private VelocityEngine velocityEngine;
	
	static {
		pageToTemplate.put("main", "page-main.html");
		pageToTemplate.put("main-unlogged", "page-main-unlogged.html");
		pageToTemplate.put("registration", "page-registration.html");
		pageToTemplate.put("login", "page-login.html");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException {
		doGet (request, response);
	}	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		setup(request, response);
		
		CommandProcessor processor = new CommandProcessor(new UnmodifiableRequestProxy (request));		
		Map<String, Object> values = processor.process ();
		String mainContent = render(values);
		
		response.getWriter().println(mainContent);
	}

	private void setup(HttpServletRequest request, HttpServletResponse response) {
		if (velocityEngine == null) {
			try {
				initVelocity(getServletContext().getRealPath("/templates"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//initialize all the systems
		AuthSystem.setCurrentSesssion (request.getSession());
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
	}

	private String render(Map<String, Object> values) {
		String pageName = (String) values.get("page");
		if (pageName == null) pageName = "login";
		String templateName = pageToTemplate.get(pageName); 
		String mainContent = null;
		try {
			String page = renderTemplate(templateName, values);
			mainContent = renderTemplate("main.html", new MapBuilder().put("body", page).instance());			
		} catch (Exception e) {
			mainContent = printStackTrace(e);
		}
		return mainContent;
	}

	private String printStackTrace(Exception e) {
		String mainContent;
		StringWriter writer = new StringWriter ();
		PrintWriter stream = new PrintWriter(writer, true);
		e.printStackTrace(stream);
		stream.flush();
		writer.flush();
		
		mainContent = writer.toString();
		return mainContent;
	}
	
    @SuppressWarnings("unchecked")
	public String renderTemplate (String templateName, Map<String, Object> objects) throws Exception {
        VelocityContext context = new VelocityContext();
        for (Iterator iterator = objects.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
	        context.put(key, objects.get(key));			
		}

        StringWriter writer = new StringWriter();
        velocityEngine.mergeTemplate(templateName, "UTF-8", context, writer);
		return writer.toString();
    }
    
    public void initVelocity (String templatePath) throws Exception {
    	System.out.println("Initializing velocity context, path is: " + templatePath);
        ExtendedProperties eprops = new ExtendedProperties();
        eprops.setProperty("file.resource.loader.path", templatePath);

        velocityEngine = new VelocityEngine();
        velocityEngine.setExtendedProperties(eprops);
        velocityEngine.init();
    }
	
}

