package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revature.model.Reimbushment;
import com.revature.util.ConnectionUtil;

public class ReimbushmentDaoUsedByEmpImp extends ReimbushmentDaoImp implements ReimbushmentDaoUsedByEmp{

	@Override
	public boolean submitReimbushmentRequest(int eid, double amount, int type_id) {
		boolean submited = false;
		
		// Get date
		//long now = System.currentTimeMillis();
        //Timestamp sqlTimestamp = new Timestamp(now);
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy   HH:mma z EEE");
		java.sql.Timestamp ts = new java.sql.Timestamp(d.getTime());
		try {
			Connection connection = ConnectionUtil.getConnection();
			String sql = "INSERT INTO reimbushment (amount, submited, author, status_id, type_id, receipt) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			
			pstmt.setDouble(1, amount);
			pstmt.setTimestamp(2, ts);
			pstmt.setInt(3, eid);
			pstmt.setInt(4, 1);
			pstmt.setInt(5, type_id);
			pstmt.setBytes(6, null);

			if (pstmt.executeUpdate() != 0)
				submited = true;
			else
				submited = false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return submited;
	}
	
	@Override
	public List<Reimbushment> viewAllResolveReimbushmentRequestById(int id) {
		List<Reimbushment> reList = new ArrayList<>();
		Connection conn = null;
		System.out.println("resolved dao");
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbushment WHERE author = ? and status_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			// 1: pending, 2:resolved, 3: reject
			pstmt.setInt(2,2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//id, amount, submited, resolved, author, resolver, status_id, type_id, receipt
				Reimbushment reI = new Reimbushment();
				reI.setId(rs.getInt("id"));
				reI.setAmount(rs.getDouble("amount"));
				reI.setSubmited(rs.getTimestamp("submited"));
				reI.setResolved(rs.getTimestamp("resolved"));
				reI.setAuthor(id);
				reI.setResolver(rs.getInt("resolver"));
				reI.setStatusid(rs.getInt("status_id"));
				reI.setTypeid(rs.getInt("type_id"));
				reI.setReceipt(rs.getBytes("receipt"));
				reList.add(reI);
				System.out.println(reI.toString());
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return reList;
	}

	@Override
	public List<Reimbushment> viewAllPendingReimbushmentRequestById(int id) {
		List<Reimbushment> reList = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbushment WHERE author = ? and status_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			// 1: pending, 2:resolved, 3: reject
			pstmt.setInt(2,1);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//id, amount, submited, resolved, author, resolver, status_id, type_id, receipt
				Reimbushment reI = new Reimbushment();
				reI.setId(rs.getInt("id"));
				reI.setAmount(rs.getDouble("amount"));
				reI.setSubmited(rs.getTimestamp("submited"));
				reI.setResolved(rs.getTimestamp("resolved"));
				reI.setAuthor(id);
				reI.setResolver(rs.getInt("resolver"));
				reI.setStatusid(rs.getInt("status_id"));
				reI.setTypeid(rs.getInt("type_id"));
				reI.setReceipt(rs.getBytes("receipt"));
				reList.add(reI);
				System.out.println(reI.toString());
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return reList;
	}

}

