package org.baeldung.registration;

import java.util.Locale;

import org.baeldung.persistence.model.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

//@SuppressWarnings("serial")
//@Component
public class OnRegistrationCompleteEvent extends ApplicationEvent{
	
	//private final String appUrl;
	
	//private final Locale locale;
	
	//private final User user;
	
	private String appUrl;
	
	private Locale locale;
	
	private User user;
	
	public OnRegistrationCompleteEvent(final User _user, final Locale _locale, final String _appUrl) {
		super(_user);
		this.user = _user;
		this.locale = _locale;
		this.appUrl = _appUrl;
	}
	
	
	public String getAppUrl() {
		return this.appUrl;
	}
	
	/*optional*/
	public void setAppUrl(String _appUrl) {
		this.appUrl = _appUrl;
	}
	
	public Locale getLocale() {
		return this.locale;
	}
	
	/*optional*/
	public void setLocale(Locale _locale) {
		this.locale = _locale;
	}
	
	public User getUser() {
		return this.user;
	}
	
	/*optional*/
	public void setAppUrl(User _user) {
		this.user = _user;
	}
	
}
