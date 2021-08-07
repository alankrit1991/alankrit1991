package com.ecommercewebsite.shopperhub.exception;

public class ProductNotAvailableException extends Exception {

	public ProductNotAvailableException(String name){
		super("The product "+name+" is currently not available");
	}
}
