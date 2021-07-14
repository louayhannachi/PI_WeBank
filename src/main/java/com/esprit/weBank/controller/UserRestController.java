package com.esprit.weBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esprit.weBank.entities.User;
import com.esprit.weBank.services.UserService;
import com.esprit.weBank.util.UserRole;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@PutMapping(value = "/createEmployee")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User temp = userService.findUserByCin(user.getCin());
		if (temp != null) {
			return new ResponseEntity<>("Cin already exist !", HttpStatus.BAD_REQUEST);
		}
		User temp2 = userService.findByUserName(user.getUsername());
		if (temp2 != null) {
			return new ResponseEntity<>("UserName already exist !", HttpStatus.BAD_REQUEST);
		}
		user.setRole(UserRole.ROLE_EMPLOYEE);
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
	}

	@PutMapping(value = "/createClient")
	public ResponseEntity<Object> createClient(@RequestBody User user) {
		User temp = userService.findUserByCin(user.getCin());
		if (temp != null) {
			return new ResponseEntity<>("Cin already exist !", HttpStatus.BAD_REQUEST);
		}
		User temp2 = userService.findByUserName(user.getUsername());
		if (temp2 != null) {
			return new ResponseEntity<>("UserName already exist !", HttpStatus.BAD_REQUEST);
		}
		user.setRole(UserRole.ROLE_CLIENT);
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/createAdmin")
	public ResponseEntity<Object> createAdmin(@RequestBody User user) {
		User temp = userService.findUserByCin(user.getCin());
		if (temp != null) {
			return new ResponseEntity<>("Cin already exist !", HttpStatus.BAD_REQUEST);
		}
		User temp2 = userService.findByUserName(user.getUsername());
		if (temp2 != null) {
			return new ResponseEntity<>("UserName already exist !", HttpStatus.BAD_REQUEST);
		}
		user.setRole(UserRole.ROLE_ADMIN);
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
	}

	@PostMapping(value = "/updateUser/{cin}")
	public ResponseEntity<Object> updateUser(@PathVariable(value = "cin") String cin, @RequestBody User user) {
		User temp = userService.findUserByCin(user.getCin());
		if (temp != null) {
			return new ResponseEntity<>("Cin already exist !", HttpStatus.BAD_REQUEST);
		}
		User temp2 = userService.findByUserName(user.getUsername());
		if (temp2 != null) {
			return new ResponseEntity<>("UserName already exist !", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userService.updateUser(user, cin), HttpStatus.OK);
	}

	@DeleteMapping(value = "/deleteUser/{cin}")
	public void deleteUser(@PathVariable(value = "cin") String cin) {
		userService.deleteUserByCin(cin);
	}

	@GetMapping(value = "/getUserByName/{name}")
	public User getUserByName(@PathVariable(value = "name") String name) {
		return userService.findUserByName(name);
	}

	@GetMapping(value = "/getUserByCin/{cin}")
	public User getUserByCin(@PathVariable(value = "cin") String cin) {
		return userService.findUserByCin(cin);
	}

	@GetMapping(value = "/getAllUsers")
	public List<User> getAllUsers() {
		return userService.findAllUser();
	}
	
	@GetMapping(value ="/getUserByid/{id}")
	public User getUserByCin(@PathVariable(value = "id") int id) {
		return userService.findUserById(id);
	}
}
