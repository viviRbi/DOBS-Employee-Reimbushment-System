package com.revature.services;

public interface UserService {
	public void logIn (String username, String password);
	public void logOut();
	public void viewPendingReimbushMentRequestsById(int id);
}
