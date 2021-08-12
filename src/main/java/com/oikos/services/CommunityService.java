package com.oikos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikos.models.Community;
import com.oikos.models.Message;
import com.oikos.models.Profile;
import com.oikos.models.dtos.MessageDTO;
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
	 * Método para checar se um usuário pertence a uma comunidade
	 * 
	 * @param ProfileCommunityDTO
	 * @return Um booleano 
	 */
	boolean isMember(Profile profile, Community community) {
		
		
		for(int i = 0; i < profile.getMemberOf().size(); i++) {
			Community comm = profile.getMemberOf().get(i);
			if(comm.getCommunityId() == community.getCommunityId()) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Método para entrar em uma comunidade.
	 * 
	 * @param ProfileCommunityDTO
	 * @return Um Optional contendo a comunidade a qual o usuário de juntou ou vázio
	 *         para ser tratado como erro.
	 */
	public Optional<?> joinCommunity(ProfileCommunityDTO profileCommunityDto) {
		return communityRepository.findById(profileCommunityDto.getCommunityId()).map(community -> {

			Optional<Profile> profile = profileRepository.findById(profileCommunityDto.getProfileId());

			if (profile.isEmpty() || isMember(profile.get(), community)) {
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
	 */
	public Optional<?> leaveCommunity(ProfileCommunityDTO profileCommunityDto) {
		return communityRepository.findByCommunityId(profileCommunityDto.getCommunityId()).map(community -> {

			Optional<Profile> profile = profileRepository.findById(profileCommunityDto.getProfileId());

			if (profile.isEmpty() || !isMember(profile.get(), community)) {
				return Optional.empty();
			}
			
			for(int i = 0; i < profile.get().getMemberOf().size(); i++) {
				Community comm = profile.get().getMemberOf().get(i);
				if(profileCommunityDto.getCommunityId() == comm.getCommunityId()) {
					profile.get().getMemberOf().remove(i);
					break;
				}
			}
			
			for(int i = 0; i < community.getCommunityMembers().size(); i++) {
				Profile prof = community.getCommunityMembers().get(i);
				if(profileCommunityDto.getProfileId() == prof.getProfileId()) {
					community.getCommunityMembers().remove(i);
					break;
				}
			}

			community.setCommunityNumberOfMembers(community.getCommunityNumberOfMembers() - 1);

			profileRepository.save(profile.get());

			return Optional.ofNullable(communityRepository.save(community));

		}).orElse(Optional.empty());
	}
	
	/**
	 * Método para deletar uma comunidade.
	 * 
	 * @param profileMessageCommunityDTO
	 * @return Um Optional nulo ou com uma comunidade recém-instanciada para indicar sucesso
	 * 
	 */
	public Optional<?> deleteCommunity(ProfileCommunityDTO profileCommunityDto) {
		Optional<Community> comunidadeQueEstouProcurando = communityRepository
				.findById(profileCommunityDto.getCommunityId());
		return comunidadeQueEstouProcurando.map(community -> {

			Optional<Profile> usuarioQueEstouProcurando = profileRepository
					.findById(profileCommunityDto.getProfileId());

			if (usuarioQueEstouProcurando.isEmpty()) {
				return Optional.empty();
			}

			if (usuarioQueEstouProcurando.get().equals(community.getCommunityOwner()) == false) {
				return Optional.empty();
			}

			communityRepository.deleteById(community.getCommunityId());

			return Optional.ofNullable(new Community());

		}).orElse(Optional.empty());
	}

	/**
	 * Método para postar uma mensagem na comunidade.
	 * 
	 * @param profileMessageCommunityDTO
	 * @return Um Optional contendo a comunidade alterada pelo usuário ou vázio para
	 *         ser tratado como erro.
	 */
	public Optional<?> postMessageOnCommunity(MessageDTO messageDto) {
		return profileRepository.findByProfileEmail(messageDto.getProfileEmail()).map(profile -> {
			Optional<Community> community = communityRepository.findByCommunityName(messageDto.getCommunityName());
			if (community.isEmpty()) {
				return Optional.empty();
			}

			Message message = new Message();

			message.setMessageContent(messageDto.getMessageContent());
			message.setMessageTitle(messageDto.getMessageTitle());
			return Optional.empty();
		}).orElse(Optional.empty());

	}

	/**
	 * Método para editar a foto de uma comunidade caso o usuário seja dono dela.
	 * 
	 * @param ProfileCommunityDTO
	 * @return Um Optional contendo a comunidade alterada pelo usuário ou vázio para
	 *         ser tratado como erro.
	 */
	public Optional<?> changePicture(ProfileCommunityDTO profileCommunityDto) {
		return communityRepository.findByCommunityName(profileCommunityDto.getCommunityName()).map(community -> {

			Optional<Profile> profile = profileRepository.findByProfileEmail(profileCommunityDto.getProfileEmail());

			if (profile.isEmpty()
					|| !community.getCommunityOwner().getProfileEmail().equals(profile.get().getProfileEmail())) {
				return Optional.empty();
			}

			community.setCommunityPic(profileCommunityDto.getCommunityPic());

			return Optional.ofNullable(communityRepository.save(community));
		}).orElse(Optional.empty());

	}
	
	/**
	 * Método para editar a bio de uma comunidade caso o usuário seja dono dela.
	 * 
	 * @param ProfileCommunityDTO
	 * @return Um Optional contendo a comunidade alterada pelo usuário ou vázio para
	 *         ser tratado como erro.
	 */
	public Optional<?> editBio(ProfileCommunityDTO profileCommunityDto) {
		return communityRepository.findById(profileCommunityDto.getCommunityId()).map(community -> {

			Optional<Profile> profile = profileRepository.findById(profileCommunityDto.getProfileId());

			if (profile.isEmpty()
					|| !community.getCommunityOwner().getProfileEmail().equals(profile.get().getProfileEmail())) {
				return Optional.empty();
			}

			community.setCommunityBio(profileCommunityDto.getCommunityBio());

			return Optional.ofNullable(communityRepository.save(community));
		}).orElse(Optional.empty());
	}


}
