package com.ecommercewebsite.shopperhub.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommercewebsite.shopperhub.controller.ProductController;
import com.ecommercewebsite.shopperhub.domain.Role;
import com.ecommercewebsite.shopperhub.domain.User;
import com.ecommercewebsite.shopperhub.domain.product.Product;
import com.ecommercewebsite.shopperhub.domain.product.ProductCategory;
import com.ecommercewebsite.shopperhub.domain.product.ProductReview;
import com.ecommercewebsite.shopperhub.exception.OperationNotSupportedForUser;
import com.ecommercewebsite.shopperhub.exception.ProductNotAvailableException;
import com.ecommercewebsite.shopperhub.services.ProductService;
import com.ecommercewebsite.shopperhub.services.UserService;

@Controller
public class ProductControllerImpl implements ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/products")
	public List<Product> getAllProducts(){
		
		return productService.getProducts();
	}
	
	@GetMapping("/products/{productName}")
	public List<Product> getAllProductsbyName(@PathVariable("productName") String productName){
		
		return productService.getAllProductsbyName(productName);
	}
	
	@GetMapping("/products/category/{categoryName}")
	public List<Product> getProductsByCategory(@PathVariable("categoryName") String category){
		
		return productService.getProductsByCategory(category);
	}
	
	@GetMapping("/productsRecommended")
	public List<Product> getRecommendedProducts(){   // returns top 10 products from each category
		
		List<Product> recommendedList = new ArrayList<>();
		ProductCategory.stream().forEach(category -> recommendedList.addAll(productService.getProductsByCategory(category.getVal())
				.stream().limit(10L).collect(Collectors.toList())));
		return recommendedList;
	}
	
	@PostMapping("/product")
	public void saveProduct(@RequestBody Product product,@RequestParam("quantity") Integer quantity) throws OperationNotSupportedForUser {
		
		Integer userId = product.getSellerId();
		Optional<User> reviewProvider = userService.getUserById(userId);
		if(reviewProvider.isPresent()) {
			if(reviewProvider.get().equals(Role.SELLER.getVal())) {
				
				productService.saveProduct(product,quantity);
			} else {
				throw new OperationNotSupportedForUser("User must be seller to save product");
			}
		} else {
			throw new OperationNotSupportedForUser("User must have account on website to save product");
		}
		
	}
	
	@PostMapping("/productReview/{productId}")
	public void saveProductReview(@PathVariable("productId") Integer productId,@RequestParam("comment") String comment,
			@RequestParam("rating") Integer rating, @RequestParam("reviewId") Integer reviewId, @RequestParam("userId") Integer userId) throws OperationNotSupportedForUser {
		
		Optional<User> reviewProvider = userService.getUserById(userId);
		if(reviewProvider.isPresent()) {
			if(reviewProvider.get().equals(Role.BUYER.getVal())) {
				
				ProductReview productReview = new ProductReview(reviewId, userId, productId, rating, comment);
				productService.saveProductReview(productReview);
			} else {
				throw new OperationNotSupportedForUser("User must be buyer to post review");
			}
		} else {
			throw new OperationNotSupportedForUser("User must have account on website to post review");
		}
		
	}
	
	@PostMapping("/product/addToCart")
	public void addProductToCart(@RequestParam("cartId") Integer cartId, @RequestParam("userId") Integer userId, @RequestParam("productId") Integer productId) throws OperationNotSupportedForUser {
		
		Optional<User> reviewProvider = userService.getUserById(userId);
		if(reviewProvider.isPresent()) {
			if(reviewProvider.get().equals(Role.BUYER.getVal())) {
				
				productService.addProductToCart(cartId, userId, productId);
			} else {
				throw new OperationNotSupportedForUser("User must be buyer to add products to cart");
			}
		} else {
			throw new OperationNotSupportedForUser("User must have account on website to add product to cart");
		}
		
	}
	
	@PostMapping("/product/order/{userId}")
	public Boolean placeOrder(@PathVariable("userId") Integer userId) throws ProductNotAvailableException, OperationNotSupportedForUser {
		
		Optional<User> reviewProvider = userService.getUserById(userId);
		if(reviewProvider.isPresent()) {
			if(reviewProvider.get().equals(Role.BUYER.getVal())) {
				
				return productService.placeOrder(userId);
			} else {
				throw new OperationNotSupportedForUser("User must be buyer to place orders");
			}
		} else {
			throw new OperationNotSupportedForUser("User must have account on website to place orders");
		}
		
	}
}
