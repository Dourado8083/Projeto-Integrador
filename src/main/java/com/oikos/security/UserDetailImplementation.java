package com.oikos.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.oikos.models.Profile;

public class UserDetailImplementation implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private String profileEmail;
	private String profilePassword;
	private List<GrantedAuthority> authorities;

	
	
	public UserDetailImplementation(Profile profile) {
		super();
		this.profileEmail = profile.getProfileEmail();
		this.profilePassword = profile.getProfilePassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return profilePassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return profileEmail;
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
