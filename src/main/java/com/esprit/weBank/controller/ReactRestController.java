package com.esprit.weBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.weBank.entities.React;
import com.esprit.weBank.services.ReactService;

@RestController
public class ReactRestController {
	
	@Autowired
	private ReactService reactService;
	
	@PutMapping(value = "/createReact")
    public React createReact(@RequestBody React react) {
		return reactService.saveReact(react);
	}
	
	@GetMapping(value = "/getAllReacts")
	public List<React> getAllReacts() {
		return reactService.findAllReact();
	}
	
	@DeleteMapping(value ="/deleteReact/{id}")
	public void deleteComment(@PathVariable(value = "id") int id) {
		reactService.deleteReactById(id);
	}

}
