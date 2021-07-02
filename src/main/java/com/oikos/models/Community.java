package com.oikos.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Community {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long communityId;
	
	@NotNull(message = "Insira um nome para a comunidade!")
	@Size(min = 5, max = 30)
	private String communityName;
	
	private String communityPic;
	
	@Size(max = 255)
	private String communityBio;
	
	@ManyToOne
	private Profile communityOwner;
	
	@ManyToMany
	@JoinTable(name = "Community_Profile",
		joinColumns = @JoinColumn(name = "communityId"),
		inverseJoinColumns = @JoinColumn(name = "profileId")
	)
	private List<Profile> communityMembers;
	
	//private List<Message> boards = new ArrayList<Message>
	
	public long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(long communityId) {
		this.communityId = communityId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	
	
	public Profile getCommunityOwner() {
		return communityOwner;
	}

	public void setCommunityOwner(Profile communityOwner) {
		this.communityOwner = communityOwner;
	}

	public String getCommunityPic() {
		return communityPic;
	}

	public void setCommunityPic(String communityPic) {
		this.communityPic = communityPic;
	}

	public String getCommunityBio() {
		return communityBio;
	}

	public void setCommunityBio(String communityBio) {
		this.communityBio = communityBio;
	}
	
	
}
