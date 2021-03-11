package com.revature.services;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import com.revature.dao.ReimbushmentDao;
import com.revature.dao.ReimbushmentDaoImp;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImp;
import com.revature.model.Reimbushment;
import com.revature.model.User;

public class UserServiceImp implements UserService{
	
	@Override
	public User logIn(String username, String password, String role) {
		
		
		 String hashed = hashPassword(password);
		 
		 // Check user authentication in Database
		UserDao uDao = new UserDaoImp(); 

		User u = new User();
		u = uDao.checkAuthentication(username, hashed, role );
		return u;
	}

	@Override
	public List<Reimbushment> viewAllReimbushmentRequestById(int eid) {
		ReimbushmentDao rDao = new ReimbushmentDaoImp(); 
		List<Reimbushment> reList = rDao.viewReimbushmentRequestById(eid);
		return reList;
	}
	
	@Override
	public String hashPassword(String password) {
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
		 
		 return hashed;
	}

}
