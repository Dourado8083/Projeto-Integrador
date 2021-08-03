package com.oikos.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@JsonIgnoreProperties({ "communitiesOwned", "memberOf", "messagesReceived" })
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long profileId;

	@NotNull
	private String profileName;

	@NotNull
	private String profileEmail;

	@NotNull
	private String profilePassword;

	private String profileBio;
	
	private String profilePic;

	private int profileType;
	
	private int numberOfFollowers;
	
	@OneToMany(mappedBy = "communityOwner")
	@JsonIgnoreProperties("communitiesOwned")
	private List<Community> communitiesOwned = new ArrayList<>();

	@ManyToMany(mappedBy = "communityMembers")
	@JsonIgnoreProperties({"communityNumberOfMembers", "communityPic", "communityBio", "communityOwner", "communityMembers", "messages", "numberOfFollowers"})
	private List<Community> memberOf = new ArrayList<>();
	
	@OneToMany(mappedBy = "profileFrom")
	@JsonIgnoreProperties("messagesSent")
	private List<Message> messagesSent = new ArrayList<>();
	
	@OneToMany(mappedBy = "profileOn")
	@JsonIgnoreProperties("messagesReceived")
	private List<Message> messagesReceived = new ArrayList<>();

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
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

	public String getProfileBio() {
		return profileBio;
	}

	public void setProfileBio(String profileBio) {
		this.profileBio = profileBio;
	}
	

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public int getProfileType() {
		return profileType;
	}

	public void setProfileType(int profileType) {
		this.profileType = profileType;
	}

	public int getNumberOfFollowers() {
		return numberOfFollowers;
	}

	public void setNumberOfFollowers(int numberOfFollowers) {
		this.numberOfFollowers = numberOfFollowers;
	}

	public List<Community> getCommunitiesOwned() {
		return communitiesOwned;
	}

	public void setCommunitiesOwned(List<Community> communitiesOwned) {
		this.communitiesOwned = communitiesOwned;
	}

	public List<Community> getMemberOf() {
		return memberOf;
	}

	public void setMemberOf(List<Community> memberOf) {
		this.memberOf = memberOf;
	}

	public List<Message> messagesReceived() {
		return messagesReceived;
	}

	public void setFeed(List<Message> feed) {
		this.messagesReceived = feed;
	}

	@Override
	public int hashCode() {
		return Objects.hash(profileId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		return profileId == other.profileId;
	}


}
