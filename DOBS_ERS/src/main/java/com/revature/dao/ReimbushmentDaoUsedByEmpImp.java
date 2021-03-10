package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

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

}
