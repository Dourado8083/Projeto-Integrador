package com.oikos.models.dtos;

public class CommentDTO {
	
	private long profileFromId;
	private long messageOnId;
	private String commentContent;
	
	public long getProfileFromId() {
		return profileFromId;
	}
	
	public void setProfileFromId(long profileFromId) {
		this.profileFromId = profileFromId;
	}
	
	public long getMessageOnId() {
		return messageOnId;
	}
	
	public void setMessageOnId(long messageOnId) {
		this.messageOnId = messageOnId;
	}
	
	public String getCommentContent() {
		return commentContent;
	}
	
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	
}
