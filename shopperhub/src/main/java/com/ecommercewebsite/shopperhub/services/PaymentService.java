package com.ecommercewebsite.shopperhub.services;

import com.ecommercewebsite.shopperhub.domain.product.Order;

public interface PaymentService {

	public Boolean checkPaymentValid(Integer userId);
	
	public Boolean makePayment(Order order);
}
