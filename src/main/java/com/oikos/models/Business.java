package com.oikos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Business {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long businessId;
	
	@NotNull
	private String businessName;
	
	@NotNull
	private String businessAlias;

	@NotNull
	private String businessEmail;

	@NotNull
	private String businessPassword;

	private String businessBio;
	
	private String businessPic;
	
	

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

	public String getBusinessPassword() {
		return businessPassword;
	}

	public void setBusinessPassword(String businessPassword) {
		this.businessPassword = businessPassword;
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
	
}
