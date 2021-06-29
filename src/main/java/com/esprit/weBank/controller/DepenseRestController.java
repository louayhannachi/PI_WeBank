package com.esprit.weBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.weBank.entities.Depense;
import com.esprit.weBank.services.DepenseService;

@RestController
public class DepenseRestController {
	@Autowired
	private DepenseService depenseService;	
	
	@PutMapping(value = "/createDepense")
    public Depense createDepense(@RequestBody Depense depense) {
		return depenseService.saveDepense(depense);
	}
	
	@PostMapping(value ="/updateDepense/{id}")
	public Depense updateDepense(@PathVariable(value = "id") int id, @RequestBody Depense depense) {
		return depenseService.updateDepense(depense, id);
	}
	
	@DeleteMapping(value ="/deleteDepense/{id}")
	public void deleteDepense(@PathVariable(value = "id") int id) {
		depenseService.deleteDepenseById(id);
	}
	
	
	@GetMapping(value ="/getDepenseById/{id}")
	public Depense getUserByCin(@PathVariable(value = "id") int id) {
		return depenseService.findDepenseById(id);
	}
	
	@GetMapping(value ="/getAllDepense")
	public List<Depense> getAllDepense() {
		return depenseService.findAllDepense();
	}
}
