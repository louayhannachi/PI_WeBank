package com.esprit.weBank.security;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.esprit.weBank.services.UserService;
import com.esprit.weBank.util.UserRole;

import java.util.List;


@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${spring.security.user.password}")
	private String pwd;
	
	@Value("${spring.security.user.name}")
	private String defaultUser;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.esprit.weBank.entities.User user = new com.esprit.weBank.entities.User();
		if (!username.equals(defaultUser)) {
			user = userService.findByUserName(username);
		} else {
			user.setUsername(defaultUser);
			user.setPassword(passwordEncoder.encode(pwd));
			user.setRole(UserRole.ROLE_ADMIN);
		}
		 
		if (user != null) {
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
			return new User(user.getUsername(), user.getPassword(), authorities);
			
		} else {	
			throw new UsernameNotFoundException("User " + username + " does not exist !");
		}
	}
}
