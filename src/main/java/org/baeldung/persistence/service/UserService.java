package org.baeldung.persistence.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.baeldung.persistence.dao.PasswordResetTokenRepository;
import org.baeldung.persistence.dao.RoleRepository;
import org.baeldung.persistence.dao.UserRepository;
import org.baeldung.persistence.dao.VerificationTokenRepository;
import org.baeldung.persistence.model.PasswordResetToken;
import org.baeldung.persistence.model.User;
import org.baeldung.persistence.model.VerificationToken;
import org.baeldung.validation.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository tokenRepository;
	
	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;
	
	//@Autowired
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	//Method
	
	@Override
	public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException{
		
		if(emailExist(accountDto.getEmail())) {
			throw new EmailExistsException("There is an account with that email address: "
					+ accountDto.getEmail());
		}
		
		final User user = new User();
		
		user.setFirstName(accountDto.getFirstName());
		user.setLastName(accountDto.getLastName());
		user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		user.setEmail(accountDto.getEmail());

		ArrayList<String> ls=new ArrayList<String>();
		ls.add(roleRepository.findByName("ROLE_USER").getId());
		
		user.setRoles(ls);
		
		return userRepository.save(user);
	}

	@Override
	public User getUser(String verificationToken) {
		final User user = tokenRepository.findByToken(verificationToken).getUser();
		return user;
	}

	@Override
	public void saveRegisteredUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.deleteByEmail(user.getEmail());
	}

	@Override
	public void createVerificationTokenForUser(User user, String token) {
		
		final VerificationToken myToken = new VerificationToken(token, user);
		tokenRepository.save(myToken);
	}

	@Override
	public VerificationToken getVerificationToken(String Verification) {
		
		return tokenRepository.findByToken(Verification);
	}

	@Override
	public VerificationToken generateNewVerificationToken(String existingVerificationToken) {
		VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
		vToken.updateToken(UUID.randomUUID().toString());
		vToken = tokenRepository.save(vToken);	
		// need delete previous token ?
		return vToken;
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		/*
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
		*/
       
	}

	@Override
	public User findUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	@Override
	public User getUserByPasswordResetToken(String token) {
		//return passwordTokenRepository.findByToken(token);
		return null;
	}

	@Override
	public User getUserByID(String id) {
		
		return userRepository.findOne(id);
	}

	@Override
	public void changeUserPassword(User user, String password) {
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
	}

	@Override
	public boolean checkIfValidOldPassword(User user, String oldPassword) {
		return passwordEncoder.matches(oldPassword, user.getPassword());
	}

	private boolean emailExist(final String email) {
		final User user = userRepository.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}

	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
