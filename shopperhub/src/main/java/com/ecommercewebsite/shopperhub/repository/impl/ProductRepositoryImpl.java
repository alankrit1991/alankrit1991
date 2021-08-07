package com.ecommercewebsite.shopperhub.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommercewebsite.shopperhub.domain.Cart;
import com.ecommercewebsite.shopperhub.domain.product.Product;
import com.ecommercewebsite.shopperhub.domain.product.ProductReview;
import com.ecommercewebsite.shopperhub.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int saveProduct(Product product) {

		return jdbcTemplate.update(
				"insert into product (id, name, category, price, description, status, availableQuantity, sellerId, rating) values(?,?,?,?,?,?,?,?,?)",
				product.getId(), product.getName(), product.getCategory(), product.getPrice(), product.getDescription(),
				product.getStatus(), product.getAvailableQuantity(), product.getSellerId(), product.getRating());
	}

	public int updateProduct(Product product) {

		return jdbcTemplate.update(
				"update product set name=?, category=?, price=?, description=?, status=?, availableQuantity=?, sellerId=?, rating=? where id=? ",
				product.getName(), product.getCategory(), product.getPrice(), product.getDescription(), product.getStatus(),
				product.getAvailableQuantity(), product.getSellerId(), product.getRating(), product.getId());
	}

	public List<Product> getProducts() {
		return jdbcTemplate.query("select * from product",
				(rs, rowNum) -> new Product(rs.getInt("id"), rs.getString("name"), rs.getString("category"),
						rs.getDouble("price"), rs.getString("description"), rs.getString("status"),
						rs.getInt("availableQuantity"), rs.getInt("sellerId"), rs.getInt("rating")));
	}

	public Optional<Product> getProductById(Integer id) {

		return jdbcTemplate.queryForObject("select * from product where id = ?", new Object[] { id },
				(rs, rowNum) -> Optional.of(new Product(rs.getInt("id"), rs.getString("name"), rs.getString("category"),
						rs.getDouble("price"), rs.getString("description"), rs.getString("status"),
						rs.getInt("availableQuantity"), rs.getInt("sellerId"), rs.getInt("rating"))));
	}

	public List<Product> getAllProductsbyName(String name) {

		return jdbcTemplate.query("select * from product where name like ?  ", new Object[] { "%" + name + "%" },
				(rs, rowNum) -> new Product(rs.getInt("id"), rs.getString("name"), rs.getString("category"),
						rs.getDouble("price"), rs.getString("description"), rs.getString("status"),
						rs.getInt("availableQuantity"), rs.getInt("sellerId"), rs.getInt("rating")));
	}

	public List<Product> getProductsByCategory(String category) {

		return jdbcTemplate.query("select * from product where category = ? order by rating desc ",
				new Object[] { category },
				(rs, rowNum) -> new Product(rs.getInt("id"), rs.getString("name"), rs.getString("category"),
						rs.getDouble("price"), rs.getString("description"), rs.getString("status"),
						rs.getInt("availableQuantity"), rs.getInt("sellerId"), rs.getInt("rating")));
	}

	public int saveProductReview(ProductReview productReview) {

		return jdbcTemplate.update("insert into product_review (productId, rating, comment) values(?,?,?)",
				productReview.getProductId(), productReview.getRating(), productReview.getComment());
	}

	public List<ProductReview> getProductReviewByProductId(Integer productId) {

		return jdbcTemplate.query("select * from product_review where productId = ?", new Object[] { productId },
				(rs, rowNum) -> new ProductReview(rs.getInt("reviewId"), rs.getInt("userId"), rs.getInt("productId"),
						rs.getInt("rating"), rs.getString("comment")));
	}

	public int saveProductToCart(Cart cart) {

		return jdbcTemplate.update("insert into cart (cartId, userId, productId) values(?,?,?)", cart.getCartId(),
				cart.getUserId(), cart.getProductId());
	}

	public List<Cart> getAllCart() {
		return jdbcTemplate.query("select * from cart",
				(rs, rowNum) -> new Cart(rs.getInt("cartId"), rs.getInt("userId"), rs.getInt("productId")));
	}
	
	public int deleteProductFromCart(Integer productId, Integer userId) {
		
		return jdbcTemplate.update(
                "delete from cart where productId = ? and userId = ?",
                productId, userId);
	}
}
