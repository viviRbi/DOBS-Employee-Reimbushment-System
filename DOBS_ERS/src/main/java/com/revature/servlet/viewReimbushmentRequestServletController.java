package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


// Contain logic to forward serlet
@WebServlet("/viewReimbushment")
public class viewReimbushmentRequestServletController extends HttpServlet{
	public static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		
		//Get session and check user role
		HttpSession session = req.getSession();
		String role = (String) session.getAttribute("role");
		System.out.println(role);
		
		String status = req.getParameter("status");
		int eid = Integer.parseInt(req.getParameter("eid"));
		String haveId = (eid >0) ? "haveId" : "noId";
		
		RequestDispatcher rd;
		// Switch to servlets in viewReimbushment folder
		switch (status + haveId + role){
		case ("pending" + "haveId" + "employee"):
			// employee
				rd = req.getRequestDispatcher("/viewPendingRequestById");
				rd.forward(req, resp);
			break;
		case ("resolved" + "haveId" + "employee"):
			// employee
			rd = req.getRequestDispatcher("/viewResolvedRequestById");
		rd.forward(req, resp);
			break;
		case ("all" + "haveId"+"employee"):
		case ("all" + "haveId"+"manager"):
			// manager or employee view 1 employee
			rd = req.getRequestDispatcher("/viewAllRequestById");
			rd.forward(req, resp);
			break;
		case ("pending"+"noId" + "manager"):
			// manager all view pending request
			rd = req.getRequestDispatcher("/viewAllPendingRequest");
			rd.forward(req, resp);
			break;
		case ("resolved"+"noId" + "manager"):
			// manager view all resolved request
			rd = req.getRequestDispatcher("/viewAllResolvedRequest");
			rd.forward(req, resp);
			break;
		default:
			// Wrong url or doesn't have permission = homepage
			//resp.sendRedirect("index.html");
		}
	}
}
