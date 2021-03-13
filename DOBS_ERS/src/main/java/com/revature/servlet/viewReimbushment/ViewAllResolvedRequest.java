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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbushment;
import com.revature.model.SendingAlert;
import com.revature.services.ManagerService;
import com.revature.services.ManagerServiceImp;

@WebServlet("/viewAllResolvedRequest")
public class ViewAllResolvedRequest extends HttpServlet{
	public static final long serialVersionUID = 1L;
	// Convert obj to json and vice versa
		ObjectMapper om = new ObjectMapper();
		private static Logger log = Logger.getLogger(ViewAllResolvedRequest.class);
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
			log.info("manager is viewing all resolved requests");
			
			ManagerService m = new ManagerServiceImp();	
			List<Reimbushment> allPending = m.viewAllResolvedReimbushment();
			
			log.debug(allPending);
			// Prepare to write response
			PrintWriter pw = resp.getWriter();
			
			// If able to get at least 1 employee
			if (allPending != null) {
				resp.setContentType("application/json");
				String jsonReIs = om.writeValueAsString(allPending);
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
