package com.revature.servlet.logInOut;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LogOutServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		log.info("logout");
		if(req.getSession(false) == null) {
			resp.sendRedirect("index.html");
		}
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		resp.sendRedirect("index.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
