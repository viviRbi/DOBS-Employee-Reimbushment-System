package com.revature.dao;
import java.util.List;

import com.revature.model.Reimbushment;
public interface ReimbushmentDaoUsedByMa extends ReimbushmentDao{
	
	public List<Reimbushment> viewAllPendingReimbushment();
	public List<Reimbushment> viewAllResolvedReimbushment();
	//public void viewReimbushmentReceipt(int id);
	public boolean rejectReimbushmentRequests(int managerId, Integer[] reimbushmentId);
	public boolean approvedReimbushmentRequestById(int managerId, Integer[] reimbushmentId);
	
}
