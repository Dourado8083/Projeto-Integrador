/*package com.oikos.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oikos.models.Community;
import com.oikos.repositories.CommunityRepository;

@RestController
@RequestMapping
public class CommunityController {
	
	@Autowired
	private CommunityRepository repository;
	@GetMapping("/community")
	public List<Community> GetAll()
	{
		return repository.findAll();
	}
	
	@GetMapping("/{communityId}")
	public ResponseEntity<Community> GetById(@PathVariable long communityId)
	{
		return repository.findById(communityId).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/communityname/{communityName}")
	public ResponseEntity<Community> GetByName(@RequestParam String communityName)
	{
		return repository.findByTitulo(communityName);
		
	}
	
	@PostMapping
	public ResponseEntity<Community> post (@RequestBody Community community)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(community));
	}
	
	@PutMapping
	public ResponseEntity<Community> put (@RequestBody Community community)
	{
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(community));
	}
	
	@DeleteMapping("/{communityid}")
	public void delete(@PathVariable long communityId)
	{
		repository.deleteById(communityId);
	}

}
*/
