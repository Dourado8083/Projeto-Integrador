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

import com.oikos.models.Community;
import com.oikos.models.dtos.ProfileCommunityDTO;
import com.oikos.repositories.CommunityRepository;
import com.oikos.services.CommunityService;

@RestController
@RequestMapping("/community")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
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

	@PostMapping("/create")
	public ResponseEntity<Community> communityPost(@RequestBody Community com) {
		return ResponseEntity.status(HttpStatus.CREATED).body(communityRepository.save(com));
	}

	@DeleteMapping("/delete/{communityid}")
	public void communityDelete(@PathVariable long communityId) {
		communityRepository.deleteById(communityId);
	}

	@PutMapping("/join-community")
	public ResponseEntity<?> joinCommunity(@Valid @RequestBody ProfileCommunityDTO profileCommunityDto) {
		return communityService.joinCommunity(profileCommunityDto).map(community -> {
			return ResponseEntity.status(200)
					.body("Você agora é membro da comunidade " + profileCommunityDto.getCommunityName() + "!");
		}).orElse(ResponseEntity.status(400).body("Oops, operação inválida!"));
	}

	@PutMapping("/leave-community")
	public ResponseEntity<?> leaveCommunity(@Valid @RequestBody ProfileCommunityDTO profileCommunityDto) {
		return communityService.leaveCommunity(profileCommunityDto).map(community -> {
			return ResponseEntity.status(200).body(
					"Você saiu da comunidade" + profileCommunityDto.getCommunityName() + ". Sentiremos sua falta!");
		}).orElse(ResponseEntity.status(400).body("Oops, operação inválida!"));
	}

	@PutMapping("/edit-bio")
	public ResponseEntity<?> editBio(@Valid @RequestBody ProfileCommunityDTO profileCommunityDto) {
		return communityService.editBio(profileCommunityDto).map(community -> {
			return ResponseEntity.status(200).body("A descrição foi alteradas com sucesso!");
		}).orElse(ResponseEntity.status(400).body("Oops, operação inválida!"));
	}

	@PutMapping("/delete-community")
	public ResponseEntity<?> deleteCommunity(@Valid @RequestBody ProfileCommunityDTO profileCommunityDto) {
		return communityService.deleteCommunity(profileCommunityDto).map(community -> {
			return ResponseEntity.status(200).body("Comunidade deletada com sucesso!");
		}).orElse(ResponseEntity.status(400).body("Oops, operação inválida!"));
	}
}
