package com.oikos.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.oikos.models.Profile;

public class UserDetailImplementation implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private List<GrantedAuthority> autoridades;

	
	
	public UserDetailImplementation(Profile profile) {
		super();
		this.userName = profile.getProfileName();
		this.password = profile.getProfilePassword();
	}
	public UserDetailImplementation() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return autoridades;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
