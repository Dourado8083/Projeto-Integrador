package com.oikos.models.dtos;

public class ProfileBusinessDTO {

	long profileId;
	long businessId;
	private String businessName;
	private String businessAlias;
	private String businessEmail;
	private String businessBio;
	private String businessPic;
	private String businessAddress;
	private String businessPhone;
	private String businessHeader;
	private String businessBackground;

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessAlias() {
		return businessAlias;
	}

	public void setBusinessAlias(String businessAlias) {
		this.businessAlias = businessAlias;
	}

	public String getBusinessEmail() {
		return businessEmail;
	}

	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}

	public String getBusinessBio() {
		return businessBio;
	}

	public void setBusinessBio(String businessBio) {
		this.businessBio = businessBio;
	}

	public String getBusinessPic() {
		return businessPic;
	}

	public void setBusinessPic(String businessPic) {
		this.businessPic = businessPic;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getBusinessHeader() {
		return businessHeader;
	}

	public void setBusinessHeader(String businessHeader) {
		this.businessHeader = businessHeader;
	}

	public String getBusinessBackground() {
		return businessBackground;
	}

	public void setBusinessBackground(String businessBackground) {
		this.businessBackground = businessBackground;
	}

}
