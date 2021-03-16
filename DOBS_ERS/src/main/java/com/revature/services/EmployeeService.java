package com.revature.services;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbushment;

public interface EmployeeService{
	
	public List<Reimbushment> viewPendingReimbushmentRequestById(int id);
	
	public List<Reimbushment> viewResolveReimbushmentRequestById(int id);
	public Employee viewProfile(int id);
	public boolean updateProfile(Employee e);
	public boolean submitReimbushmentRequest(int type, double amount, int id, byte[] receiptBytes);
}
