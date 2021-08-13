package com.oikos.models.dtos;

public class MessageCommunityDTO {
	
	private long profileFromId;
	private long CommunityToId;
	private String messageContent;
	
	public long getProfileFromId() {
		return profileFromId;
	}
	public void setProfileFromId(long profileFromId) {
		this.profileFromId = profileFromId;
	}

	public long getCommunityToId() {
		return CommunityToId;
	}
	public void setCommunityToId(long communityToId) {
		CommunityToId = communityToId;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	

}
