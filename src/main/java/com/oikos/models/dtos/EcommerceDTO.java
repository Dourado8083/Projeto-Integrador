package com.oikos.models.dtos;

public class EcommerceDTO {

	private long businessOwnerId;
	private long businessOnId;
	private String ecommerceName;
	private String ecommercePic;
	private String ecommerceHeader;

	public long getBusinessOnId() {
		return businessOnId;
	}

	public void setBusinessOnId(long businessOnId) {
		this.businessOnId = businessOnId;
	}

	public long getBusinessOwnerId() {
		return businessOwnerId;
	}

	public void setBusinessOwnerId(long businessOwnerId) {
		this.businessOwnerId = businessOwnerId;
	}

	public String getEcommercePic() {
		return ecommercePic;
	}

	public void setEcommercePic(String ecommercePic) {
		this.ecommercePic = ecommercePic;
	}

	public String getEcommerceName() {
		return ecommerceName;
	}

	public void setEcommerceName(String ecommerceName) {
		this.ecommerceName = ecommerceName;
	}

	public String getEcommerceHeader() {
		return ecommerceHeader;
	}

	public void setEcommerceHeader(String ecommerceHeader) {
		this.ecommerceHeader = ecommerceHeader;
	}
	
}
