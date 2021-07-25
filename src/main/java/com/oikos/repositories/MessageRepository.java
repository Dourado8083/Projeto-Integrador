package com.oikos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oikos.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	public List<Message> findAllByMessageContentContainingIgnoreCase(String messageContent);

}
