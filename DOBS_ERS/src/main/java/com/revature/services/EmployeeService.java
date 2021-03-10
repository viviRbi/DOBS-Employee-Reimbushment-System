package com.revature.services;

public interface EmployeeService{
	// Do not pass receipt img here
	public boolean submitReimbushmentRequest(int eid, double amount, int type_id);
	// Use id to retrieve the img
	public void viewPendingReimbushmentRequestById(int id);
	
	public void viewResolveReimbushmentRequestById(int id);
	public void viewProfile(int id);
	public void updateProfile(int id);
}
