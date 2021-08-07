package com.ecommercewebsite.shopperhub.repository.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecommercewebsite.shopperhub.domain.Member;
import com.ecommercewebsite.shopperhub.domain.User;
import com.ecommercewebsite.shopperhub.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Optional<User> getUserById(Integer userId) {
		
		return jdbcTemplate.queryForObject("select * from user where userId = ?", new Object[] { userId },
				(rs, rowNum) -> Optional.of(new Member(rs.getInt("userId"), rs.getString("role"), rs.getString("username"),
						rs.getString("password"), rs.getString("email"), rs.getString("address"),
						rs.getInt("mobile"))));
		
	}
}
