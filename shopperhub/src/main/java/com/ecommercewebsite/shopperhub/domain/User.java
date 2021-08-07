package com.ecommercewebsite.shopperhub.domain;

public class User {

	private String role;
	
	public User() {
		role = Role.GUEST.getVal();
	}

	public User(String role) {
		super();
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}
