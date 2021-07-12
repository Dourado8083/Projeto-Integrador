package com.oikos.services;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.oikos.models.Profile;
import com.oikos.models.dtos.ProfileLoginDTO;
import com.oikos.repositories.CommunityRepository;
import com.oikos.repositories.ProfileRepository;

public class ProfileService {

	private @Autowired CommunityRepository communityRepository;
	private @Autowired ProfileRepository profileRepository;

	public Optional<Object> profileSignup(Profile profile) {
		return profileRepository.findByProfileEmailIgnoreCase(profile.getProfileEmail()).map(emailAlreadyInUse -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encryptedPassword = encoder.encode(profile.getProfilePassword());
			profile.setProfilePassword(encryptedPassword);
			return Optional.ofNullable(profileRepository.save(profile));
		});

	}

	public Optional<?> getCredentials(ProfileLoginDTO profileLoginDto) {
		
		return profileRepository.findByProfileEmailIgnoreCase(profileLoginDto.getProfileEmail()).map(profile -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches(profileLoginDto.getProfilePassword(), profile.getProfilePassword())) {
				String basicStructure = profileLoginDto.getProfileEmail() + ":" + profileLoginDto.getProfilePassword();
				byte[] authorizationBase64 = Base64.encodeBase64(basicStructure.getBytes(Charset.forName("US-ASCII")));
				String authorizationHeader = "Basic " + new String(authorizationBase64);
				
				profileLoginDto.setProfileToken(authorizationHeader);
				profileLoginDto.setProfileEmail(profile.getProfileEmail());
				profileLoginDto.setProfilePassword(profile.getProfilePassword());
				
				return Optional.ofNullable(profileLoginDto);
			}
			else {
				return Optional.empty();
			}

		}).orElse(Optional.empty());
				
		
	}

}
