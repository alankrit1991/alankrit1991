package com.ecommercewebsite.shopperhub.domain;

public class Cart {

	private Integer cartId;
	private Integer userId;
	private Integer productId;
	
	public Cart(Integer cartId, Integer userId, Integer productId) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.productId = productId;
	}
	
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
}
