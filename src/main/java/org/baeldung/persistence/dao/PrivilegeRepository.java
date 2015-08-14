package org.baeldung.persistence.dao;

import org.baeldung.persistence.model.Privilege;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrivilegeRepository extends MongoRepository<Privilege, String> {
	
	Privilege findByName(String name);
	//delete
	
}
