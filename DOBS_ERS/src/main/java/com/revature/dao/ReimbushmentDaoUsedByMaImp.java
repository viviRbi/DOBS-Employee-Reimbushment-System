package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbushment;
import com.revature.util.ConnectionUtil;

public class ReimbushmentDaoUsedByMaImp extends ReimbushmentDaoImp implements ReimbushmentDaoUsedByMa{

	@Override
	public boolean rejectReimbushmentRequests(int managerId, Integer[] reimbushmentId) {
		boolean rejected = false;
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			// Get date
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy   HH:mma z EEE");
			java.sql.Timestamp ts = new java.sql.Timestamp(d.getTime());
						
			// Divide sql stament to loop all array of id to ?,?,?)
			String sql = "UPDATE reimbushment SET status_id=?, resolver=?, resolved=? WHERE id IN(";
			
			for (int i =0; i<reimbushmentId.length; i++) {
				sql += "?,";
			};
			// Remove last comma and add ")" at the end of query
			sql = sql.substring(0,sql.lastIndexOf(",")) + ")";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 3);
			pstmt.setInt(2, managerId);
			pstmt.setTimestamp(3, ts);
			
			// for loop for Prepare Statement. Loop though each id
			for (int i =0; i<reimbushmentId.length; i++) {
				pstmt.setInt(4+i, reimbushmentId[i]);
			}
			
			if(pstmt.executeUpdate() != 0)
				rejected = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} 
		return rejected;
	}

	@Override
	public boolean approvedReimbushmentRequestById(int managerId, Integer[] reimbushmentId) {
		boolean approved = false;
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			// Get date
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy   HH:mma z EEE");
			java.sql.Timestamp ts = new java.sql.Timestamp(d.getTime());
						
			// Divide sql stament to loop all array of id to ?,?,?)
			String sql = "UPDATE reimbushment SET status_id=?, resolver=?, resolved=? WHERE id IN(";
			
			for (int i =0; i<reimbushmentId.length; i++) {
				sql += "?,";
			};
			// Remove last comma and add ")" at the end of query
			sql = sql.substring(0,sql.lastIndexOf(",")) + ")";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 2);
			pstmt.setInt(2, managerId);
			pstmt.setTimestamp(3, ts);
			
			// for loop for Prepare Statement. Loop though each id
			for (int i =0; i<reimbushmentId.length; i++) {
				pstmt.setInt(4+i, reimbushmentId[i]);
			}
			
			if(pstmt.executeUpdate() != 0)
				approved = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return approved;

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
