package com.oikos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikos.models.Comment;
import com.oikos.models.Message;
import com.oikos.models.dtos.CommentDTO;
import com.oikos.repositories.CommentRepository;
import com.oikos.repositories.MessageRepository;
import com.oikos.repositories.ProfileRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	public Optional<?> postComment(CommentDTO commentDto) {
		return profileRepository.findById(commentDto.getProfileFromId()).map(profile -> {
			
			Optional<Message> message = messageRepository.findById(commentDto.getMessageOnId());
			if(message.isEmpty()) {
				return Optional.empty();
			}
			
			Comment comment = new Comment();
			
			comment.setProfileFrom(profile);
			comment.setMessageOn(message.get());
			comment.setCommentContent(commentDto.getCommentContent());
			
			message.get().getCommentList().add(comment);
			
			messageRepository.save(message.get());
			
			return Optional.ofNullable(commentRepository.save(comment));
			
		}).orElse(Optional.empty());
	}
	
}
