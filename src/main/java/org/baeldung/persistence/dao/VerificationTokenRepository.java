package org.baeldung.persistence.dao;

import org.baeldung.persistence.model.User;
import org.baeldung.persistence.model.VerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VerificationTokenRepository extends
		MongoRepository<VerificationToken, String> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
