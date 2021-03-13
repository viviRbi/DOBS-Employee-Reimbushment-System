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
	public Employee viewProfile(int id) {
		Employee e = new Employee();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM employee WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				e.setId(rs.getInt("user_id"));
				e.setUsername(rs.getString("username"));
				e.setPassword(rs.getString("password"));
				e.setFirstname(rs.getString("firstname"));
				e.setLastname(rs.getString("lastname"));
				e.setEmail(rs.getString("email"));
				e.setAvatar(rs.getString("avatar"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	@Override
	public boolean updateProfile(Employee e) {
		boolean updated = false;
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "UPDATE employee SET username= ?, password =?, firstname=?, lastname=?, email=?, avatar=? WHERE user_id=? AND password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,e.getUsername());
			pstmt.setString(2,e.getNewpassword());
			pstmt.setString(3,e.getFirstname());
			pstmt.setString(4,e.getLastname());
			pstmt.setString(5,e.getEmail());
			pstmt.setString(6,e.getAvatar());
			pstmt.setInt(7, e.getId());
			pstmt.setString(8, e.getPassword());

			if(pstmt.executeUpdate() > 0) 
				updated = true;
			else
				updated = false;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return updated;
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
