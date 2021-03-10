package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {
	
		public void viewProfile(int id);
		public void updateProfile(int id);
		
		public List<Employee> viewAllEmployees();
}
