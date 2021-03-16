package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbushment;

public interface ReimbushmentDaoUsedByEmp extends ReimbushmentDao{
	// Do not pass receipt img here
	public List<Reimbushment> viewAllResolveReimbushmentRequestById(int id);
	public List<Reimbushment> viewAllPendingReimbushmentRequestById(int id);
	public boolean submitReimbushmentRequest(int type, double amount, int id, byte[] receiptBytes);
}
