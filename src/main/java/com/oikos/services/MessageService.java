package com.oikos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikos.models.Community;
import com.oikos.models.Message;
import com.oikos.models.Profile;
import com.oikos.models.dtos.MessageBusinessDTO;
import com.oikos.models.dtos.MessageCommunityDTO;
import com.oikos.models.dtos.MessageProfileDTO;
import com.oikos.repositories.BusinessRepository;
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
	
	@Autowired
	private BusinessRepository businessRepository;
	
	
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
			message.setMessagePic(messageProfileDto.getMessagePic());
			message.setProfileFrom(profileFrom);
			message.setProfileOn(profileOn.get());
			message.setMessageType(messageProfileDto.getMessageType());
			
			profileFrom.getMessagesSent().add(message);
			profileOn.get().getMessagesReceived().add(message);
			
			profileRepository.save(profileFrom);
			profileRepository.save(profileOn.get());
			
			return Optional.ofNullable(messageRepository.save(message));
			
		}).orElse(Optional.empty());
	}
	
	/**
	 * Método para postar uma mensagem em uma comunidade
	 * 
	 * @param ProfileCommunityDTO
	 * @return Um Optional contendo a mensagem postada
	 */
	public Optional<?> postMessageOnCommunity(MessageCommunityDTO messageCommunityDto) {
		return profileRepository.findById(messageCommunityDto.getProfileFromId()).map(profileFrom -> {
			
			Optional<Community> communityOn = communityRepository.findById(messageCommunityDto.getCommunityToId());
			
			if(communityOn.isEmpty()) {
				return Optional.empty();
			}
			
			Message message = new Message();
			
			message.setMessageContent(messageCommunityDto.getMessageContent());
			message.setMessagePic(messageCommunityDto.getMessagePic());
			message.setProfileFrom(profileFrom);
			message.setCommunityOn(communityOn.get());
			message.setMessageType(messageCommunityDto.getMessageType());
			
			profileFrom.getMessagesSent().add(message);
			communityOn.get().getMessages().add(message);
			
			profileRepository.save(profileFrom);
			communityRepository.save(communityOn.get());
			
			return Optional.ofNullable(messageRepository.save(message));
			
		}).orElse(Optional.empty());
	}
	
	/**
	 * Método para postar uma mensagem em um negócio
	 * 
	 * @param ProfileCommunityDTO
	 * @return Um Optional contendo a mensagem postada
	 */
	public Optional<?> postMessageOnBusiness(MessageBusinessDTO messageBusinessDto) {
		return businessRepository.findById(messageBusinessDto.getBusinessToId()).map(business -> {
			
			Message message = new Message();
			
			message.setBusinessOn(business);
			message.setMessageContent(messageBusinessDto.getMessageContent());
			message.setMessagePic(messageBusinessDto.getMessagePic());
			message.setMessageType(messageBusinessDto.getMessageType());
			
			business.getBusinessMessages().add(message);
			businessRepository.save(business);
			
			return Optional.ofNullable(messageRepository.save(message));
			
		}).orElse(Optional.empty());
	}
	
	
}
