package com.revature.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbushment;
import com.revature.model.SendingAlert;
import com.revature.model.User;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImp;
import com.revature.servlet.viewReimbushment.ViewAllPendingRequest;

@WebServlet("/submitReimbushment")
//@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
//maxFileSize=1024*1024*10,      // 10MB
//maxRequestSize=1024*1024*50)

public class SubmitReimbushment extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	 private static Logger log = Logger.getLogger(SubmitReimbushment.class);
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		log.info("Employee submiting reimbushment");
		
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		/*
		Reimbushment reI = new Reimbushment();
		
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}*/
		HttpSession session = req.getSession();
		int id = (int) session.getAttribute("id");
		System.out.println("User id: "+ id);
		
		Enumeration<String> requestParameters = req.getParameterNames();
        while (requestParameters.hasMoreElements()) {
            String paramName = (String) requestParameters.nextElement();
            System.out.println("Request Paramter Name: " + paramName 
                            + ", Value - " + req.getParameter(paramName));
        }
		
		String type = req.getParameter("type");
		System.out.println("User type--: "+ type);
		/*double amount = Double.parseDouble(req.getParameter("amount"));
		InputStream receiptStream = null;
		try {
			Part receiptPart = req.getPart("receipt");
			receiptStream = receiptPart.getInputStream();
		} catch(IOException | ServletException e) {
			e.printStackTrace();
		}
		System.out.println(receiptStream);
		
		byte[] receiptBytes = receiptStream.readAllBytes();
		System.out.println(receiptBytes);*/
		
		//log.debug("Info submited: "+data);
		//reI = om.readValue(data, Reimbushment.class);
		
		EmployeeService es = new EmployeeServiceImp();
		//boolean submited = es.submitReimbushmentRequest( type, amount, id);
		boolean submited = true;
		// Testing image file
		/*Part receiptPath = req.getPart("receipt");
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		
		inputStream = receiptPath.getInputStream();
		outputStream = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[1024];
		
		while (inputStream.read(buffer) != -1) {
			outputStream.write(buffer);
		}
		reI.setReceipt(outputStream.toByteArray());
		
		System.out.println(reI.getReceipt());
		
		if(inputStream != null) inputStream.close();
		if(outputStream != null) outputStream.close();*/
		
		if (submited == true) {
			log.debug("Successfilly submit");
			SendingAlert info = new SendingAlert();
			info.setStatusCode(200);
			info.setDescription("Submited");
			info.setMessage("Your reimbushment form had been submited");
			resp.setContentType("application/json");
			pw.println(om.writeValueAsString(info));
		} else {
			log.debug("Fail to submit request");
			SendingAlert info = new SendingAlert();
			info.setStatusCode(500);
			info.setDescription("Fail to save data to database");
			info.setMessage("Sorry, your reimbushment form cannot be submited for now");
			resp.setContentType("application/json");
			pw.println(om.writeValueAsString(info));
		}
		
	}
}
