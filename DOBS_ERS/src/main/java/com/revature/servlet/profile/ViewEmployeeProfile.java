package com.revature.servlet.profile;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.SendingAlert;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImp;

@WebServlet("/viewProfile")
public class ViewEmployeeProfile extends HttpServlet{
	public static final long serialVersionUID = 1L;
	// Convert obj to json and vice versa
	ObjectMapper om = new ObjectMapper();
	
	// Change to do post later
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		
		int eid = Integer.parseInt(req.getParameter("eid"));
		
		EmployeeService e = new EmployeeServiceImp();
		Employee info = e.viewProfile(eid);
		
		PrintWriter pw = resp.getWriter();
		
		if (info != null) {
			resp.setContentType("application/json");
			String jsonReIs = om.writeValueAsString(info);
			pw.println(jsonReIs);
		} else {
			SendingAlert err = new SendingAlert();
			err.setStatusCode(204);
			err.setDescription("No Content Found");
			err.setMessage("Cannot update your profile");
			resp.setContentType("application/json");
			pw.println(om.writeValueAsString(err));
		}
	}
}
