package com.oikos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.oikos.models.Message;

public interface MessageRepository extends JpaRepository<Message, String> {

	public List<Message> findAllByContentContainingIgnoreCase(String messageContent);
	
	public List<Message> findAllByPlaceContainingIgnoreCase(String from);
}
