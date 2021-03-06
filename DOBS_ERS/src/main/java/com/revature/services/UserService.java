package com.revature.services;

public interface UserService {
	public void logIn (String username, String password, String role);
	public void logOut();
	public void viewPendingReimbushMentRequestsById(int id);
}
