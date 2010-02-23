package com.gpmedia.notimob.systems;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.gpmedia.notimob.MapBuilder;
import com.gpmedia.notimob.Utils;

public class Renderer {
	private static Map<String, String> pageToTemplate = new HashMap<String, String>();
	private static VelocityEngine velocityEngine;
	
	static {
		pageToTemplate.put("main", "page-main.html");
		pageToTemplate.put("main-unlogged", "page-main-unlogged.html");
		pageToTemplate.put("registration", "page-registration.html");
		pageToTemplate.put("login", "page-login.html");
		pageToTemplate.put("add-connection", "page-add-connection.html");
		pageToTemplate.put("add-connection", "page-add-connection.html");
		pageToTemplate.put("choose-connection", "page-choose-connection.html");
		pageToTemplate.put("edit-connection", "page-edit-connection.html");
		pageToTemplate.put("preferences", "page-preferences.html");
		pageToTemplate.put("admin", "page-admin.html");		
	}
	
	public static String render(Map<String, Object> values) {
		String pageName = (String) values.get("page");
		if (pageName == null) pageName = "login";
		String templateName = pageToTemplate.get(pageName); 
		String mainContent = null;
		try {
			String page = renderTemplate(templateName, values);
			mainContent = renderTemplate("main.html", new MapBuilder().put("body", page).instance());			
		} catch (Exception e) {
			mainContent = Utils.printStackTrace(e);
		}
		return mainContent;
	}

	@SuppressWarnings("unchecked")
	public static String renderTemplate (String templateName, Map<String, Object> objects) throws Exception {
		if (!templateName.equals("main.html")) {
			System.out.println("----------------------------------------------------------------------");
			System.out.println("Render: " + templateName + " context is: " + objects);
		}
        VelocityContext context = new VelocityContext();
        for (Iterator iterator = objects.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
	        context.put(key, objects.get(key));			
		}

        StringWriter writer = new StringWriter();
        velocityEngine.mergeTemplate(templateName, "UTF-8", context, writer);
		return writer.toString();
    }
    
    public static void init (ServletContext context) {
		if (velocityEngine == null) {
			try {
				initVelocity(context.getRealPath("/templates"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
    
    public static void initVelocity (String templatePath) throws Exception {
    	System.out.println("Initializing velocity context, path is: " + templatePath);
        ExtendedProperties eprops = new ExtendedProperties();
        eprops.setProperty("file.resource.loader.path", templatePath);

        velocityEngine = new VelocityEngine();
        velocityEngine.setExtendedProperties(eprops);
        velocityEngine.init();
    }
    

}
