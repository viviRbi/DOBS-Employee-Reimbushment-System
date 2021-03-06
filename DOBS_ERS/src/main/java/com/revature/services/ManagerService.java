package com.revature.services;

public interface ManagerService extends UserService{
	public void approveReimushmentRequest(int managerId, int reimbushmentId);
	public void denyReimbushmentRequest(int managerId, int reimbushmentId);
	public void viewAllPendingReimbushment();
	public void viewAllOfImagesOfReceipts();
	public void viewAllEmployees();
}
