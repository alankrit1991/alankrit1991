package com.ecommercewebsite.shopperhub.domain.product;

public class ProductReview {

	private Integer reviewId;
	private Integer userId;
	private Integer productId;
	private Integer rating;
	private String comment;
	
	public ProductReview(Integer reviewId, Integer userId, Integer productId, Integer rating, String comment) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.productId = productId;
		this.rating = rating;
		this.comment = comment;
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
