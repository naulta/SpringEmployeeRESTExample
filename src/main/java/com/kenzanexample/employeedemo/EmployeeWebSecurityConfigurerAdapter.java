package com.kenzanexample.employeedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
public class EmployeeWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("abc123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("andy").password("abc123").roles("USER");
	}

	// Add security configuration to require authorization for DELETE method
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.httpBasic().and()
		.authorizeRequests()
		.antMatchers(HttpMethod.DELETE, "/employee/**").hasRole("ADMIN").and()
		.csrf().disable();

		// add this line to use H2 web console while using web security
		http.headers().frameOptions().disable();
	}

	//TODO: Replace depreciated PasswordEncoder
	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

}
