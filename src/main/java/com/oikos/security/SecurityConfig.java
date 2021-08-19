package com.oikos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailServiceImplementation userDetailsServiceImplement;
	
	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.inMemoryAuthentication().withUser("boaz").password(passwordEncoder().encode("boaz")).authorities("ROLE_ADMIN");
 		auth.userDetailsService(userDetailsServiceImplement);
 	}

	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Todas as rotas liberadas por causa do heroku
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/**").permitAll()
		.antMatchers(HttpMethod.POST, "/profile/signup").permitAll()
		.antMatchers(HttpMethod.POST, "/profile/credentials").permitAll()
		.antMatchers(HttpMethod.POST, "/profile/signin").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}

}
