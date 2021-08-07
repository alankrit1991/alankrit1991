package com.ecommercewebsite.shopperhub.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommercewebsite.shopperhub.domain.User;
import com.ecommercewebsite.shopperhub.repository.UserRepository;
import com.ecommercewebsite.shopperhub.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<User> getUserById(Integer userId) {
		
		return userRepository.getUserById(userId);
	}

}
