package org.baeldung.persistence.dao;


import org.baeldung.persistence.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
	public Role findByName(String name);
	//delete
}
