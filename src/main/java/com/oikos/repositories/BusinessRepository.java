package com.oikos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oikos.models.Business;


@Repository
public interface BusinessRepository extends JpaRepository<Business, Long>{
	
	Optional<Business> findById(long id);
	
}