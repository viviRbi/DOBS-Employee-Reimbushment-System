package com.revature.services;

public interface EmployeeService{
	// Do not pass receipt img here
	public void submitReimbushmentRequest(String id, double amount, int type_id);
	// Use id to retrieve the img
	public void viewReimbushmentReceipt(int id);
	
	public void viewResolveReimbushmentRequestById(int id);
	public void viewProfile(int id);
	public void updateProfile(int id);
}
