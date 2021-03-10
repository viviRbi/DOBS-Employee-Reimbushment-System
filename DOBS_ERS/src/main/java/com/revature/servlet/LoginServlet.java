package com.revature.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.*;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;



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
		UserService uService = new UserServiceImp();
		
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
		
		// Get each field of User obj obtained from request 
		String username = u.getUsername();
		String password = u.getPassword();	
		String role = u.getRole();
		
		// Get User data in Database
		User user = new User();
		user = uService.logIn(username, password, role);
		
		// Attaching the print writer to our response
		PrintWriter pw = resp.getWriter();
		
		// If user exist in Database
		if (user != null) {
			// get the current session OR create one if it doesn't exist
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			resp.setContentType("application/json");
			pw.println(om.writeValueAsString(user));
		} else {
			SendingAlert err = new SendingAlert();
			err.setStatusCode(204);
			err.setDescription("No Content Found");
			err.setMessage("Username or password is incorrect");
			resp.setContentType("application/json");
			pw.println(om.writeValueAsString(err));
		}
		pw.close();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		resp.sendRedirect("index.html");	
	}
}
