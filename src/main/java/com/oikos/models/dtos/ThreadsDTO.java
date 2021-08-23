package com.oikos.models.dtos;

public class ThreadsDTO {

	private String threadsTitle;
	private String threadsMessage;
	private long threadsCreatorId;
	private long communityOnId;

	public String getThreadsTitle() {
		return threadsTitle;
	}

	public void setThreadsTitle(String threadsTitle) {
		this.threadsTitle = threadsTitle;
	}
	
	public String getThreadsMessage() {
		return threadsMessage;
	}

	public void setThreadsMessage(String threadsMessage) {
		this.threadsMessage = threadsMessage;
	}

	public long getThreadsCreatorId() {
		return threadsCreatorId;
	}

	public void setThreadsCreatorId(long threadsCreatorId) {
		this.threadsCreatorId = threadsCreatorId;
	}

	public long getCommunityOnId() {
		return communityOnId;
	}

	public void setCommunityOnId(long communityOnId) {
		this.communityOnId = communityOnId;
	}


}
