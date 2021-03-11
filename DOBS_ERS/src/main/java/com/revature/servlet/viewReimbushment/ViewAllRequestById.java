package com.revature.servlet.viewReimbushment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.ManagerService;
import com.revature.services.ManagerServiceImp;
import com.revature.services.UserService;
import com.revature.services.UserServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Reimbushment;
import com.revature.model.SendingAlert;

@WebServlet("/viewAllRequestById")
public class ViewAllRequestById extends HttpServlet{
	public static final long serialVersionUID = 1L;
	// Convert obj to json and vice versa
	ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		int eid = Integer.parseInt(req.getParameter("eid"));
		System.out.println(eid);
		UserService u = new UserServiceImp();	
		List<Reimbushment> allRe = u.viewAllReimbushmentRequestById(eid) ;
		
		System.out.println("view all request");
		System.out.println(allRe);
		// Prepare to write response
		PrintWriter pw = resp.getWriter();
		
		// If able to get at least 1 employee
		if (allRe != null) {
			resp.setContentType("application/json");
			String jsonReIs = om.writeValueAsString(allRe);
			pw.println(jsonReIs);
		}else {
			SendingAlert err = new SendingAlert();
			err.setStatusCode(204);
			err.setDescription("No Content Found");
			err.setMessage("There is no reimbushment.");
			resp.setContentType("application/json");
			pw.println(om.writeValueAsString(err));
		}
		pw.close();
	}
}
