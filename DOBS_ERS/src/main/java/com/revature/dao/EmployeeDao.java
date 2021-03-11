package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {
	
		public Employee viewProfile(int id);
		public Employee updateProfile(int id);
		
		public List<Employee> viewAllEmployees();
}
