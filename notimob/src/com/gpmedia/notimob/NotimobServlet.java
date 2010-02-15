package com.gpmedia.notimob;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gpmedia.notimob.systems.AuthSystem;
import com.gpmedia.notimob.systems.Renderer;

@SuppressWarnings("serial")
public class NotimobServlet extends HttpServlet {

	public void setup(HttpServletRequest request, HttpServletResponse response) {
		//initialize all the systems
		AuthSystem.setCurrentSesssion (request.getSession());
		Renderer.init(getServletContext());
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
	}

}
