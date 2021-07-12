package com.oikos.models.dtos;

import javax.validation.constraints.NotBlank;

public class ProfileLoginDTO {

	@NotBlank(message = "Entre com um login!")
	private String profileEmail;

	@NotBlank(message = "Entre com uma senha!")
	private String profilePassword;

	private String profileToken;

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

}
