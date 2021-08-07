package com.ecommercewebsite.shopperhub.services;

import java.util.List;
import java.util.Optional;

import com.ecommercewebsite.shopperhub.domain.product.Product;
import com.ecommercewebsite.shopperhub.domain.product.ProductReview;
import com.ecommercewebsite.shopperhub.exception.ProductNotAvailableException;

public interface ProductService {

	int saveProduct(Product product, Integer quantity);
	
	int updateProduct(Product product);
	
	List<Product> getProducts();
	
	List<Product> getAllProductsbyName(String name);
	
	Optional<Product> getProductById(Integer id);
	
	List<Product> getProductsByCategory(String category);
	
	int saveProductReview(ProductReview productReview);
	
	List<ProductReview> getProductReviewByProductId(Integer productId);
	
	void addProductToCart(Integer cartId, Integer userId, Integer productId);
	
	List<Product> getProductsFromCartByUserId(Integer userId);
	
	void reduceProductQuantityByOne(Integer productId);
	
	Boolean placeOrder(Integer userId) throws ProductNotAvailableException;
}
