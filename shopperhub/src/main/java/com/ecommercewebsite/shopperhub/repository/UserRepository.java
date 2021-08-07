package com.ecommercewebsite.shopperhub.repository;

import java.util.Optional;

import com.ecommercewebsite.shopperhub.domain.User;

public interface UserRepository {

	Optional<User> getUserById(Integer userId);
}
