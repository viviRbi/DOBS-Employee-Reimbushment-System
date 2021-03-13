package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.services.ManagerService;
import com.revature.services.ManagerServiceImp;
import com.revature.services.UserServiceImp;
import com.revature.servlet.viewReimbushment.ViewAllPendingRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.SendingAlert;

@WebServlet("/allEmployees")
public class viewAllEmployeesServlet extends HttpServlet{
	public static final long serialVersionUID = 1L;
	// Convert obj to json and vice versa
	ObjectMapper om = new ObjectMapper();
	private static Logger log = Logger.getLogger(viewAllEmployeesServlet.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		log.info("Viewing all emp profiles");
		ManagerService m = new ManagerServiceImp();	
		List<Employee> allEmployees = m.viewAllEmployees();
		log.debug("All emp profiles "+ allEmployees);
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
