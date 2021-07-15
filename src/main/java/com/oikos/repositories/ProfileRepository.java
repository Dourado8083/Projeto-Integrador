package com.oikos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oikos.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	public List<Profile> findAllByProfileNameContainingIgnoreCase(String profileName);

	public List<Profile> findAllByProfileEmailContainingIgnoreCase(String profileEmail);

	public Optional<Profile> findByProfileEmailIgnoreCase(String profileEmail);
	
	public Optional<Profile> findByProfileEmail(String profileEmail);

	public Optional<Profile> findByProfileName(String profileName);

}
