package com.revature.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class Reimbushment {
	private int id;
	private double amount;
	private Timestamp submited;
	private Timestamp resolved;
	private int statusid;
	private int typeid;
	private int author;
	private int resolver;
	private byte[] receipt = null;
	private String authorUserName = null;
	private String resolverUserName = null;
	
	

	@Override
	public String toString() {
		return "Reimbushment [id=" + id + ", amount=" + amount + ", submited=" + submited + ", resolved=" + resolved
				+ ", statusid=" + statusid + ", typeid=" + typeid + ", author=" + author + ", resolver=" + resolver
				+ ", receipt=" + Arrays.toString(receipt) + ", authorUserName=" + authorUserName + ", resolverUserName="
				+ resolverUserName + "]";
	}
	public String getAuthorUserName() {
		return authorUserName;
	}
	public void setAuthorUserName(String authorUserName) {
		this.authorUserName = authorUserName;
	}

	public String getResolverUserName() {
		return resolverUserName;
	}

	public void setResolverUserName(String resolverUserName) {
		this.resolverUserName = resolverUserName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getReceipt() {
		return receipt;
	}
	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}
	public int getResolver() {
		return resolver;
	}
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getSubmited() {
		return submited;
	}
	public void setSubmited(Timestamp timestamp) {
		this.submited = timestamp;
	}
	public Timestamp getResolved() {
		return resolved;
	}
	public void setResolved(Timestamp resolved) {
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
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	
	
}
