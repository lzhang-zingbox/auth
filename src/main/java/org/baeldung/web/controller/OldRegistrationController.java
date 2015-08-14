package org.baeldung.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.baeldung.persistence.model.User;
import org.baeldung.persistence.model.VerificationToken;
import org.baeldung.persistence.service.IUserService;
import org.baeldung.persistence.service.UserDto;
import org.baeldung.registration.OnRegistrationCompleteEvent;
import org.baeldung.validation.EmailExistsException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Controller
//@RequestMapping(value = "/old")
public class OldRegistrationController {
	
	
	static Logger log = Logger.getLogger(OldRegistrationController.class.getName());
	
	static private boolean DEBUG = true;
	
	@Autowired
	private IUserService userService;
	
	/*
	@Autowired 
	private MessageSource message;
	
	@Autowired
	private JavaMailSender mailSender; //change to spring mail later
	*/
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	/*
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public ModelAndView greetingPage() {
		return new ModelAndView("greeting");
	}
	*/
	
    @RequestMapping(value = "/user/welcome", method = RequestMethod.GET)
    public String showRegistrationForm(final HttpServletRequest request, final Model model) {
        
        final UserDto accountDto = new UserDto();
        model.addAttribute("user", accountDto);
        if(DEBUG){
			System.out.println("Rendering registration page.");
		}
        return "welcome";
    }
	
    @RequestMapping(value = "/user/welcome2", method = RequestMethod.GET)
    public ModelAndView showRegistrationForm2(final HttpServletRequest request, final Model model) {
        
        final UserDto accountDto = new UserDto();
        model.addAttribute("user", accountDto);
        if(DEBUG){
			System.out.println("Rendering registration page.");
		}
        return new ModelAndView("welcome");
    }
	
    
	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid final UserDto userDto,
			final HttpServletRequest request, final Errors errors) {
		
		if(DEBUG){
			System.out.println("Registering user account with information : " + userDto.toString());
		}
		
		final User registered = createUserAccount(userDto);
		
		if (registered == null) {
			return new ModelAndView("registration", "user", userDto);
		}
		
		try {
			final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, 
					request.getLocale(), appUrl));
			
		} catch (final Exception ex) {
			log.warn("Unable to register user", ex);
			return new ModelAndView("emailError", "user", userDto);
		}
		return new ModelAndView("SuccessRegister", "user", userDto);
	}
	
	private User createUserAccount(final UserDto accountDto) {
		User registered = null;
		
		try {
			registered = userService.registerNewUserAccount(accountDto);
		} catch (EmailExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return registered;
		
	}

}
