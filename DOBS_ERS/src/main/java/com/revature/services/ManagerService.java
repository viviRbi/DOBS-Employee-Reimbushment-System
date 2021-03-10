package com.revature.services;
import java.util.List;

import com.revature.model.Employee;

public interface ManagerService{
	public void approveReimushmentRequest(int managerId, int reimbushmentId);
	public void denyReimbushmentRequest(int managerId, int reimbushmentId);
	public void viewAllPendingReimbushment();
	public void viewAllOfImagesOfReceipts();
	public List<Employee> viewAllEmployees();
}
