package com.oikos.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

	private String businessBio;

	private String businessPic;

	private String businessAddress;

	private String businessPhone;

	private String businessHeader;

	private String businessBackground;

	@ManyToOne
	@JsonIgnoreProperties({ "communitiesOwned", "businessOwned", "memberOf", "messagesSent", "messagesReceived" })
	private Profile businessOwner;

	@OneToMany(mappedBy = "businessOn")
	@JsonIgnoreProperties({ "communityOn", "profileOn", "ProfileFrom", "businessOn" })
	private List<Message> businessMessages = new ArrayList<>();

	public Business() {

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

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public Profile getBusinessOwner() {
		return businessOwner;
	}

	public void setBusinessOwner(Profile businessOwner) {
		this.businessOwner = businessOwner;
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

	public List<Message> getBusinessMessages() {
		return businessMessages;
	}

	public void setBusinessMessages(List<Message> businessMessages) {
		this.businessMessages = businessMessages;
	}



}
