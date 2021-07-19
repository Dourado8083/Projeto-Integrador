package com.oikos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikos.models.Profile;
import com.oikos.models.dtos.ProfileCommunityDTO;
import com.oikos.repositories.CommunityRepository;
import com.oikos.repositories.ProfileRepository;

@Service
public class CommunityService {

	@Autowired
	private CommunityRepository communityRepository;
	@Autowired
	private ProfileRepository profileRepository;

	/**
	 * Método para entrar em uma comunidade.
	 * 
	 * @param ProfileCommunityDTO
	 * @return Um Optional contendo a comunidade a qual o usuário de juntou ou vázio
	 *         para ser tratado como erro.
	 * @author Edson (Cyberpatinho)
	 */
	public Optional<?> joinCommunity(ProfileCommunityDTO profileCommunityDto) {
		return communityRepository.findByCommunityName(profileCommunityDto.getCommunityName()).map(community -> {

			Optional<Profile> profile = profileRepository.findByProfileEmail(profileCommunityDto.getProfileEmail());

			if (profile.isEmpty()) {
				return Optional.empty();
			}

			profile.get().getMemberOf().add(community);
			community.getCommunityMembers().add(profile.get());
			community.setCommunityNumberOfMembers(community.getCommunityNumberOfMembers() + 1);

			profileRepository.save(profile.get());

			return Optional.ofNullable(communityRepository.save(community));

		}).orElse(Optional.empty());
	}

	/**
	 * Método de sair de uma comunidade. A implementação atual roda em complexidade
	 * O(N) de tempo e pode ser melhorada futuramente, no entanto, dado o escopo
	 * limitado do projeto, não é uma prioridade (Pode-se usar um set, talvez?).
	 * 
	 * @param ProfileCommunityDTO
	 * @return Um Optional contendo a comunidade da qual o usuário saiu ou vázio
	 *         para ser tratado como erro.
	 * @author Edson (Cyberpatinho)
	 */
	public Optional<?> leaveCommunity(ProfileCommunityDTO profileCommunityDto) {
		return communityRepository.findByCommunityName(profileCommunityDto.getCommunityName()).map(community -> {

			Optional<Profile> profile = profileRepository.findByProfileEmail(profileCommunityDto.getProfileEmail());

			if (profile.isEmpty()) {
				return Optional.empty();
			}

			boolean flagProfile = true;
			boolean flagCommunity = true;
			int communitySize = community.getCommunityMembers().size();
			int profileCommunitiesOn = profile.get().getMemberOf().size();

			for (int i = 0; i < Math.max(communitySize, profileCommunitiesOn) && flagCommunity && flagProfile; i++) {
				if (i < communitySize && flagProfile) {
					if (community.getCommunityMembers().get(i).equals(profile.get())) {
						community.getCommunityMembers().remove(i);
						flagProfile = false;
					}
				}
				if (i < profileCommunitiesOn && flagCommunity) {
					if (profile.get().getMemberOf().get(i).equals(community)) {
						profile.get().getMemberOf().remove(i);
						flagCommunity = false;
					}
				}
			}

			if (flagCommunity || flagProfile) {
				return Optional.empty();
			}

			community.setCommunityNumberOfMembers(community.getCommunityNumberOfMembers() - 1);

			profileRepository.save(profile.get());

			return Optional.ofNullable(communityRepository.save(community));

		}).orElse(Optional.empty());
	}

	/**
	 * Método para editar a Bio de uma comunidade caso o usuário seja dono dela.
	 * 
	 * @param ProfileCommunityDTO
	 * @return Um Optional contendo a comunidade alterada pelo usuário ou vázio para
	 *         ser tratado como erro.
	 * @author Edson (Cyberpatinho)
	 */
	public Optional<?> editBio(ProfileCommunityDTO profileCommunityDto) {
		return communityRepository.findByCommunityName(profileCommunityDto.getCommunityName()).map(community -> {

			Optional<Profile> profile = profileRepository.findByProfileEmail(profileCommunityDto.getProfileEmail());
			
			if (profile.isEmpty() || !community.getCommunityOwner().getProfileEmail().equals(profile.get().getProfileEmail())) {
				return Optional.empty();
			}

			community.setCommunityBio(profileCommunityDto.getCommunityBio());

			return Optional.ofNullable(communityRepository.save(community));
		}).orElse(Optional.empty());
	}

}
