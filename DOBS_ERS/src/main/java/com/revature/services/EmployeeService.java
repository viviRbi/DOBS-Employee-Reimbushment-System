package com.revature.services;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbushment;

public interface EmployeeService{
	
	public boolean submitReimbushmentRequest(int eid, double amount, int type_id);
	public List<Reimbushment> viewPendingReimbushmentRequestById(int id);
	
	public List<Reimbushment> viewResolveReimbushmentRequestById(int id);
	public Employee viewProfile(int id);
	public Employee updateProfile(int id);
}
