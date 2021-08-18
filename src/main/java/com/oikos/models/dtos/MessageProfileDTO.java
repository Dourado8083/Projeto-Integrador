package com.oikos.models.dtos;

public class MessageProfileDTO {
	
	private long profileFromId;
	private long profileToId;
	private String messageContent;
	private String messagePic;
	private String messageType;
	
	public long getProfileFromId() {
		return profileFromId;
	}
	public void setProfileFromId(long profileFromId) {
		this.profileFromId = profileFromId;
	}
	public long getProfileToId() {
		return profileToId;
	}
	public void setProfileToId(long profileToId) {
		this.profileToId = profileToId;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
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
	
	
	
}
