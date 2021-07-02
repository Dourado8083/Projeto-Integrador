package com.oikos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Community {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long communityId;
	
	@NotNull(message = "Insira um nome para a comunidade!")
	@Size(min = 5, max = 30)
	private String communityName;
	
	@NotNull(message = "Insira um dono para a comunidade!")
	private long communityOwner;
	
	private String communityPic;
	
	@Size(max = 255)
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
