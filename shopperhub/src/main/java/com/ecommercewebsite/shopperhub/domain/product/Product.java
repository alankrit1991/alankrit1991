package com.ecommercewebsite.shopperhub.domain.product;

public class Product {

	private Integer id;
	private String name;
	private String category;
	private Double price;
	private String description;
	private String status;
	private Integer availableQuantity;
	private Integer sellerId;
	private Integer rating;
	
	public Product(Integer id, String name, String category, Double price, String description, String status,
			Integer availableQuantity, Integer seller, Integer rating) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
		this.status = status;
		this.availableQuantity = availableQuantity;
		this.sellerId = seller;
		this.rating = rating;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer seller) {
		this.sellerId = seller;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
}
