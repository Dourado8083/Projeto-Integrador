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
	
	
}
