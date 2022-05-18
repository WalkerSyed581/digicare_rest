package com.digicare.digicare_rest_test.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicare.digicare_rest_test.security.JwtTokenProvider;
import com.digicare.digicare_rest_test.service.JwtUserDetailsService;
import com.digicare.digicare_rest_test.model.JwtRequest;
import com.digicare.digicare_rest_test.model.JwtResponse;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtInMemoryUserDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody JwtRequest loginRequest) {
		System.out.println(loginRequest.getEmail()+ loginRequest.getPassword());
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtTokenUtil.generateToken(authentication);
		return ResponseEntity.ok(new JwtResponse(jwt));
	}
}
