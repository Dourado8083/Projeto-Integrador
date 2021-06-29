package com.oikos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oikos.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	public List<Message> findAllByMessageContentContainingIgnoreCase(String messageContent);
	public List<Message> findAllByMessageFromContaining(long messageFrom);
	
<<<<<<< HEAD
	public List<Message> findAllByPlaceContainingIgnoreCase(String from);
	
}
=======
}
>>>>>>> c976d4cf476c96d15fc5043c81ed8a3c3bb9e54a
