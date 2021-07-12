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
<<<<<<< HEAD
 	public Optional<Profile> findByProfileEmailIgnoreCase(String profileEmail);
=======
>>>>>>> 495814dfec1bcd5945152d2c6f3d739c9fe62dee
 	public Optional<Profile> findByProfileName(String profileName);

	
	
}
