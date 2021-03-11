package com.revature.services;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.dao.ReimbushmentDaoUsedByMa;
import com.revature.dao.ReimbushmentDaoUsedByMaImp;
import com.revature.model.Employee;
import com.revature.model.Reimbushment;
import com.revature.model.User;

public class ManagerServiceImp extends UserServiceImp implements ManagerService{

	@Override
	public List<Employee> viewAllEmployees() {
		EmployeeDaoImp eDao = new EmployeeDaoImp();
		List<Employee> allEmployees = eDao.viewAllEmployees();
		return allEmployees;
	}

	@Override
	public boolean approveReimushmentRequest(int managerId, int reimbushmentId) {
		ReimbushmentDaoUsedByMa reDao = new ReimbushmentDaoUsedByMaImp();
		boolean approved = reDao.approvedReimbushmentRequestById(null);
		return approved;
	}

	@Override
	public boolean denyReimbushmentRequest(int managerId, int reimbushmentId) {
		ReimbushmentDaoUsedByMa reDao = new ReimbushmentDaoUsedByMaImp();
		boolean reject = reDao.rejectReimbushmentRequests(null);
		return reject;
	}

	@Override
	public List<Reimbushment> viewAllPendingReimbushment() {
		ReimbushmentDaoUsedByMa reDao = new ReimbushmentDaoUsedByMaImp();
		List<Reimbushment> allPending = reDao.viewAllPendingReimbushment();
		return allPending;
	}

	@Override
	public List<Reimbushment> viewAllResolvedReimbushment() {
		ReimbushmentDaoUsedByMa reDao = new ReimbushmentDaoUsedByMaImp();
		List<Reimbushment> allResolved = reDao.viewAllResolvedReimbushment();
		return allResolved;
	}

}
