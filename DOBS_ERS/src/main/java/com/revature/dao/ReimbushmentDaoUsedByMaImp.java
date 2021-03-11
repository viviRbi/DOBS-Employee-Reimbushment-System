package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbushment;

public class ReimbushmentDaoUsedByMaImp extends ReimbushmentDaoImp implements ReimbushmentDaoUsedByMa{

	@Override
	public List<Reimbushment> viewAllResolveReimbushmentRequestById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbushment> viewAllPendingReimbushmentRequestById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean rejectReimbushmentRequests(int[] id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approvedReimbushmentRequestById(int[] id) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
