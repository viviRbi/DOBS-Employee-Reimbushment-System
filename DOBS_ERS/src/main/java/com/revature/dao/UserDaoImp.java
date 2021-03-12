package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDaoImp implements UserDao{
	private PreparedStatement pstmt = null;
	private User u = null;
	
	@Override
	public User checkAuthentication(String username, String hashedPassword, String role) {
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM "+role+" WHERE username=? AND password=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, hashedPassword);
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println("3");
			
			if (rs.next()) {
				u = new User();
				u.setId(rs.getInt("user_id"));
				u.setUsername(rs.getString("username"));
				u.setRole(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}
}
