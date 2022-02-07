package com.digicare.digicare_rest_test.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.digicare.digicare_rest_test.model.user.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {


	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String username;
	
	private String password;
    private boolean active;
	
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(User user) {
	    this.username = user.getEmail();
	    this.password = user.getPassword();
	    this.active = user.isActive();
	    this.authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return authorities;
	}
	
	@Override
	public String getPassword() {
	    return password;
	}
	
	@Override
	public String getUsername() {
	    return username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
	    return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
	    return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
	    return true;
	}
	
	@Override
	public boolean isEnabled() {
	    return active;
	}
}