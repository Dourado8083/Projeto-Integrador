package com.oikos.models.dtos;

public class ThreadsDTO {

	private String threadsTitle;
	private long threadsCreatorId;
	private long communityOnId;

	public String getThreadsTitle() {
		return threadsTitle;
	}

	public void setThreadsTitle(String threadsTitle) {
		this.threadsTitle = threadsTitle;
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
