package com.oikos.security;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oikos.models.Profile;
import com.oikos.repositories.ProfileRepository;

@Service
public class UserDetailServiceImplementation implements UserDetailsService {
	
	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Profile> profile = profileRepository.findByProfileEmailIgnoreCase(email);
		
		if(profile.isPresent()) {
			return new UserDetailImplementation(profile.get());
		}
		else {
			throw new UsernameNotFoundException(email + " não cadastrado!");
		}
		
	}
}
