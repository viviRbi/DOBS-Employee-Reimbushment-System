package com.revature.services;

public interface EmployeeService extends UserService{
	// Do not pass receipt img here
	public void submitReimbushmentRequest(int id, String amount);
	// Use id to retrieve the img
	public void viewReimbushmentReceipt(int id);
	
	public void viewResolveReimbushmentRequestById(int id);
	public void viewProfile(int id);
	public void updateProfile(int id);
}
