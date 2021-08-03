package com.oikos.models.dtos;

import javax.validation.constraints.NotBlank;

public class ProfileLoginDTO {

	private long profileId;
	
	private String profileName;
	
	private String profilePic;
	
	@NotBlank(message = "Entre com um login!")
	private String profileEmail;

	@NotBlank(message = "Entre com uma senha!")
	private String profilePassword;

	private String profileToken;

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	
	public String getProfileEmail() {
		return profileEmail;
	}

	public void setProfileEmail(String profileEmail) {
		this.profileEmail = profileEmail;
	}

	public String getProfilePassword() {
		return profilePassword;
	}

	public void setProfilePassword(String profilePassword) {
		this.profilePassword = profilePassword;
	}

	public String getProfileToken() {
		return profileToken;
	}

	public void setProfileToken(String profileToken) {
		this.profileToken = profileToken;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

}
