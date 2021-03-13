package com.revature.servlet.profile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.SendingAlert;
import com.revature.model.User;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImp;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;
import com.revature.servlet.viewReimbushment.ViewAllPendingRequest;

@WebServlet("/updateProfile")
public class UpdateEmployeeProfile extends HttpServlet{
	public static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(UpdateEmployeeProfile.class);
	// Convert obj to json and vice versa
	ObjectMapper om = new ObjectMapper();
	
	// Change to do post later
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		log.info("Updating profile");
		//Get session and check user role
		HttpSession session = req.getSession();
		String role = (String) session.getAttribute("role");
		
		if (role.contentEquals("employee")) {
			BufferedReader reader = req.getReader();
			StringBuilder s = new StringBuilder();
			
			String line = reader.readLine();
			while (line != null) {
				s.append(line);
				line = reader.readLine();
			}
			
			String body = s.toString();
			
			ObjectMapper om = new ObjectMapper();
			
			//User user = om.readValue(body, User.class);
			Employee emp = om.readValue(body, Employee.class);
			//emp.setUsername(user.getUsername());
			//emp.setPassword(user.getPassword());
			
			// Change normal password to hashed password 
			UserService u = new UserServiceImp();
			String hashedPassword = u.hashPassword(emp.getPassword());
			emp.setPassword(hashedPassword);
			
			String newHashedPassword = u.hashPassword(emp.getNewpassword());
			emp.setNewpassword(newHashedPassword);
			
			emp.setId((int)session.getAttribute("id"));
			emp.setRole("employee");
			log.debug("Value passed by employee: " + emp.toString());
			
			// Update to database
			EmployeeService e = new EmployeeServiceImp();
			boolean updated = e.updateProfile(emp);
			
			PrintWriter pw = resp.getWriter();
			
			if (updated == true) {
				log.debug("Successfully save ---" );
				// hide user password
				emp.setPassword(null);
				emp.setRole("employee");
				
				SendingAlert alert = new SendingAlert();
				alert.setStatusCode(200);
				alert.setDescription("Update");
				alert.setMessage("Successfully update your profile");
				resp.setContentType("application/json");
				pw.println(om.writeValueAsString(alert));
			} else {
				log.debug("Fail to save" );
				SendingAlert err = new SendingAlert();
				err.setStatusCode(204);
				err.setDescription("No Content Found");
				err.setMessage("Cannot update your profile. Please check your password");
				resp.setContentType("application/json");
				pw.println(om.writeValueAsString(err));
			}
		} else resp.sendRedirect("index.html");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
		resp.sendRedirect("index.html");
	}
}
