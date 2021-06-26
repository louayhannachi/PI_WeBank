package com.esprit.weBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.esprit.weBank.entities.User;
import com.esprit.weBank.services.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;	
	
	@PostMapping(value = "/createUser")
    public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping(value ="/getAllUsers")
	public List<User> getAllUsers() {
		return userService.findAllUser();
	}
}
