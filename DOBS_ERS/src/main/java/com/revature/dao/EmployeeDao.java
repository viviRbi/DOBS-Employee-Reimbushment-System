package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {
	
		public Employee viewProfile(int id);
		public boolean updateProfile(Employee e);
		
		public List<Employee> viewAllEmployees();
}
