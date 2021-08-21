package com.oikos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oikos.models.Business;
import com.oikos.models.Threads;

@Repository
public interface ThreadsRepository extends JpaRepository<Threads, Long>{
	
	Optional<Threads> findById(long id);
	
}