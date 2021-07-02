package com.oikos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oikos.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	public List<Message> findAllByMessageContentContainingIgnoreCase(String messageContent);

	public List<Message> findAllByMessageFromContaining(long messageFrom);

	//public List<Message> findAllByMessageWhereContainingIgnoreCase(String from);
	// só retirar esse metodo do comentario quando seu GetMappiing for feito, se não pode causar erros.

}
