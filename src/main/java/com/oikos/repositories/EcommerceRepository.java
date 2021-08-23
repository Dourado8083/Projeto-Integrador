package com.oikos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oikos.models.Business;
import com.oikos.models.Ecommerce;


@Repository
public interface EcommerceRepository extends JpaRepository<Ecommerce, Long>{
	
	Optional<Ecommerce> findById(long id);
	
}