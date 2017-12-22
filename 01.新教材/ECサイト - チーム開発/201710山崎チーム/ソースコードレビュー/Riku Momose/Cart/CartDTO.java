package com.internousdev.sukesyunshop.dto;

public class CartDTO extends CatalogDTO{

	private int id;
	private String userId;
	private int productId;

	public int getId() {
		return id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setId(int id) {
		this.id = id;
	}

}