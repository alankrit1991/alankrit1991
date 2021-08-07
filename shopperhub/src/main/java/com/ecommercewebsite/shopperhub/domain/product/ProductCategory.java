package com.ecommercewebsite.shopperhub.domain.product;

import java.util.stream.Stream;

public enum ProductCategory {

	CLOTHES_AND_FASHION("clothes"), ELECTONICS("electronics"), HOME_APPLIANCES("appliance"), BOOKS("books"), TOYS("toys");
	
	private String val;
	
	ProductCategory(String val){
		this.val = val;
	}
	
	public String getVal() {
		return val;
	}



	public static Stream<ProductCategory> stream(){
		
		return Stream.of(ProductCategory.values());
	}
}
