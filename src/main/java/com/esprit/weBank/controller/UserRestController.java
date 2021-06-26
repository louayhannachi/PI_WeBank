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
	
	@PutMapping(value = "/createUser")
    public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PostMapping(value ="/updateUser/{cin}")
	public User updateUser(@PathVariable(value = "cin") String cin, @RequestBody User user) {
		return userService.updateUser(user, cin);
	}
	
	@DeleteMapping(value ="/deleteUser/{cin}")
	public void deleteUser(@PathVariable(value = "cin") String cin) {
		userService.deleteUserByCin(cin);
	}
	
	@GetMapping(value ="/getUserByName/{name}")
	public User getUserByName(@PathVariable(value = "name") String name) {
		return userService.findUserByName(name);
	}
	
	@GetMapping(value ="/getUserByCin/{cin}")
	public User getUserByCin(@PathVariable(value = "cin") String cin) {
		return userService.findUserByCin(cin);
	}
	
	@GetMapping(value ="/getAllUsers")
	public List<User> getAllUsers() {
		return userService.findAllUser();
	}
}
