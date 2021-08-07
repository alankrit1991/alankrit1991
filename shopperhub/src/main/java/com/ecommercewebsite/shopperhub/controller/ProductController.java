package com.ecommercewebsite.shopperhub.controller;

import java.util.List;

import com.ecommercewebsite.shopperhub.domain.product.Product;
import com.ecommercewebsite.shopperhub.exception.OperationNotSupportedForUser;
import com.ecommercewebsite.shopperhub.exception.ProductNotAvailableException;

public interface ProductController {
	
	List<Product> getAllProducts();
	
	List<Product> getAllProductsbyName(String name);

	List<Product> getRecommendedProducts();
	
	List<Product> getProductsByCategory(String category);
	
	void saveProduct(Product product,Integer quantity) throws OperationNotSupportedForUser;
	
	void saveProductReview(Integer productId, String comment, Integer rating, Integer reviewId, Integer userId) throws OperationNotSupportedForUser;
	
	void addProductToCart(Integer cartId, Integer userId, Integer productId) throws OperationNotSupportedForUser;
	
	Boolean placeOrder(Integer userId) throws ProductNotAvailableException, OperationNotSupportedForUser;
}
