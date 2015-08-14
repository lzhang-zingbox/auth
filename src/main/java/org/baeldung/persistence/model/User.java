package org.baeldung.persistence.model;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document

public class User {
	
	static Logger log = Logger.getLogger(VerificationToken.class.getName());
	
	@Id private String id;
	
	private String firstName;
	
	private String lastName;
	
	@Indexed(name = "emailidx") private String email;
	
	private String password;
	
	private boolean enabled;
	
	private boolean tokenExpired;
	
	private Collection<String> roles;
	
	public User() {
		super();
		this.enabled = false;
		this.tokenExpired = false;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(final String _id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(final String _firstName) {
		this.firstName = _firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(final String _lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(final String _email) {
		this.email = _email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(final String _password) {
		this.password = _password;
	}
	
	public Collection<String> getRoles() {
		return this.roles;
	}
	
	public void setRoles(final Collection<String> _roles) {
		this.roles = _roles;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isTokenExpired() {
		return tokenExpired;
	}
	
	public void setTokenExpired(final boolean _expired) {
		this.tokenExpired = _expired;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
	}
	
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;
        if (!email.equals(user.email)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [firstName=").append(firstName).append("]").append("[lastName=").append(lastName).append("]").append("[username").append(email).append("]");
        return builder.toString();
    }
	
}
