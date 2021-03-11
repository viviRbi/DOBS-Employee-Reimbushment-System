package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbushment;
import com.revature.util.ConnectionUtil;

public class ReimbushmentDaoUsedByMaImp extends ReimbushmentDaoImp implements ReimbushmentDaoUsedByMa{

	@Override
	public boolean rejectReimbushmentRequests(int[] id) {
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean approvedReimbushmentRequestById(int[] id) {
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Reimbushment> viewAllPendingReimbushment() {
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbushment> viewAllResolvedReimbushment() {
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
