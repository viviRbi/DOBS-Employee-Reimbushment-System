package com.revature.services;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImp;
import com.revature.model.User;

public class UserServiceImp implements UserService{
	
	@Override
	public User logIn(String username, String password, String role) {

		// Hash password to MD5 byte
	     MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_16BE));
		
		// Convert hashed password to string
		 BigInteger bi = new BigInteger(1, hashedPassword );
		 String hashed = bi.toString(16);
		 System.out.println(hashed);
		 
		 // Check user authentication in Database
		UserDao uDao = new UserDaoImp(); 

		User u = new User();
		u = uDao.checkAuthentication(username, hashed, role );
		return u;
	}

	@Override
	public void viewPendingReimbushMentRequestsById(int id) {
		// TODO Auto-generated method stub
		
	}

}
