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
import com.revature.model.User;

@WebServlet("/submitReimbushment")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)

public class SubmitReimbushment extends HttpServlet{
	 private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		Reimbushment reI = new Reimbushment();
		
		System.out.println("-----------");
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String data = s.toString();
		reI = om.readValue(data, Reimbushment.class);
		
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
		
		PrintWriter pw = resp.getWriter();
		resp.setContentType("application/json");
		pw.println(om.writeValueAsString(reI));
		
	}
}
