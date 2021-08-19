package com.oikos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikos.models.Business;
import com.oikos.models.dtos.ProfileBusinessDTO;
import com.oikos.repositories.BusinessRepository;
import com.oikos.repositories.ProfileRepository;

@Service
public class BusinessService {

	@Autowired
	BusinessService businessService;

	@Autowired
	BusinessRepository businessRepository;

	@Autowired
	ProfileRepository profileRepository;

	/**
	 * Método para criar uma página de negócios.
	 * 
	 * @param ProfileCommunityDTO
	 * @return Um Optional contendo o negócio criado ou vázio para ser tratado como
	 *         erro.
	 */
	public Optional<Business> createBusiness(ProfileBusinessDTO profileBusinessDto) {
		return profileRepository.findById(profileBusinessDto.getProfileId()).map(profile -> {

			Business business = new Business();
			business.setBusinessOwner(profile);
			business.setBusinessName(profileBusinessDto.getBusinessName());
			business.setBusinessAlias(profileBusinessDto.getBusinessAlias());
			business.setBusinessEmail(profileBusinessDto.getBusinessEmail());
			business.setBusinessBio(profileBusinessDto.getBusinessBio());
			business.setBusinessPic(profileBusinessDto.getBusinessPic());
			business.setBusinessAddress(profileBusinessDto.getBusinessAddress());
			business.setBusinessPhone(profileBusinessDto.getBusinessPhone());
			business.setBusinessHeader(profileBusinessDto.getBusinessHeader());
			business.setBusinessBackground(profileBusinessDto.getBusinessBackground());
			
			profile.getBusinessOwned().add(business);
			profileRepository.save(profile);

			return Optional.ofNullable(businessRepository.save(business));

		}).orElse(Optional.empty());
	}

}
