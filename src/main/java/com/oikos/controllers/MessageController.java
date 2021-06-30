package com.oikos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oikos.models.Message;
import com.oikos.repositories.MessageRepository;

@RestController
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageRepository messagerepository;

	@GetMapping("messageContent/{messageContent}")
	public ResponseEntity<List<Message>> GetByMessageContent(@PathVariable String messageContent) {
		return ResponseEntity.ok(messagerepository.findAllByMessageContentContainingIgnoreCase(messageContent));
		/* alteração de RequestParam para @PathVariable 
		 * alteração de OPTIONAL para Response Entity
		 */
	}

	/*
	 * @GetMapping("/from") public ResponseEntity<Message>GetByName(@RequestBody
	 * String from)
	 */
	{

	}

	@GetMapping("/{messageId}")
	public ResponseEntity<Message> GetById(@PathVariable long messageId) {
		return messagerepository.findById(messageId).map(mes -> ResponseEntity.ok(mes))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Message> post(@RequestBody Message message) {
		return ResponseEntity.status(HttpStatus.CREATED).body(messagerepository.save(message));
	}

	@PutMapping
	public ResponseEntity<Message> put(@RequestBody Message message) {
		return ResponseEntity.status(HttpStatus.OK).body(messagerepository.save(message));

	}

}