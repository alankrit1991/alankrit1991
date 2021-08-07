package com.ecommercewebsite.shopperhub.domain;

public class Member extends User {

	private Integer userId;
	private String username;
	private String password;
	private String email;
	private String address;
	private int mobile;
	
	public Member(Integer userId, String role, String username, String password, String email, String address, int mobile) {
		super(role);
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.mobile = mobile;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	
}
