package com.ecommercewebsite.shopperhub.repository;

import java.util.List;
import java.util.Optional;

import com.ecommercewebsite.shopperhub.domain.Cart;
import com.ecommercewebsite.shopperhub.domain.product.Product;
import com.ecommercewebsite.shopperhub.domain.product.ProductReview;

public interface ProductRepository {

	int saveProduct(Product product);
	
	int updateProduct(Product product);
	
	List<Product> getProducts();
	
	Optional<Product> getProductById(Integer id);
	
	List<Product> getAllProductsbyName(String name);
	
	List<Product> getProductsByCategory(String category);	//will return ordered product by rating
	
	int saveProductReview(ProductReview productReview);
	
	List<ProductReview> getProductReviewByProductId(Integer productId);
	
	int saveProductToCart(Cart cart);
	
	List<Cart> getAllCart();
	
	int deleteProductFromCart(Integer productId, Integer userId);
}
