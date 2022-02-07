package com.digicare.digicare_rest_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digicare.digicare_rest_test.repository.*;
import com.digicare.digicare_rest_test.security.UserPrincipal;
import com.digicare.digicare_rest_test.model.user.User;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with this email: %s", email)));
		UserPrincipal up = new UserPrincipal(user);
		return up;
	}

}