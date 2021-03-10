package com.revature.services;

import com.revature.dao.ReimbushmentDaoUsedByEmp;
import com.revature.dao.ReimbushmentDaoUsedByEmpImp;

public class EmployeeServiceImp implements EmployeeService{

	@Override
	public boolean submitReimbushmentRequest(int eid, double amount, int type_id) {
		ReimbushmentDaoUsedByEmp reDao = new ReimbushmentDaoUsedByEmpImp();
		boolean submited = reDao.submitReimbushmentRequest(eid, amount, type_id);
		return submited;
	}

	@Override
	public void viewPendingReimbushmentRequestById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewResolveReimbushmentRequestById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewProfile(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProfile(int id) {
		// TODO Auto-generated method stub
		
	}

}
