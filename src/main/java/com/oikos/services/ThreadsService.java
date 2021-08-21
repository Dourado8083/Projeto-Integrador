package com.oikos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikos.models.Community;
import com.oikos.models.Threads;
import com.oikos.models.dtos.ThreadsDTO;
import com.oikos.repositories.CommunityRepository;
import com.oikos.repositories.ProfileRepository;
import com.oikos.repositories.ThreadsRepository;

@Service
public class ThreadsService {
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	CommunityRepository communityRepository;
	
	@Autowired
	ThreadsRepository threadsRepository;
	
	public Optional<?> createThreads(ThreadsDTO threadsDto) {
		return profileRepository.findById(threadsDto.getThreadsCreatorId()).map(profile -> {
			
			Optional<Community> community = communityRepository.findById(threadsDto.getCommunityOnId());
			
			if(community.isEmpty()) {
				return Optional.empty();
			}
			
			Threads threads = new Threads();
			
			threads.setThreadsCreator(profile);
			threads.setCommunityOn(community.get());
			threads.setThreadsTitle(threadsDto.getThreadsTitle());
			threads.setNumberOfMessages(0);
			
			community.get().getThreadsList().add(threads);
			profile.getThreadsCreated().add(threads);
			
			communityRepository.save(community.get());
			profileRepository.save(profile);
			
			return Optional.ofNullable(threadsRepository.save(threads));
			
		}).orElse(Optional.empty());
	}
	
}
