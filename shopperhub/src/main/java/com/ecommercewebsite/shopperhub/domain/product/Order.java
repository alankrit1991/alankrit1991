package com.ecommercewebsite.shopperhub.domain.product;

import java.util.Date;
import java.util.List;

public class Order {

	private Integer id;
	private Integer buyerId;
	private List<Product> productList;
	private Double price;
	private Date date;
	private OrderStatus orderStatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyer) {
		this.buyerId = buyer;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> listProducts) {
		this.productList = listProducts;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
