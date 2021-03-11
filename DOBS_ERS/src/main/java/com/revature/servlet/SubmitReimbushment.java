package com.revature.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbushment;
import com.revature.model.SendingAlert;
import com.revature.model.User;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImp;

@WebServlet("/submitReimbushment")
//@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
//maxFileSize=1024*1024*10,      // 10MB
//maxRequestSize=1024*1024*50)

public class SubmitReimbushment extends HttpServlet{
	 private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		Reimbushment reI = new Reimbushment();
		
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String data = s.toString();
		reI = om.readValue(data, Reimbushment.class);
		
		EmployeeService es = new EmployeeServiceImp();
		boolean submited = es.submitReimbushmentRequest( reI.getTypeid(), reI.getAmount(), reI.getAuthor());
		
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
			SendingAlert info = new SendingAlert();
			info.setStatusCode(200);
			info.setDescription("Submited");
			info.setMessage("Your reimbushment form had been submited");
			resp.setContentType("application/json");
			pw.println(om.writeValueAsString(info));
		} else {
			SendingAlert info = new SendingAlert();
			info.setStatusCode(500);
			info.setDescription("Fail to save data to database");
			info.setMessage("Sorry, your reimbushment form cannot be submited for now");
			resp.setContentType("application/json");
			pw.println(om.writeValueAsString(info));
		}
		
	}
}
