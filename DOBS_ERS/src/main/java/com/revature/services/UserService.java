package com.revature.services;

import com.revature.model.User;

import java.util.List;

import com.revature.model.Reimbushment;

public interface UserService {
	public User logIn (String username, String password, String role);
	public List<Reimbushment> viewAllReimbushmentRequestById(int id);
	public String hashPassword(String password); 
}
