package com.revature.servlet.approveRejectReimbushment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/manager/approveReimbushment")
public class ApproveReimbushmentServlet extends HttpServlet{
	public static final long serialVersionUID = 1L;
	// Convert obj to json and vice versa
	ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		System.out.println("Hello");
		int[] id = {1,2,3}; 
		System.out.println(id);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		System.out.println("Hello");
		
	}
}
