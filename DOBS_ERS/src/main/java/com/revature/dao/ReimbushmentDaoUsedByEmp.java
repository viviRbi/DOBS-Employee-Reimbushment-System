package com.revature.dao;

public interface ReimbushmentDaoUsedByEmp extends ReimbushmentDao{
	// Do not pass receipt img here
	public boolean submitReimbushmentRequest(int eid, double amount, int type_id);
}
