package com.oikos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikos.models.Business;
import com.oikos.models.Ecommerce;
import com.oikos.models.dtos.EcommerceDTO;
import com.oikos.repositories.BusinessRepository;
import com.oikos.repositories.EcommerceRepository;
import com.oikos.repositories.ProfileRepository;

@Service
public class EcommerceService {
	
	@Autowired
	EcommerceRepository ecommerceRepository;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	BusinessRepository businessRepository;
	
	
	/**
	 * Método para criar um ecommerce
	 * 
	 * @param EcommerceDTO
	 * @return Um Optional contendo o ecommerce criado ou vázio para ser tratado como
	 *         erro.
	 */
	public Optional<?> createEcommerce(EcommerceDTO ecommerceDto) {
		return profileRepository.findById(ecommerceDto.getBusinessOwnerId()).map(profile -> {

			Optional<Business> business = businessRepository.findById(ecommerceDto.getBusinessOnId());
			
			if(business.isEmpty() || business.get().getBusinessOwner().getProfileId() != profile.getProfileId()) {
				return Optional.empty();
			}
			
			Ecommerce ecommerce = new Ecommerce();
			ecommerce.setEcommercePic(ecommerceDto.getEcommercePic());
			ecommerce.setBusinessOn(business.get());
			
			business.get().setEcommerce(ecommerce);
			
			ecommerceRepository.save(ecommerce);
			businessRepository.save(business.get());
			
			return Optional.ofNullable(ecommerce);

		}).orElse(Optional.empty());
	}
	
}
