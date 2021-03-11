package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbushmentDaoUsedByEmp;
import com.revature.dao.ReimbushmentDaoUsedByEmpImp;
import com.revature.model.Employee;
import com.revature.model.Reimbushment;

public class EmployeeServiceImp implements EmployeeService{
	ReimbushmentDaoUsedByEmp reDao = new ReimbushmentDaoUsedByEmpImp();
	
	@Override
	public boolean submitReimbushmentRequest(int eid, double amount, int type_id) {
		boolean submited = reDao.submitReimbushmentRequest(eid, amount, type_id);
		return submited;
	}

	@Override
	public List<Reimbushment> viewPendingReimbushmentRequestById(int id) {
		
		return null;
	}

	@Override
	public List<Reimbushment> viewResolveReimbushmentRequestById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee viewProfile(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateProfile(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
