package com.oikos.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oikos.models.Message;
import com.oikos.repositories.MessageRepository;

@RestController
@RequestMapping
public class MessageController {
	@Autowired
	private MessageRepository repository;

	@GetMapping("messageContent/{messageContent}")
	public Optional<Message> findByMessageContent(@PathVariable String messageContent) {
		return repository.findAllByMessageContentContainingIgnoreCase(messageContent);
		/* alteração de RequestParam para @PathVariable */
	}

	/*
	 * @GetMapping("/from") public ResponseEntity<Message>GetByName(@RequestBody
	 * String from)
	 */
	{

	}

	@GetMapping("/{messageId}")
	public ResponseEntity<Message> GetById(@PathVariable long messageId) {
		return repository.findById(messageId).map(mes -> ResponseEntity.ok(mes))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Message> post(@RequestBody Message message) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(message));
	}

	@PutMapping
	public ResponseEntity<Message> put(@RequestBody Message message) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(message));

	}

}