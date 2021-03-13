package com.revature.servlet.viewReimbushment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.services.UserService;
import com.revature.services.UserServiceImp;
import com.revature.servlet.logInOut.LogOutServlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbushment;
import com.revature.model.SendingAlert;

@WebServlet("/viewAllRequestById")
public class ViewAllRequestById extends HttpServlet{
	public static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ViewAllRequestById.class);
	// Convert obj to json and vice versa
	ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		log.info("An emp or manager is viewing all employee requests");
		int eid = Integer.parseInt(req.getParameter("eid"));

		UserService u = new UserServiceImp();	
		List<Reimbushment> allRe = u.viewAllReimbushmentRequestById(eid) ;
		
		// Prepare to write response
		PrintWriter pw = resp.getWriter();
		
		// If able to get at least 1 employee
		if (allRe != null) {
			log.debug("All employee request is: " + allRe);
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
