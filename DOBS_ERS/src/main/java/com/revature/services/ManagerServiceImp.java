package com.revature.services;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.model.Employee;
import com.revature.model.Reimbushment;
import com.revature.model.User;

public class ManagerServiceImp extends UserServiceImp implements ManagerService{

	@Override
	public List<Employee> viewAllEmployees() {
		// TODO Auto-generated method stub
		EmployeeDaoImp eDao = new EmployeeDaoImp();
		List<Employee> allEmployees = eDao.viewAllEmployees();
		return allEmployees;
	}

	@Override
	public boolean approveReimushmentRequest(int managerId, int reimbushmentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean denyReimbushmentRequest(int managerId, int reimbushmentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Reimbushment> viewAllPendingReimbushment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbushment> viewAllResolvedReimbushment() {
		// TODO Auto-generated method stub
		return null;
	}

}
