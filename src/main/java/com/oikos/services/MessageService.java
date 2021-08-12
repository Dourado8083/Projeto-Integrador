package com.oikos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikos.models.Message;
import com.oikos.models.Profile;
import com.oikos.models.dtos.MessageProfileDTO;
import com.oikos.repositories.CommunityRepository;
import com.oikos.repositories.MessageRepository;
import com.oikos.repositories.ProfileRepository;

@Service
public class MessageService {
	
	@Autowired
	private CommunityRepository communityRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	
	/**
	 * Método para postar uma mensagem em um perfil
	 * 
	 * @param ProfileCommunityDTO
	 * @return Um Optional contendo a mensagem postada
	 */
	public Optional<?> postMessageOnProfile(MessageProfileDTO messageProfileDto) {
		return profileRepository.findById(messageProfileDto.getProfileFromId()).map(profileFrom -> {
			
			Optional<Profile> profileOn = profileRepository.findById(messageProfileDto.getProfileToId());
			
			if(profileOn.isEmpty()) {
				return Optional.empty();
			}
			
			Message message = new Message();
			
			message.setMessageContent(messageProfileDto.getMessageContent());
			message.setProfileFrom(profileFrom);
			message.setProfileOn(profileOn.get());
			
			profileFrom.getMessagesSent().add(message);
			profileOn.get().getMessagesReceived().add(message);
			
			profileRepository.save(profileFrom);
			profileRepository.save(profileOn.get());
			
			return Optional.ofNullable(messageRepository.save(message));
			
		}).orElse(Optional.empty());
	}
	
	
}
