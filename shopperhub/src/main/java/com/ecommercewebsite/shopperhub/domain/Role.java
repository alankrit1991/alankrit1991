package com.ecommercewebsite.shopperhub.domain;

public enum Role {

	BUYER("buyer"), SELLER("seller"), GUEST("guest");
	
	private String val;
	
	Role(String val){
		this.val = val;
	}

	public String getVal() {
		return val;
	}
	
}
