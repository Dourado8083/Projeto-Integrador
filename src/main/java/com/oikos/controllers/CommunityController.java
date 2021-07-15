package com.oikos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oikos.models.Community;
import com.oikos.models.dtos.ProfileCommunityDTO;
import com.oikos.repositories.CommunityRepository;
import com.oikos.services.CommunityService;

@RestController
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private CommunityRepository communityRepository;
	
	@Autowired
	private CommunityService communityService;

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


	@DeleteMapping("/delete/{communityid}")
	public void communityDelete(@PathVariable long communityId) {
		communityRepository.deleteById(communityId);
	}

	@PutMapping("/join-community")
	public ResponseEntity<?> joinCommunity(@Valid @RequestBody ProfileCommunityDTO profileCommunityDto){
		return communityService.joinCommunity(profileCommunityDto).map(community -> {
			return ResponseEntity.status(200).body("Você agora é membro da comunidade " + profileCommunityDto.getCommunityName() + "!");
		}).orElse(ResponseEntity.status(400).body("Operação inválida!"));
	}
	
}
