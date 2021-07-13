package com.esprit.weBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.weBank.entities.Alert;
import com.esprit.weBank.models.AuthenticationRequest;
import com.esprit.weBank.models.AuthenticationResponse;
import com.esprit.weBank.security.AppUserDetailsService;
import com.esprit.weBank.util.Jwtutil;

@RestController
public class AuthenticationRestController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AppUserDetailsService appUserDetailsService;

	@Autowired
	private Jwtutil jwtUtil;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			if (authentication.isAuthenticated()) {
				final UserDetails userDetails = appUserDetailsService
						.loadUserByUsername(authenticationRequest.getUsername());
				final String jwt = jwtUtil.generateToken(userDetails);
				return ResponseEntity.ok(new AuthenticationResponse(jwt));
			}
		} catch (BadCredentialsException e) {
			return new ResponseEntity("Incorrect username or password", HttpStatus.UNAUTHORIZED);
		}
		return null;
	}

}
