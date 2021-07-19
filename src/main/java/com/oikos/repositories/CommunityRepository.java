package com.oikos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oikos.models.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long>{

	public List<Community> findAllByCommunityNameContainingIgnoreCase(String communityName);

	public Optional<Community> findByCommunityId(long communityId);

	public Optional<Community> findByCommunityName(String communityName);
	
	public void deleteByCommunityId(long communityId);
	
}