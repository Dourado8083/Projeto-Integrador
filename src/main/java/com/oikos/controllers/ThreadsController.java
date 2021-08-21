package com.oikos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oikos.models.Threads;
import com.oikos.models.dtos.ThreadsDTO;
import com.oikos.repositories.ThreadsRepository;
import com.oikos.services.ThreadsService;

@RestController
@RequestMapping("/threads")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class ThreadsController {

	@Autowired
	ThreadsRepository threadsRepository;
	
	@Autowired
	ThreadsService threadsService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Threads>> GetAll() {
		return ResponseEntity.ok(threadsRepository.findAll());
	}
	
	@GetMapping("/{threadsId}")
	public ResponseEntity<Threads> threadsGetById(@PathVariable long threadsId) {
		return threadsRepository.findById(threadsId).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/create-threads")
	public ResponseEntity<?> createCommunity(@Valid @RequestBody ThreadsDTO threadsDto) {
		return threadsService.createThreads(threadsDto).map(newThreads -> {
			return ResponseEntity.status(201).build();
		}).orElse(ResponseEntity.status(400).build());
	}
	
}
