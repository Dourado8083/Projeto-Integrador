package com.oikos.models.dtos;

public class ProfileCommunityDTO {
	
	private String profileEmail;
	private String communityName;
	private String communityPic;
	private String communityBio;
	
	public String getProfileEmail() {
		return profileEmail;
	}
	public void setProfileEmail(String profileEmail) {
		this.profileEmail = profileEmail;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
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
