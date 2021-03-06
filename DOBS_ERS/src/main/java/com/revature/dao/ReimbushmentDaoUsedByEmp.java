package com.revature.dao;

public interface ReimbushmentDaoUsedByEmp extends ReimbushmentDao{
	// Do not pass receipt img here
	public void submitReimbushmentRequest(int id, String amount);
}
