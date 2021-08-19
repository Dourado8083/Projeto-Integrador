package com.oikos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oikos.models.dtos.CommentDTO;
import com.oikos.services.CommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/post")
	public ResponseEntity<?> postComment(@Valid @RequestBody CommentDTO commentDto) {
		return commentService.postComment(commentDto).map(comment -> {
			return ResponseEntity.status(200).body(comment);
		}).orElse(ResponseEntity.status(400).build());
	}
	


}
