package org.baeldung.persistence.model;

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.apache.log4j.Logger;

@Document
//@CompoundIndex(name="VTindex", def = "{user:1}", unique = false)
public class VerificationToken {
	
    static Logger log = Logger.getLogger(VerificationToken.class.getName());
	private static final int EXPIRATION = 60*24;
	
	@Id private String id;
	
	private String token;
	
	/*private String username*/
	/*private String email*/
	
	private User user;
	
	private Date expiryDate;
	
	public VerificationToken() {
		super();
	}
	
	public VerificationToken(final String token,  final User user) {
        super();

        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String _id) {
		this.id = _id;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(final String token) {
		this.token = token;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(final User _user) {
		this.user = _user;
	}
	
	public Date getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(final Date _expiryDate) {
		this.expiryDate = _expiryDate;
	}
	
	private Date calculateExpiryDate(final int expiryTimeInMinutes) {
		final Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime());
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}
	
	public void updateToken(final String _token) {
		this.token = _token;
		this.expiryDate = calculateExpiryDate(EXPIRATION);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		
		final VerificationToken other = (VerificationToken) obj;
		
		if (expiryDate == null) {
			if(other.expiryDate != null) {
				return false;
			}
		} else if (!expiryDate.equals(other.expiryDate)) {
			return false;
		}
		
		if (this.token == null) {
			if (other.token != null){
				return false;
			}
		} else if (!token.equals(other.token)) {
			return false;
		}
		
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Token [String = ").append(token).append("]").
			append("[ Expires").append(expiryDate).append(" ]");
		return builder.toString();
	}
}
