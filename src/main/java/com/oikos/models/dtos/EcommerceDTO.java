package com.oikos.models.dtos;

public class EcommerceDTO {

	private long businessOwnerId;
	private long businessOnId;
	private String ecommercePic;

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
	
}
