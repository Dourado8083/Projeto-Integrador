package com.oikos.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long messageId;

	@NotNull
	private String messageContent;

	@NotNull
	private long messageFrom;

	@NotNull
	private int messageType;

	@NotNull
	private int messageWhere;

	private String messageTitle;

	private int messageReactions;
	
	/*
	  Relação de mensagens postadas em uma comunidade.
	 */
	@ManyToOne
	@JoinColumn(name = "communityId")
	private Community communityOn;
	
	/*
	  Relação de mensagens em um feed.
	 */
	@ManyToOne
	@JoinColumn(name = "profileId")
	private Profile profileOn;

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

	public long getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(int messageFrom) {
		this.messageFrom = messageFrom;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public int getMessageReactions() {
		return messageReactions;
	}

	public void setMessageReactions(int messageReactions) {
		this.messageReactions = messageReactions;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public int getMessageWhere() {
		return messageWhere;
	}

	public void setMessageWhere(int messageWhere) {
		this.messageWhere = messageWhere;
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

	public void setMessageFrom(long messageFrom) {
		this.messageFrom = messageFrom;
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