package com.oikos.controllers;

import java.util.List;
import java.util.Optional;

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

import com.oikos.models.Profile;
import com.oikos.models.dtos.ProfileCommunityDTO;
import com.oikos.models.dtos.ProfileDTO;
import com.oikos.models.dtos.ProfileLoginDTO;
import com.oikos.repositories.ProfileRepository;
import com.oikos.services.ProfileService;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class ProfileController {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ProfileService profileService;

	@GetMapping("/all")
	public ResponseEntity<List<Profile>> GetAll() {
		return ResponseEntity.ok(profileRepository.findAll());
	}
	
	@GetMapping("/{profileId}")
	public ResponseEntity<Profile> profileGetById(@PathVariable long profileId) {
		return profileRepository.findById(profileId).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		profileRepository.deleteById(id);
	}
	
	@PutMapping("/edit-profile")
	public ResponseEntity<Object> editProfile(@Valid @RequestBody ProfileDTO profileDto) {
		return profileService.editBio(profileDto).map(profile -> {
			return ResponseEntity.status(200).build();
		}).orElse(ResponseEntity.status(400).build());
	}

	@PostMapping("/signup")
	public ResponseEntity<Object> cadastrarUsuario(@Valid @RequestBody Profile profile) {

		Optional<Object> newProfile = profileService.profileSignup(profile);
		if (newProfile.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).build();
		}

	}

	@PostMapping("/credentials")
	public ResponseEntity<?> getCredentials(@Valid @RequestBody ProfileLoginDTO profileLoginDto) {
		return profileService.getCredentials(profileLoginDto).map(profile -> ResponseEntity.ok(profile))
				.orElse(ResponseEntity.badRequest().build());
	}

	@PostMapping("/signin")
	public ResponseEntity<ProfileLoginDTO> signIn(@RequestBody Optional<ProfileLoginDTO> user) {
		return profileService.signIn(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/create-community")
	public ResponseEntity<?> createCommunity(@Valid @RequestBody ProfileCommunityDTO profileCommunityDto) {
		return profileService.createCommunity(profileCommunityDto).map(newCommunity -> {
			return ResponseEntity.status(201).build();
		}).orElse(ResponseEntity.status(400).build());
	}

}
