/*package com.oikos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.oikos.models.Community;

public interface CommunityRepository extends JpaRepository<Community, String>{

	public List<Community> findAllByNameContainingIgnoreCase (String communityName);

	public Optional<Community> findById(long communityId);

	public ResponseEntity<Community> findByTitulo(String communityName);

	public void deleteById(long communityId);
	
}*/