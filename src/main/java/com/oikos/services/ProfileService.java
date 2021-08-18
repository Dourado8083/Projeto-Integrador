package com.oikos.services;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oikos.models.Community;
import com.oikos.models.Profile;
import com.oikos.models.dtos.ProfileCommunityDTO;
import com.oikos.models.dtos.ProfileDTO;
import com.oikos.models.dtos.ProfileLoginDTO;
import com.oikos.repositories.CommunityRepository;
import com.oikos.repositories.ProfileRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private CommunityRepository communityRepository;

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
			if (encoder.matches(profileLoginDto.getProfilePassword(), profile.getProfilePassword())) {
				String basicStructure = profileLoginDto.getProfileEmail() + ":" + profileLoginDto.getProfilePassword();
				byte[] authorizationBase64 = Base64.encodeBase64(basicStructure.getBytes(Charset.forName("US-ASCII")));
				String authorizationHeader = "Basic " + new String(authorizationBase64);

				profileLoginDto.setProfileToken(authorizationHeader);
				profileLoginDto.setProfileEmail(profile.getProfileEmail());
				profileLoginDto.setProfilePassword(profile.getProfilePassword());

				return Optional.ofNullable(profileLoginDto);
			} else {
				return Optional.empty();
			}

		}).orElse(Optional.empty());

	}

	
	public Optional<ProfileLoginDTO> signIn(Optional<ProfileLoginDTO> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Profile> usuario = profileRepository.findByProfileEmail(user.get().getProfileEmail());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getProfilePassword(), usuario.get().getProfilePassword())) {

				String auth = user.get().getProfileEmail() + ":" + user.get().getProfilePassword();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setProfileToken(authHeader);			
				user.get().setProfileId(usuario.get().getProfileId());
				user.get().setProfileName(usuario.get().getProfileName());
				user.get().setProfilePic(usuario.get().getProfilePic());
				
				return user;

			}
		}
		return Optional.empty();
	}
	
	
	/**
	 * Método para criar uma comunidade.
	 * 
	 * @param ProfileCommunityDTO
	 * @return Um Optional contendo a comunidade criada ou vázio para ser tratado
	 *         como erro.
	 */
	public Optional<Object> createCommunity(ProfileCommunityDTO profileCommunityDto) {
		return communityRepository.findByCommunityName(profileCommunityDto.getCommunityName())
				.map(communityAlreadyExists -> {
					return Optional.empty();
				}).orElseGet(() -> {
					Optional<Profile> communityOwner = profileRepository
							.findById(profileCommunityDto.getProfileId());

					if (communityOwner.isEmpty()) {
						return Optional.empty();
					}

					Community communityToCreate = new Community();
					communityToCreate.setCommunityOwner(communityOwner.get());
					communityToCreate.setCommunityNumberOfMembers(1);
					communityToCreate.setCommunityName(profileCommunityDto.getCommunityName());
					communityToCreate.setCommunityBio(profileCommunityDto.getCommunityBio());
					communityToCreate.setCommunityPic(profileCommunityDto.getCommunityPic());

					communityOwner.get().getMemberOf().add(communityToCreate);
					communityToCreate.getCommunityMembers().add(communityOwner.get());
					profileRepository.save(communityOwner.get());

					return Optional.ofNullable(communityRepository.save(communityToCreate));
				});
	}
	
	/**
	 * Método para editar a bio de um perfil 
	 * 
	 * @param ProfileDTO
	 * @return Um Optional contendo o perfil alterado pelo usuário ou vázio para
	 *         ser tratado como erro.
	 */
	
	public Optional<?> editBio (ProfileDTO profiledto){
		return profileRepository.findById(profiledto.getProfileId()).map(profile -> {
			
		profile.setProfileBio(profiledto.getProfileBio());
		
		return Optional.ofNullable(profileRepository.save(profile));
			
		}).orElse(Optional.empty());
	}
	

	public Optional<Object> follow() {
		return Optional.empty();
	}

}
