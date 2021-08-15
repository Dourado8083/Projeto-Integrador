package com.oikos.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long messageId;
	
	private String messageTitle;
	
	@NotNull
	private String messageContent;
	
	private String messagePic;

	/*
	 * Relação de mensagens postadas em uma comunidade.
	 */
	@ManyToOne
	@JoinColumn(name = "communityOnId")
	@JsonIgnoreProperties({ "communityOwner", "communityMembers", "messages"})
	private Community communityOn;

	/*
	 * Relação de quem enviou a mensagem.
	 */
	@ManyToOne
	@JoinColumn(name = "profileFromId")
	@JsonIgnoreProperties({ "communitiesOwned", "memberOf", "messagesSent", "messagesReceived" })
	private Profile profileFrom;
	
	/*
	 * Relação de mensagens em um feed.
	 */
	@ManyToOne
	@JoinColumn(name = "profileOnId")
	@JsonIgnoreProperties({ "communitiesOwned", "memberOf", "messagesSent", "messagesReceived" })
	private Profile profileOn;
	
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
