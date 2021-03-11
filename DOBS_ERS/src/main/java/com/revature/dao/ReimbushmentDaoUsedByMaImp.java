package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		List<Reimbushment> reList = new ArrayList<>();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbushment WHERE status_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,1);
			// 1: pending, 2:resolved, 3: reject
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//id, amount, submited, resolved, author, resolver, status_id, type_id, receipt
				Reimbushment reI = new Reimbushment();
				reI.setId(rs.getInt("id"));
				reI.setAmount(rs.getDouble("amount"));
				reI.setSubmited(rs.getTimestamp("submited"));
				reI.setResolved(rs.getTimestamp("resolved"));
				reI.setAuthor(rs.getInt("author"));
				reI.setResolver(rs.getInt("resolver"));
				reI.setStatusid(rs.getInt("status_id"));
				reI.setTypeid(rs.getInt("type_id"));
				reI.setReceipt(rs.getBytes("receipt"));
				reList.add(reI);
				System.out.println(reI.toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reList;
	}

	@Override
	public List<Reimbushment> viewAllResolvedReimbushment() {
		List<Reimbushment> reList = new ArrayList<>();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbushment WHERE status_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,2);
			// 1: pending, 2:resolved, 3: reject
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//id, amount, submited, resolved, author, resolver, status_id, type_id, receipt
				Reimbushment reI = new Reimbushment();
				reI.setId(rs.getInt("id"));
				reI.setAmount(rs.getDouble("amount"));
				reI.setSubmited(rs.getTimestamp("submited"));
				reI.setResolved(rs.getTimestamp("resolved"));
				reI.setAuthor(rs.getInt("author"));
				reI.setResolver(rs.getInt("resolver"));
				reI.setStatusid(rs.getInt("status_id"));
				reI.setTypeid(rs.getInt("type_id"));
				reI.setReceipt(rs.getBytes("receipt"));
				reList.add(reI);
				System.out.println(reI.toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reList;
	}

}
