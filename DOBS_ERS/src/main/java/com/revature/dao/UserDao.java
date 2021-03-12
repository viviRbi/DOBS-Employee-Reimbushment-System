package com.revature.dao;

import com.revature.model.User;

public interface UserDao {
	public User checkAuthentication(String username, String hashedPassword, String role);
	public String getUserNameById(String columName, String tableName, int user_id);
}
