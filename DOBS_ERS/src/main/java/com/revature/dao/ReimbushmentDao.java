package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbushment;

public interface ReimbushmentDao {
	
	public List<Reimbushment> viewReimbushmentRequestById(String id);
	
}
