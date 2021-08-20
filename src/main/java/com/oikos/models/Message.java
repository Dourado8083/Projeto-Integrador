package com.oikos.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long messageId;
	
	private String messageTitle;
	
	@NotNull
	@Lob
	private String messageContent;
	
	private String messagePic;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date data = new java.sql.Date(System.currentTimeMillis());
	
	//feed, profile, community, business
	private String messageType;

	/*
	 * Relação de mensagens postadas em uma comunidade.
	 */
	@ManyToOne
	@JoinColumn(name = "communityOnId")
	@JsonIgnoreProperties({"communityOwner", "communityMembers", "messages"})
	private Community communityOn;

	/*
	 * Relação de mensagens postadas em um negócio
	 */
	@ManyToOne
	@JoinColumn(name = "businessOnId")
	@JsonIgnoreProperties({"communityOwner", "communityMembers", "messages", "businessOwner", "businessMessages"})
	private Business businessOn;
	
	/*
	 * Relação de quem enviou a mensagem.
	 */
	@ManyToOne
	@JoinColumn(name = "profileFromId")
	@JsonIgnoreProperties({ "communitiesOwned", "memberOf", "messagesSent", "messagesReceived", "businessOwned", "commentsMade" })
	private Profile profileFrom;
	
	/*
	 * Relação de mensagens em um perfil
	 */
	@ManyToOne
	@JoinColumn(name = "profileOnId")
	@JsonIgnoreProperties({ "communitiesOwned", "memberOf", "messagesSent", "messagesReceived", "businessOwned", "commentsMade" })
	private Profile profileOn;
	
	
	/*
	 * Relação de comentários em uma mensagem
	 */
	@OneToMany(mappedBy = "messageOn")
	@JsonIgnoreProperties({"messageOn", "businessOwned", "communitiesOwned", "memberOf", "messagesSent", "messagesReceived"})
	private List<Comment> commentList = new ArrayList<>();

	
	public Message() {

	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	
	public String getMessagePic() {
		return messagePic;
	}

	public void setMessagePic(String messagePic) {
		this.messagePic = messagePic;
	}
	
	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Community getCommunityOn() {
		return communityOn;
	}

	public void setCommunityOn(Community communityOn) {
		this.communityOn = communityOn;
	}

	public Profile getProfileOn() {
		return profileOn;
	}

	public void setProfileOn(Profile profileOn) {
		this.profileOn = profileOn;
	}
	
	public Profile getProfileFrom() {
		return profileFrom;
	}

	public void setProfileFrom(Profile profileFrom) {
		this.profileFrom = profileFrom;
	}

	public Business getBusinessOn() {
		return businessOn;
	}

	public void setBusinessOn(Business businessOn) {
		this.businessOn = businessOn;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(messageId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return messageId == other.messageId;
	}



}
