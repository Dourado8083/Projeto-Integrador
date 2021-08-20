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
public class Thread {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long threadId;
	
	private String threadTitle;
	private long numberOfMessages;
	

	@ManyToOne
	@JoinColumn(name = "threadCreatorId")
	@JsonIgnoreProperties({"profileEmail", "profilePassword", "profileBio", "numberOfFollowers", "businessOwned", "communitiesOwned", "memberOf", "messagesSent", "messagesReceived", "commentsMade"})
	Profile threadCreator;
	
	@ManyToOne
	@JoinColumn(name = "communityOnId")
	@JsonIgnoreProperties({"communityNumberOfMembers", "communityPic", "communityBio", "communityOwner", "communityMembers", "messages", "threadList"})
	Community communityOn;
	
	@OneToMany(mappedBy = "threadOn")
	List<Message> messageList = new ArrayList<>();
	
	public long getThreadId() {
		return threadId;
	}

	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}

	public String getThreadTitle() {
		return threadTitle;
	}

	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}

	public long getNumberOfMessages() {
		return numberOfMessages;
	}

	public void setNumberOfMessages(long numberOfMessages) {
		this.numberOfMessages = numberOfMessages;
	}

	public Profile getThreadCreator() {
		return threadCreator;
	}

	public void setThreadCreator(Profile threadCreator) {
		this.threadCreator = threadCreator;
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
