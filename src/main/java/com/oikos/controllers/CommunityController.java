package com.oikos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oikos.models.Community;
import com.oikos.repositories.CommunityRepository;

@RestController
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private CommunityRepository communityRepository;

	@GetMapping("/all")
	public List<Community> communityGetAll() {
		return communityRepository.findAll();
	}

	@GetMapping("/{communityId}")
	public ResponseEntity<Community> communityGetById(@PathVariable long communityId) {
		return communityRepository.findById(communityId).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/communityname/{communityName}")
	public List<Community> communityGetByName(@PathVariable String communityName) {
		return communityRepository.findAllByCommunityNameContainingIgnoreCase(communityName);
	}

	@PostMapping
	public ResponseEntity<Community> communityPost(@Valid @RequestBody Community community) {
		return ResponseEntity.status(HttpStatus.CREATED).body(communityRepository.save(community));
	}

	@PutMapping
	public ResponseEntity<Community> communityPut(@RequestBody Community community) {
		return ResponseEntity.status(HttpStatus.OK).body(communityRepository.save(community));
	}

	@DeleteMapping("/delete/{communityid}")
	public void communityDelete(@PathVariable long communityId) {
		communityRepository.deleteById(communityId);
	}

}
