package com.oikos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikos.models.Profile;
import com.oikos.models.dtos.ProfileCommunityDTO;
import com.oikos.repositories.CommunityRepository;
import com.oikos.repositories.ProfileRepository;

@Service
public class CommunityService {

	@Autowired
	private CommunityRepository communityRepository;
	@Autowired
	private ProfileRepository profileRepository;

	public Optional<?> joinCommunity(ProfileCommunityDTO profileCommunityDto){
		return communityRepository.findByCommunityName(profileCommunityDto.getCommunityName()).map(community -> {
			
			Optional<Profile> profile = profileRepository.findByProfileEmail(profileCommunityDto.getProfileEmail());
			
			if(profile.isEmpty()) {
				return Optional.empty();
			}
			
			profile.get().getMemberOf().add(community);
			community.getCommunityMembers().add(profile.get());
			community.setCommunityNumberOfMembers(community.getCommunityNumberOfMembers() + 1);
			
			communityRepository.save(community);
			profileRepository.save(profile.get());
			
			return Optional.ofNullable(communityRepository.save(community));
			
		}).orElse(Optional.empty());
	}

}
