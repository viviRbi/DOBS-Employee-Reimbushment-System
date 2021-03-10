package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.SendingAlert;
import com.revature.services.ManagerService;
import com.revature.services.ManagerServiceImp;

@WebServlet("/viewReimbushmentRequestById")
public class ViewReimbushmentRequestById extends HttpServlet{
	private static final long serialVersionUID = 1L;
	ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		ManagerService m = new ManagerServiceImp();	
		List<Employee> allEmployees = m.viewAllEmployees();
		
		System.out.println(req.getParameter("status"));
		System.out.println(req.getParameter("eid"));
		// Prepare to write response
		PrintWriter pw = resp.getWriter();
		
		// If able to get at least 1 employee
		if (allEmployees != null) {
			resp.setContentType("application/json");
			String jsonEmployees = om.writeValueAsString(allEmployees);
			pw.println(jsonEmployees);
		}else {
			SendingAlert err = new SendingAlert();
			err.setStatusCode(204);
			err.setDescription("No Content Found");
			err.setMessage("There is no employee. Something goes wrong");
			resp.setContentType("application/json");
			pw.println(om.writeValueAsString(err));
		}
		pw.close();
	}

}
