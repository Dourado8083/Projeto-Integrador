package com.oikos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikos.repositories.CommunityRepository;
import com.oikos.repositories.ProfileRepository;

@Service
public class CommunityServices {
	
	@Autowired
	private CommunityRepository communityRepository;
	@Autowired
	private ProfileRepository profileRepository;
	
	public void createCommunity() {
		
	}
	
	
}
