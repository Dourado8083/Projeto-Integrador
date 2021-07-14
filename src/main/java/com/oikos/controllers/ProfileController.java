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
import com.oikos.models.dtos.ProfileLoginDTO;
import com.oikos.repositories.ProfileRepository;
import com.oikos.services.ProfileService;

@RestController
@RequestMapping("/profile")
@CrossOrigin("*")
public class ProfileController {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Profile>> GetAll(){
		return ResponseEntity.ok(profileRepository.findAll());
	}
	
	@GetMapping("/{profileId}")
	public ResponseEntity<Profile> GetById(@PathVariable long profileId){
		return profileRepository.findById(profileId).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/name/{profileName}")
	public ResponseEntity<List<Profile>> GetByName(@PathVariable String profileName)
	{
		return ResponseEntity.ok(profileRepository.findAllByProfileNameContainingIgnoreCase(profileName));
	}
	
	@GetMapping("/email/{profileEmail}")
	public ResponseEntity<List<Profile>> GetByEmail(@PathVariable String profileEmail)
	{
		return ResponseEntity.ok(profileRepository.findAllByProfileEmailContainingIgnoreCase(profileEmail));
	}

	@PostMapping
	public ResponseEntity<Profile> post (@RequestBody Profile profile)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(profileRepository.save(profile));
	}
	
	@PutMapping
	public ResponseEntity<Profile> put (@RequestBody Profile profile)
	{
		return ResponseEntity.status(HttpStatus.OK).body(profileRepository.save(profile));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id)
	{
		profileRepository.deleteById(id);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Object> cadastrarUsuario(@Valid @RequestBody Profile profile) {

		Optional<Object> newProfile = profileService.profileSignup(profile);
		if (newProfile.isEmpty()) {
			return ResponseEntity.status(200).body("Usuário existente!");
		} else {
			return ResponseEntity.status(201).body("Usuário criado!");
		}

	}
	
	@PutMapping("/credentials")
	public ResponseEntity<?> getCredentials(@Valid @RequestBody ProfileLoginDTO profileLoginDto) {
		return profileService.getCredentials(profileLoginDto)
				.map(profile -> ResponseEntity.ok(profile))
				.orElse(ResponseEntity.badRequest().build());
	}

}


