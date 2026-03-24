package com.hibernate.model;

public class Student{
	int sid;
	String name;
	String email;
	Passport ps;
	
	
	
	public Passport getPs() {
		return ps;
	}
	public void setPs(Passport ps) {
		this.ps = ps;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}