package com.revature.services;

import com.revature.model.User;

public interface UserService {
	public User logIn (String username, String password, String role);
	public void logOut();
	public void viewPendingReimbushMentRequestsById(int id);
}
