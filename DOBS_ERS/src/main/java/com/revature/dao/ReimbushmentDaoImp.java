package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbushment;
import com.revature.util.ConnectionUtil;

public class ReimbushmentDaoImp implements ReimbushmentDao{

	@Override
	public List<Reimbushment> viewReimbushmentRequestById(int eid) {
		List<Reimbushment> reList = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbushment WHERE author = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,eid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//id, amount, submited, resolved, author, resolver, status_id, type_id, receipt
				Reimbushment reI = new Reimbushment();
				reI.setId(rs.getInt("id"));
				reI.setAmount(rs.getDouble("amount"));
				reI.setSubmited(rs.getTimestamp("submited"));
				reI.setResolved(rs.getTimestamp("resolved"));
				reI.setAuthor(eid);
				reI.setResolver(rs.getInt("resolver"));
				reI.setStatusid(rs.getInt("status_id"));
				reI.setTypeid(rs.getInt("type_id"));
				reI.setReceipt(rs.getBytes("receipt"));
				reList.add(reI);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return reList;
	}
}