package org.baeldung.persistence.model;


import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Role {
	
	static Logger log = Logger.getLogger(VerificationToken.class.getName());
	
	@Id 
	private String id;
	
	//@ManyToMany(mappedBy = "roles")
	private Collection<String> users;
	
	/*@ManyToMany(mappedBy = "roles")
	@JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") ,
			inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id") )*/
	
	private Collection<String> privileges;
	
	private String name;
	
	public Role() {
		super();
	}
	
	public Role(final String name) {
		super();
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(final String _id) {
		this.id = _id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(final String _name) {
		this.name = _name;
	}
	
	public Collection<String> getUsers() {
		return users;
	}
	
	public void setUsers(final Collection<String> _users) {
		this.users = _users;
	}
	
	public Collection<String> getPrivileges() {
		return this.privileges;
	}
	
	public void setPrivileges(final Collection<String> _privileges) {
		this.privileges = _privileges;
	}
	
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        final Role role = (Role) obj;
        if (!role.equals(role.name)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Role [name=").append(name).append("]").append("[id=").append(id).append("]");
        return builder.toString();
    }
	
}
