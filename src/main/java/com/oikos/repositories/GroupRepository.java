package com.oikos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.oikos.models.Group;

public interface GroupRepository extends JpaRepository<Group, String>{

	public List<Group> findAllByNameContainingIgnoreCase (String groupName);

	public Optional<Group> findById(long groupId);

	public ResponseEntity<Group> findByTitulo(String groupName);

	public void deleteById(long groupId);
	
}