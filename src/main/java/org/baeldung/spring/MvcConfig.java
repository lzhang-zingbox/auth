package org.baeldung.spring;

import java.util.Locale;

import org.baeldung.validation.EmailValidator;
import org.baeldung.validation.PasswordMatchesValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = { "org.baeldung.web" })
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter{
	
	public MvcConfig() {
		super();
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		super.addViewControllers(registry);
		registry.addViewController("/welcome").setViewName("welcome");;
		registry.addViewController("/emailError.html");
		registry.addViewController("/successRegister.html");
		registry.addViewController("/greeting.html");
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/", "/resources/");
	}
	/*
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		 final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	     localeChangeInterceptor.setParamName("lang");
	     registry.addInterceptor(localeChangeInterceptor);
	}
	*/
	@Bean
	public ViewResolver viewResolver() {
		
		final InternalResourceViewResolver bean = new InternalResourceViewResolver();
		//bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/view/");
		bean.setSuffix(".jsp");
		return bean;
	}
	
	
	@Bean
	public LocaleResolver localeResolver() {
		
		final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
		return cookieLocaleResolver;
	}
	
	@Bean
	public MessageSource messageSource() {
		
		final ReloadableResourceBundleMessageSource messageSource = 
				new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);
        return messageSource;
		
	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder(11);
	}
	
	@Bean
	public EmailValidator usernameValidator() {
		return new EmailValidator();
	}
	
    @Bean
    public PasswordMatchesValidator passwordMatchesValidator() {
        return new PasswordMatchesValidator();
    }
}
