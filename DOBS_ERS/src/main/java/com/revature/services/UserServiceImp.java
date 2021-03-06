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
		UserDao uDao = new UserDaoImp(); 
		// Generate salt, which will stored at a plain text with the password so the hask know what to skip
		
	     MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_16BE));
		 BigInteger bi = new BigInteger(1, hashedPassword );
		 String hashed = bi.toString(16);

		uDao.checkAuthentication(username, hashed );
		User u = new User();
		u.setUsername(username);
		u.setPassword(hashed);
		return u;
	}

	@Override
	public void logOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewPendingReimbushMentRequestsById(int id) {
		// TODO Auto-generated method stub
		
	}

}
