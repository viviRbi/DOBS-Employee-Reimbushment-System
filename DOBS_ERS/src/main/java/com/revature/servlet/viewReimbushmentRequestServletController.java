package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/viewReimbushments")
public class viewReimbushmentRequestServletController extends HttpServlet{
	public static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		
		String status = req.getParameter("status");
		int eid = Integer.parseInt(req.getParameter("eid"));
		String haveId = (eid >0) ? "haveId" : "noId";
		
		switch (status + haveId){
		case ("pending" + "haveId"):
			// employee
			break;
		case ("resolved"+"haveId"):
			// employee
			break;
		case ("all" + "haveId"):
			// manager view 1 employee
			break;
		case ("pending"+"noId"):
			// manager all view pending request
			break;
		case ("resolved"+"noId"):
			// manager view all resolved request
			break;
		}
	}
}
