package com.example.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new  UserDetailServiceImp();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider ;
	}

	//configure method ..
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {

	        registry.addResourceHandler("/resources/**")
	                .addResourceLocations("/resources/")
	                .setCachePeriod(31556926);

	    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/**").permitAll().and().formLogin()
		.loginPage("/signin");

		/*
		 * .loginProcessingUrl("/dologin") .defaultSuccessUrl("/farmer/")
		 * .failureForwardUrl("/")
		 * .loginPage("/signin")
		 * .authenticated().anyRequest()
		 * .antMatchers("/farmer/**").hasRole("farmer")
		 * .defaultSuccessUrl("/farmer/test")
		 * .antMatchers("/farmer/**").hasRole("farmer")
			.antMatchers("/buyer/**").hasRole("buyer")
		 * 
		 */
	}
	
}
