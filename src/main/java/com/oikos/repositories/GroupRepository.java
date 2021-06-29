package com.oikos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.oikos.models.Group;

public interface GroupRepository extends JpaRepository<Group, String>{

	public List<Group> findAllByNameContainingIgnoreCase (String groupName);
	
}