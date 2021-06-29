package com.oikos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long groupId;
	
	@NotNull
	private String groupName;
	
	@NotNull
	private long groupOwner;
	
	private String groupPic;
	
	private String groupBio;

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public long getGroupOwner() {
		return groupOwner;
	}

	public void setGroupOwner(long groupOwner) {
		this.groupOwner = groupOwner;
	}

	public String getGroupPic() {
		return groupPic;
	}

	public void setGroupPic(String groupPic) {
		this.groupPic = groupPic;
	}

	public String getGroupBio() {
		return groupBio;
	}

	public void setGroupBio(String groupBio) {
		this.groupBio = groupBio;
	}
	
	
}
