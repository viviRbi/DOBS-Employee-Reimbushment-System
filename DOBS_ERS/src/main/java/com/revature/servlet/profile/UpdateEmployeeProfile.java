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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.SendingAlert;
import com.revature.model.User;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImp;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;

@WebServlet("/updateProfile")
public class UpdateEmployeeProfile extends HttpServlet{
	public static final long serialVersionUID = 1L;
	// Convert obj to json and vice versa
	ObjectMapper om = new ObjectMapper();
	
	// Change to do post later
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		
		//Get session and check user role
		HttpSession session = req.getSession();
		String role = (String) session.getAttribute("role");
		
		if (role == "employee") {
			BufferedReader reader = req.getReader();
			StringBuilder s = new StringBuilder();
			
			String line = reader.readLine();
			while (line != null) {
				s.append(line);
				line = reader.readLine();
			}
			
			String body = s.toString();
			
			ObjectMapper om = new ObjectMapper();
	
			Employee emp = om.readValue(body, Employee.class);
			
			// Change normal password to hashed password 
			UserService u = new UserServiceImp();
			String hashedPassword = u.hashPassword(emp.getPassword());
			emp.setPassword(hashedPassword);
	
			// Update to database
			EmployeeService e = new EmployeeServiceImp();
			boolean updated = e.updateProfile(emp);
			
			PrintWriter pw = resp.getWriter();
			
			if (updated == true) {
				// hide user password
				emp.setPassword(null);
				emp.setRole("employee");
				
				resp.setContentType("application/json");
				String jsonReIs = om.writeValueAsString(emp);
				pw.println(jsonReIs);
			} else {
				SendingAlert err = new SendingAlert();
				err.setStatusCode(204);
				err.setDescription("No Content Found");
				err.setMessage("Cannot update your profile");
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
