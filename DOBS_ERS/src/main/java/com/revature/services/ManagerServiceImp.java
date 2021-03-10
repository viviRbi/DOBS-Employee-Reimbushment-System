package com.revature.services;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.model.Employee;
import com.revature.model.User;

public class ManagerServiceImp extends UserServiceImp implements ManagerService{

	@Override
	public void approveReimushmentRequest(int managerId, int reimbushmentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void denyReimbushmentRequest(int managerId, int reimbushmentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAllPendingReimbushment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAllOfImagesOfReceipts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> viewAllEmployees() {
		// TODO Auto-generated method stub
		EmployeeDaoImp eDao = new EmployeeDaoImp();
		List<Employee> allEmployees = eDao.viewAllEmployees();
		return allEmployees;
	}

}
