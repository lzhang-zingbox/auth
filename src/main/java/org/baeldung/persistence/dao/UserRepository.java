package org.baeldung.persistence.dao;

import org.baeldung.persistence.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends MongoRepository<User, String> {
	public User findByEmail(String email);
	
	public User deleteByEmail(String email);
	
}
