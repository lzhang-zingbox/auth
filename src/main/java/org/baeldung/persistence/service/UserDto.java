package org.baeldung.persistence.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.baeldung.validation.ValidEmail;
import org.baeldung.validation.ValidPassword;


public class UserDto {
	
	@NotNull
	@Size(min=1)
	private String firstname;
	
	@NotNull
	@Size(min=1)
	private String lastname;
	
	@ValidPassword
	private String password;
	
	@NotNull
	@Size(min=1)
	private String matchingPassword;
	
	@ValidEmail
	@NotNull
	@Size(min=1)
	private String email;
	
	
	
	public String getFirstName() {
		return this.firstname;
	}
	
	public void setFirstName(final String _firstname) {
		this.firstname = _firstname;
	}
	
	public String getLastName() {
		return this.lastname;
	}


	public void setLastName(final String _lastname) {
		this.lastname = _lastname;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(final String _password) {
		this.password = _password;
	}
	
	public String getMatchingPassword() {
		return this.matchingPassword;
	}
	
	public void setMatchingPassword(final String _matchingPassword) {
		this.matchingPassword = _matchingPassword;
	}
	
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(final String _email) {
		this.email = _email;
	}
	
	private Integer role;
	
	public Integer getRole() {
		return this.role;
	}
	
	public void setRole(final Integer role) {
		this.role = role;
	}
	
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [firstName=").append(firstname).append("]").
        	append("[lastName=").append(lastname).append("]").append("[email").append(email).append("]").append("[password").append(password).append("]");
        return builder.toString();
    }
}
