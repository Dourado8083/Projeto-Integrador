package com.oikos.models.dtos;

public class ProductDTO {

	private long ecommerceOnId;
	private String productName;
	private String productDescription;
	private String productPic;
	private double productPrice;
	private long productAmount;

	public long getEcommerceOnId() {
		return ecommerceOnId;
	}

	public void setEcommerceOnId(long ecommerceOnId) {
		this.ecommerceOnId = ecommerceOnId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public long getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(long productAmount) {
		this.productAmount = productAmount;
	}



}
