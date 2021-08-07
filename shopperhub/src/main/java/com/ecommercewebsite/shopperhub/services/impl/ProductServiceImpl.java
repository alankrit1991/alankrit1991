package com.ecommercewebsite.shopperhub.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercewebsite.shopperhub.domain.Cart;
import com.ecommercewebsite.shopperhub.domain.product.Order;
import com.ecommercewebsite.shopperhub.domain.product.OrderStatus;
import com.ecommercewebsite.shopperhub.domain.product.Product;
import com.ecommercewebsite.shopperhub.domain.product.ProductReview;
import com.ecommercewebsite.shopperhub.exception.ProductNotAvailableException;
import com.ecommercewebsite.shopperhub.repository.ProductRepository;
import com.ecommercewebsite.shopperhub.services.PaymentService;
import com.ecommercewebsite.shopperhub.services.ProductService;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProductRepository productRepository;
	
	public int saveProduct(Product product, Integer quantity) {
		
		product.setAvailableQuantity(quantity);
		return productRepository.saveProduct(product);
	}
	
	public int updateProduct(Product product) {
		
		return productRepository.updateProduct(product);
	}
	
	public List<Product> getProducts(){
		
		return productRepository.getProducts();
	}
	
	public Optional<Product> getProductById(Integer id) {
		
		return productRepository.getProductById(id);
	}
	
	public List<Product> getAllProductsbyName(String name){
		
		return productRepository.getAllProductsbyName(name);
	}
	
	public List<Product> getProductsByCategory(String category){
		
		return productRepository.getProductsByCategory(category);
		
	}
	
	public int saveProductReview(ProductReview productReview) {
		
		Integer productId = productReview.getProductId();
		List<ProductReview> reviewList = productRepository.getProductReviewByProductId(productId);
		Integer totalRating = 0;
		Integer sumRating = 0;
		for(ProductReview review : reviewList) {
			
			sumRating = sumRating + review.getRating();
			totalRating++;
		}
		Integer newRating = (sumRating+productReview.getRating())/(totalRating+1);
		Optional<Product> productOpt = getProductById(productId);
		if(productOpt.isPresent()) {
			Product product = productOpt.get();
			product.setRating(newRating);
			updateProduct(product);
		}
		return productRepository.saveProductReview(productReview);
	}
	
	public List<ProductReview> getProductReviewByProductId(Integer productId){
		
		return productRepository.getProductReviewByProductId(productId);
	}
	
	public void addProductToCart(Integer cartId, Integer userId, Integer productId) {
		
		productRepository.saveProductToCart(new Cart(cartId, userId, productId));
		
	}
	
	public List<Product> getProductsFromCartByUserId(Integer userId){
		
		List<Product> productList = new ArrayList<>();
		
		List<Cart> listCart = productRepository.getAllCart();
		List<Integer> prodIdList = listCart.stream().filter(c -> c.getUserId().equals(userId)).map(c -> c.getProductId()).collect(Collectors.toList());
		
		prodIdList.stream().forEach(i -> {
			Optional<Product> product = getProductById(i);
			if(product.isPresent()) {
				productList.add(product.get());
			}
		});
		
		return productList;
	}
	
	public void reduceProductQuantityByOne(Integer productId) {
		
		Optional<Product> productResult = getProductById(productId);
		if(productResult.isPresent()) {
			Product product = productResult.get();
			Integer quant = product.getAvailableQuantity();
			product.setAvailableQuantity(quant-1);
			updateProduct(product);
		}
	}
	
	public Boolean placeOrder(Integer userId) throws ProductNotAvailableException {
		
		List<Product> productList = getProductsFromCartByUserId(userId);
		Boolean isPaymentValid = paymentService.checkPaymentValid(userId);
		
		if(isPaymentValid) {
			List<Product> placedProduct = new ArrayList<>();
			for(Product cartItem : productList){
				Optional<Product> productOpt = getProductById(cartItem.getId());
				if(productOpt.isPresent()) {
					Product product = productOpt.get();
					if(product.getAvailableQuantity().equals(0)) {
						throw new ProductNotAvailableException(product.getName());
					} else {
						placedProduct.add(product);
						reduceProductQuantityByOne(product.getId());
						
					}
				}
				
			}
			
			//deleting later because there can be multiple product with same product and user id
			placedProduct.forEach(p -> {
				productRepository.deleteProductFromCart(p.getId(), userId);
			});
			
			Double orderVal = placedProduct.stream().map(p -> p.getPrice()).reduce(0.0, (x,y) -> x+y);
			Order order = new Order();
			order.setProductList(placedProduct);
			order.setPrice(orderVal);
			order.setBuyerId(userId);
			order.setDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
			order.setOrderStatus(OrderStatus.PLACED);
			
			return paymentService.makePayment(order);
			
		} else {
			return false;
		}
	}
}
