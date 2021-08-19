package com.oikos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oikos.models.Business;
import com.oikos.models.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	Optional<Comment> findById(long id);
	
}