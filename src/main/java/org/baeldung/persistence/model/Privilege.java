package org.baeldung.persistence.model;

import java.util.Collection;

//import javax.persistence.ManyToMany;




import org.apache.log4j.Logger;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Privilege {
	static Logger log = Logger.getLogger(VerificationToken.class.getName());
	
	@Id private String id;
	
	private String name;
	
	//@ManyToMany(mappedBy = "privileges")
	private Collection<String> roles;
	
	public Privilege() {
		super();
	}
	
	public Privilege(final String name) {
		super();
		this.name = name;
	}
	
	public String getId() {
		return this.id;
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
	
	public Collection<String> getRoles() {
		return this.roles;
	}
	
	public void setRoles(final Collection<String> _roles) {
		this.roles = _roles;
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
        final Privilege privilege = (Privilege) obj;
        if (!privilege.equals(privilege.name)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Privilege [name=").append(name).append("]").append("[id=").append(id).append("]");
        return builder.toString();
    }
	
}
