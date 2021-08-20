package com.oikos.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long commentId;
	
	@Lob
	private String commentContent;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date data = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JoinColumn(name = "profileFromId")
	@JsonIgnoreProperties({"businessOwned", "communitiesOwned", "memberOf", "messagesSent", "messagesReceived", "commentsMade"})
	private Profile profileFrom;
	
	@ManyToOne
	@JoinColumn(name = "messageOnId")
	@JsonIgnoreProperties({"communityOn", "businessOn", "profileFrom", "profileOn", "commentList"})
	private Message messageOn;

	public Comment() {

	}
	
	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Profile getProfileFrom() {
		return profileFrom;
	}

	public void setProfileFrom(Profile profileFrom) {
		this.profileFrom = profileFrom;
	}

	public Message getMessageOn() {
		return messageOn;
	}

	public void setMessageOn(Message messageOn) {
		this.messageOn = messageOn;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	

}
