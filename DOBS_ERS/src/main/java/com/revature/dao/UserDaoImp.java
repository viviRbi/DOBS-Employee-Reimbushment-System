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

	@Override
	public String getUserNameById(String columName, String tableName, int user_id) {
		String username = null;
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT "+columName+" FROM "+tableName+" WHERE user_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				username = rs.getString("username");
				System.out.println(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return username;
	}
}
