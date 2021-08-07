package com.ecommercewebsite.shopperhub.services;

import java.util.Optional;

import com.ecommercewebsite.shopperhub.domain.User;

public interface UserService {

	Optional<User> getUserById(Integer userId);
}
