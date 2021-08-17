package com.oikos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oikos.models.Message;
import com.oikos.models.dtos.MessageBusinessDTO;
import com.oikos.models.dtos.MessageCommunityDTO;
import com.oikos.models.dtos.MessageProfileDTO;
import com.oikos.repositories.MessageRepository;
import com.oikos.services.MessageService;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessageController {
	
	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private MessageService messageService;
	
	@GetMapping("/all")
	public List<Message> messageGetAll() {
		return messageRepository.findAll();
	}
	
	@GetMapping("content/{messageContent}")
	public ResponseEntity<List<Message>> messageGetByMessageContent(@PathVariable String messageContent) {
		return ResponseEntity.ok(messageRepository.findAllByMessageContentContainingIgnoreCase(messageContent));
	}

	@GetMapping("/{messageId}")
	public ResponseEntity<Message> messageGetById(@PathVariable long messageId) {
		return messageRepository.findById(messageId).map(mes -> ResponseEntity.ok(mes))
				.orElse(ResponseEntity.notFound().build());
	}
	
	/*
	@PostMapping("/post")
	public ResponseEntity<Message> messagePost(@RequestBody Message message) {
		return ResponseEntity.status(HttpStatus.CREATED).body(messageRepository.save(message));
	}*/
	
	@PostMapping("/post")
	public ResponseEntity<?> postMessageOnProfile(@Valid @RequestBody MessageProfileDTO messageProfileDto) {
		return messageService.postMessageOnProfile(messageProfileDto).map(message -> {
			return ResponseEntity.status(200).body(message);
		}).orElse(ResponseEntity.status(400).build());
	}
	
	@PostMapping("/community-post")
	public ResponseEntity<?> postMessageOnCommunity(@Valid @RequestBody MessageCommunityDTO messageCommunityDto) {
		return messageService.postMessageOnCommunity(messageCommunityDto).map(message -> {
			return ResponseEntity.status(200).body(message);
		}).orElse(ResponseEntity.status(400).build());
	}
	
	@PostMapping("/business-post")
	public ResponseEntity<?> postMessageOnBusiness(@Valid @RequestBody MessageBusinessDTO messageBusinessDto) {
		return messageService.postMessageOnBusiness(messageBusinessDto).map(message -> {
			return ResponseEntity.status(200).body(message);
		}).orElse(ResponseEntity.status(400).build());
	}

	@PutMapping("/edit")
	public ResponseEntity<Message> messagePut(@RequestBody Message message) {
		return ResponseEntity.status(HttpStatus.OK).body(messageRepository.save(message));
	}

	@DeleteMapping("/delete/{messageId}")
	public void delete(@PathVariable long messageId) {
		messageRepository.deleteById(messageId);
	}

}