package com.revature.services;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.dao.ReimbushmentDaoUsedByEmp;
import com.revature.dao.ReimbushmentDaoUsedByEmpImp;
import com.revature.model.Employee;
import com.revature.model.Reimbushment;

public class EmployeeServiceImp  extends UserServiceImp implements EmployeeService{

	@Override
	public List<Reimbushment> viewPendingReimbushmentRequestById(int id) {
		ReimbushmentDaoUsedByEmp reDao = new ReimbushmentDaoUsedByEmpImp();
		List<Reimbushment> pendingById = reDao.viewAllPendingReimbushmentRequestById(id);
		return pendingById;
	}

	@Override
	public List<Reimbushment> viewResolveReimbushmentRequestById(int id) {
		ReimbushmentDaoUsedByEmp reDao = new ReimbushmentDaoUsedByEmpImp();
		List<Reimbushment> resolvedById = reDao.viewAllResolveReimbushmentRequestById(id);
		return resolvedById;
	}

	@Override
	public Employee viewProfile(int eid) {
		EmployeeDao eDao = new EmployeeDaoImp();
		Employee eProfile = eDao.viewProfile(eid);
		return eProfile;
	}

	@Override
	public boolean updateProfile(Employee e) {
		EmployeeDao eDao = new EmployeeDaoImp();
		boolean updated= eDao.updateProfile(e);
		return updated;
	}

	@Override
	public boolean submitReimbushmentRequest(int type, double amount, int id, byte[] receiptBytes) {
		ReimbushmentDaoUsedByEmp reDao = new ReimbushmentDaoUsedByEmpImp();
		boolean submited = reDao.submitReimbushmentRequest(type, amount, id, receiptBytes);
		return submited;
	}

}
