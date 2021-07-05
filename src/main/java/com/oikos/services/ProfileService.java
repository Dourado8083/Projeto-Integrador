package com.oikos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.oikos.models.Community;
import com.oikos.repositories.CommunityRepository;
import com.oikos.repositories.ProfileRepository;

public class ProfileService {
	
	private @Autowired CommunityRepository repositoryC;
	private @Autowired ProfileRepository repositoryP;
	
	
	
	public Optional<Community> criarGrupo(Long profileId, Community grupoParaSerCriado) {
		return repositoryP.findById(profileId).map(usuarioExistente -> {
			grupoParaSerCriado.setCommunityOwner(usuarioExistente);
			return Optional.ofNullable(repositoryC.save(grupoParaSerCriado));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
	
}
