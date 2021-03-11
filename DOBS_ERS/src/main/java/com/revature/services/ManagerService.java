package com.revature.services;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbushment;

public interface ManagerService{
	public boolean approveReimushmentRequest(int managerId, int reimbushmentId);
	public boolean denyReimbushmentRequest(int managerId, int reimbushmentId);
	public List<Reimbushment> viewAllPendingReimbushment();
	public List<Reimbushment> viewAllResolvedReimbushment();
	//public void viewAllOfImagesOfReceipts();
	public List<Employee> viewAllEmployees();
}
