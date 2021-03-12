package com.revature.servlet.approveRejectReimbushment;

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
import com.revature.model.SendingAlert;
import com.revature.model.User;
import com.revature.services.ManagerService;
import com.revature.services.ManagerServiceImp;

@WebServlet("/manager/approveReimbushment")
public class ApproveReimbushmentServlet extends HttpServlet{
	public static final long serialVersionUID = 1L;
	// Convert obj to json and vice versa
	ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		
		//Get session and check user role
		HttpSession session = req.getSession();
		String role = (String) session.getAttribute("role");
		int id = (int) session.getAttribute("id");
		
		PrintWriter pw = resp.getWriter();
		
		SendingAlert alert = new SendingAlert();
		
		if (role.contentEquals("manager")) {
			BufferedReader reader = req.getReader();
			StringBuilder s = new StringBuilder();
			
			String line = reader.readLine();
			while (line != null) {
				s.append(line);
				line = reader.readLine();
			}
			String body = s.toString();

			ObjectMapper om = new ObjectMapper();
	
			// get all checked id
			Integer[] approvedIds = om.readValue(body, Integer[].class);
			ManagerService m = new ManagerServiceImp();
			boolean approved = m.approveReimushmentRequest(id, approvedIds);
			
			if (approved) {
				alert.setStatusCode(200);
				alert.setDescription("Approve");
				alert.setMessage("Sucessfully approve the requests");
				resp.setContentType("application/json");
				pw.println(om.writeValueAsString(alert));
	
			} else {
				alert.setStatusCode(204);
				alert.setDescription("No Content Found");
				alert.setMessage("Fail to approve the requests");
				resp.setContentType("application/json");
				pw.println(om.writeValueAsString(alert));
			}

		} else {
			alert.setStatusCode(204);
			alert.setDescription("No Content Found");
			alert.setMessage("Please log in as a manager");
			resp.setContentType("application/json");
			pw.println(om.writeValueAsString(alert));
		}
		
				
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		resp.sendRedirect("index.html");
	}
}
