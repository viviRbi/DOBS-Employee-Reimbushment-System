package com.revature.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.*;

import jdk.internal.org.jline.utils.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	public static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class);
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {

		System.out.println("Someone sent a reques--t");
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		
		ObjectMapper om = new ObjectMapper();

		User u = om.readValue(body, User.class); 

		String username = u.getUsername();
		String password = u.getPassword();	
			
		if (u != null) {
			// get the current session OR create one if it doesn't exist
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			
			// Attaching the print writer to our response
			PrintWriter pw = resp.getWriter();
			resp.setContentType("application/json");
			
			pw.println(om.writeValueAsString(u));
	
		} else {
			resp.setStatus(204); // Still have a connection, but no user is found
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		//resp.sendRedirect("index.html");
		
		ObjectMapper mapper = new ObjectMapper();
		User u = new User();
		//u = mapper.readValue(body, User.class);
		
		String username = "jjj";
		String password = "vvvvvvv";
		
		u.setUsername(username);
		u.setPassword(password);
		
		System.out.println("----------aa");
		System.out.println(u.getUsername());
		
		if (u != null) {
			HttpSession session = req.getSession();
			session.setAttribute(username, username);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			
			String userJSON = mapper.writeValueAsString(u);
			out.println(u);
		}
	}
}
