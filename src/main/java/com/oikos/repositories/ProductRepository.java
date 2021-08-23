package com.oikos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oikos.models.Business;
import com.oikos.models.Ecommerce;
import com.oikos.models.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findById(long id);
	
}