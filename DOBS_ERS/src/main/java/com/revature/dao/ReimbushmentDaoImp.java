package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbushment;
import com.revature.util.ConnectionUtil;

public class ReimbushmentDaoImp implements ReimbushmentDao{

	@Override
	public List<Reimbushment> viewReimbushmentRequestById(String id) {
		List<Reimbushment> reList = new ArrayList<>();
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT FROM reimbushment WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

}
