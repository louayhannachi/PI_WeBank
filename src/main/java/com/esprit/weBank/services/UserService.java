package com.esprit.weBank.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.User;
import com.esprit.weBank.repository.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAllUser() {
		return (List<User>) userRepository.findAll();
	}
	
	public User findUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User findUserByName(String name) {
		return userRepository.findByFirstName(name).orElse(null);
	}
	
	public User findUserByCin(String cin) {
		return userRepository.findByCin(cin).orElse(null);
	}
	
	public User updateUser(User user) {
		User existingUser = userRepository.findByCin(user.getCin()).orElse(null);
		if (existingUser != null) {
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setBirthDate(user.getBirthDate());
			existingUser.setPhoneNumber(user.getPhoneNumber());
			existingUser.setEmail(user.getEmail());
			existingUser.setAddress(user.getAddress());
		}		
		return userRepository.save(existingUser);
	}
	
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

}
