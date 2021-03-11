package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbushment;

public interface ReimbushmentDaoUsedByEmp extends ReimbushmentDao{
	// Do not pass receipt img here
	public boolean submitReimbushmentRequest(int eid, double amount, int type_id);
	public List<Reimbushment> viewAllResolveReimbushmentRequestById(int id);
	public List<Reimbushment> viewAllPendingReimbushmentRequestById(int id);
}
