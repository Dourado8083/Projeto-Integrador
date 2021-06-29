package com.oikos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Community {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long communityId;
	
	@NotNull
	private String communityName;
	
	@NotNull
	private long communityOwner;
	
	private String communityPic;
	
	private String communityBio;

	public long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(long communityId) {
		this.communityId = communityId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public long getCommunityOwner() {
		return communityOwner;
	}

	public void setCommunityOwner(long communityOwner) {
		this.communityOwner = communityOwner;
	}

	public String getCommunityPic() {
		return communityPic;
	}

	public void setCommunityPic(String communityPic) {
		this.communityPic = communityPic;
	}

	public String getCommunityBio() {
		return communityBio;
	}

	public void setCommunityBio(String communityBio) {
		this.communityBio = communityBio;
	}
	
	
}
