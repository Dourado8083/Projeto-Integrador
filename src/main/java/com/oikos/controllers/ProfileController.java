package com.oikos.controllers;

import java.util.List;

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
import com.oikos.repositories.ProfileRepository;

@RestController
@RequestMapping("/profile")
@CrossOrigin("*")
public class ProfileController {

	@Autowired
	private ProfileRepository profrepository;
	
	@GetMapping
	public ResponseEntity<List<Profile>> GetAll(){
		return ResponseEntity.ok(profrepository.findAll());
	}
	
	@GetMapping("/{profileId}")
	public ResponseEntity<Profile> GetById(@PathVariable long profileId){
		return profrepository.findById(profileId).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/name/{profileName}")
	public ResponseEntity<List<Profile>> GetByName(@PathVariable String profileName)
	{
		return ResponseEntity.ok(profrepository.findAllByProfileNameContainingIgnoreCase(profileName));
	}
	
	@GetMapping("/email/{profileEmail}")
	public ResponseEntity<List<Profile>> GetByEmail(@PathVariable String profileEmail)
	{
		return ResponseEntity.ok(profrepository.findAllByProfileEmailContainingIgnoreCase(profileEmail));
	}

	@PostMapping
	public ResponseEntity<Profile> post (@RequestBody Profile profile)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(profrepository.save(profile));
	}
	
	@PutMapping
	public ResponseEntity<Profile> put (@RequestBody Profile profile)
	{
		return ResponseEntity.status(HttpStatus.OK).body(profrepository.save(profile));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id)
	{
		profrepository.deleteById(id);
	}
}


