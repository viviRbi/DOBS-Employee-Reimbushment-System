package com.revature.dao;

import com.revature.model.User;

public interface UserDao {
	public User checkAuthentication(String username, String hashedPassword, String role);
}
