package com.oikos.controllers;

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

import com.oikos.models.Group;
import com.oikos.repositories.GroupRepository;

@RestController
@RequestMapping
public class GroupController {
	
	@Autowired
	private GroupRepository repository;
	
	@GetMapping("/group")
	public List<Group> GetAll()
	{
		return repository.findAll();
	}
	
	@GetMapping("/{groupId}")
	public ResponseEntity<Group> GetById(@PathVariable long groupId)
	{
		return repository.findById(groupId).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/groupname/{groupName}")
	public ResponseEntity<Group> GetByName(@RequestParam String groupName)
	{
		return repository.findByTitulo(groupName);
		
	}
	
	@PostMapping
	public ResponseEntity<Group> post (@RequestBody Group group)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(group));
	}
	
	@PutMapping
	public ResponseEntity<Group> put (@RequestBody Group group)
	{
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(group));
	}
	
	@DeleteMapping("/{groupid}")
	public void delete(@PathVariable long groupId)
	{
		repository.deleteById(groupId);
	}

}

