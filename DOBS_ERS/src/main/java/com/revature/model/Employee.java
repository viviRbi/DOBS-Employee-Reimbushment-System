package com.revature.model;

public class Employee extends User {

	private String firstname;
	private String lastname;
	private String email;
	private String avatar = null;
	private String newpassword = null;
	
	public Employee() {
		super();
		this.setRole("employee");
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@Override
	public String toString() {
		return "Employee [ firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", avatar=" + avatar + "]";
	}
	
}
