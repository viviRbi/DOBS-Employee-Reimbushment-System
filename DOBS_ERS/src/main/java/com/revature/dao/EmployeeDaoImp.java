package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImp extends UserDaoImp implements EmployeeDao{

	@Override
	public void viewProfile(int id) {
		System.out.println("as");
		
	}

	@Override
	public void updateProfile(int id) {
		System.out.println("as");
		
	}

	@Override
	public List<Employee> viewAllEmployees() {
		List<Employee> allEmployees = new ArrayList<>();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM employee";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("user_id"));
				e.setUsername(rs.getString("username"));
				e.setPassword(rs.getString("password"));
				e.setFirstname(rs.getString("firstname"));
				e.setLastname(rs.getString("lastname"));
				e.setEmail(rs.getString("email"));
				e.setAvatar(rs.getString("avatar"));
				allEmployees.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allEmployees;
	}

}
