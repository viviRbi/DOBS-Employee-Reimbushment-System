package com.revature.dao;
import java.util.List;

import com.revature.model.Reimbushment;
public interface ReimbushmentDaoUsedByMa extends ReimbushmentDao{

	//public void viewReimbushmentReceipt(int id);
	public List<Reimbushment> viewAllResolveReimbushmentRequestById(int id);
	public List<Reimbushment> viewAllPendingReimbushmentRequestById(int id);
	public boolean rejectReimbushmentRequests(int[] id);
	public boolean approvedReimbushmentRequestById(int[] id);
	
}
