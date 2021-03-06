package com.revature.dao;

public interface ReimbushmentDaoUsedByMa extends ReimbushmentDao{

	public void viewReimbushmentReceipt(int id);
	public void viewAllResolveReimbushmentRequestById(int id);
	public void viewAllPendingReimbushmentRequestById(int id);
	public void rejectReimbushmentRequests(int[] id);
	public void approvedReimbushmentRequestById(int[] id);
	
}
