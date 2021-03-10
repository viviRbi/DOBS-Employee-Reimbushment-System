package com.revature.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class Reimbushment {
	private int id = 0;
	private double amount = 0;
	private LocalDate submited = LocalDate.now();
	private Date resolved = null;
	private int statusid = 0;
	private int typeid =1;
	private int employeeid = 0;
	private byte[] receipt = null;
	@Override
	public String toString() {
		return "Reimbushment [id=" + id + ", amount=" + amount + ", submited=" + submited + ", resolved=" + resolved
				+ ", statusid=" + statusid + ", typeid=" + typeid + ", employeeid=" + employeeid + "] = receipt = "+ receipt;
	}
	public int getId() {
		return id;
	}
	public byte[] getReceipt() {
		return receipt;
	}
	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getSubmited() {
		return submited;
	}
	public void setSubmited(LocalDate submited) {
		this.submited = submited;
	}
	public Date getResolved() {
		return resolved;
	}
	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}
	public int getStatusid() {
		return statusid;
	}
	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	
	
}
