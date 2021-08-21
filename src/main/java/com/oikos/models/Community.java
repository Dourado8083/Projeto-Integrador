package com.oikos.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Community {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long communityId;

	@NotNull(message = "Insira um nome para a comunidade!")
	@Size(max = 30)
	private String communityName; 

	private long communityNumberOfMembers;
	
	private String communityPic;

	@Lob
	private String communityBio;
	
	private String communityHeader;

	@ManyToOne
	@JsonIgnoreProperties({"communitiesOwned ", "memberOf", "messagesSent", "messagesReceived", "profileEmail", "profilePassword", "profileBio", "businessOwned", "communitiesOwned", "commentsMade", "threadsCreated"})
	private Profile communityOwner;

	@ManyToMany
	@JoinTable(name = "Community_Profile", joinColumns = @JoinColumn(name = "communityId"), inverseJoinColumns = @JoinColumn(name = "profileId"))
	@JsonIgnoreProperties({"communitiesOwned ", "memberOf", "messagesSent", "messagesReceived", "profileEmail", "profilePassword", "profileBio", "numberOfFollowers", "businessOwned", "communitiesOwned", "commentsMade", "threadsCreated"})
	private List<Profile> communityMembers = new ArrayList<>();

	/*
	 * Relação de mensagens postadas em uma comunidade.
	 */

	@OneToMany(mappedBy = "communityOn")
	@JsonIgnoreProperties({"communityOn", "profileOn"})
	private List<Message> messages = new ArrayList<>();
	
	@OneToMany(mappedBy = "communityOn")
	@JsonIgnoreProperties({"communityOn", "profileEmail", "profilePassword", "profileBio", "numberOfFollowers", "businessOwned", "communitiesOwned", "memberOf", "messagesSent", "messagesReceived", "commentsMade"})
	private List<Thread> threadList = new ArrayList<>();

	public Community() {
		
	}
	
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

	public Profile getCommunityOwner() {
		return communityOwner;
	}

	public void setCommunityOwner(Profile communityOwner) {
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
	
	public String getCommunityHeader() {
		return communityHeader;
	}

	public void setCommunityHeader(String communityHeader) {
		this.communityHeader = communityHeader;
	}
	
	public long getCommunityNumberOfMembers() {
		return communityNumberOfMembers;
	}

	public void setCommunityNumberOfMembers(long communityNumberOfMembers) {
		this.communityNumberOfMembers = communityNumberOfMembers;
	}

	public List<Profile> getCommunityMembers() {
		return communityMembers;
	}

	public void setCommunityMembers(List<Profile> communityMembers) {
		this.communityMembers = communityMembers;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Thread> getThreadList() {
		return threadList;
	}

	public void setThreadList(List<Thread> threadList) {
		this.threadList = threadList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (communityId ^ (communityId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Community other = (Community) obj;
		if (communityId != other.communityId)
			return false;
		return true;
	}


}



