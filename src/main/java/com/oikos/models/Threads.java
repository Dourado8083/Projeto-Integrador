package com.oikos.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Threads {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long threadsId;
	
	private String threadsTitle;
	private long numberOfMessages;
	

	@ManyToOne
	@JoinColumn(name = "threadsCreatorId")
	@JsonIgnoreProperties({"threadsCreated", "profileEmail", "profilePassword", "profileBio", "numberOfFollowers", "businessOwned", "communitiesOwned", "memberOf", "messagesSent", "messagesReceived", "commentsMade"})
	Profile threadsCreator;
	
	@ManyToOne
	@JoinColumn(name = "communityOnId")
	@JsonIgnoreProperties({"communityNumberOfMembers", "communityPic", "communityBio", "communityOwner", "communityMembers", "messages", "threadsList", "communityHeader"})
	Community communityOn;
	
	@OneToMany(mappedBy = "threadsOn")
	List<Message> messageList = new ArrayList<>();
	
	public Threads() {
		
	}
	
	public long getThreadsId() {
		return threadsId;
	}

	public void setThreadsId(long threadsId) {
		this.threadsId = threadsId;
	}

	public String getThreadsTitle() {
		return threadsTitle;
	}

	public void setThreadsTitle(String threadsTitle) {
		this.threadsTitle = threadsTitle;
	}

	public long getNumberOfMessages() {
		return numberOfMessages;
	}

	public void setNumberOfMessages(long numberOfMessages) {
		this.numberOfMessages = numberOfMessages;
	}

	public Profile getThreadsCreator() {
		return threadsCreator;
	}

	public void setThreadsCreator(Profile threadsCreator) {
		this.threadsCreator = threadsCreator;
	}

	public Community getCommunityOn() {
		return communityOn;
	}

	public void setCommunityOn(Community communityOn) {
		this.communityOn = communityOn;
	}
	
	public List<Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
	
}
