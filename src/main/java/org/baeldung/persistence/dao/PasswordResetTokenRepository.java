package org.baeldung.persistence.dao;

import org.baeldung.persistence.model.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PasswordResetTokenRepository extends
		MongoRepository<PasswordResetToken, String> {

}
