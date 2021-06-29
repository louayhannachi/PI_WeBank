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

import com.esprit.weBank.entities.Rapport;
import com.esprit.weBank.services.RapportService;

@RestController
public class RapportRestController {
	@Autowired
	private RapportService rapportService;
	
	
	@PutMapping(value = "/createRapport")
    public Rapport createRapport(@RequestBody Rapport rapport) {
		return rapportService.saveRapport(rapport);
	}
	
	@PostMapping(value ="/updateRapport/{id}")
	public Rapport updateRapport(@PathVariable(value = "id") int id, @RequestBody Rapport rapport) {
		return rapportService.updateRapport(rapport, id);
	}
	
	@DeleteMapping(value ="/deleteRapport/{id}")
	public void deleteRapport(@PathVariable(value = "id") int id) {
		rapportService.deleteRapportById(id);
	}
	
	@GetMapping(value ="/getRapportById/{id}")
	public Rapport getRapportByCin(@PathVariable(value = "id") int id) {
		return rapportService.findRapportById(id);
	}
	
	@GetMapping(value ="/getAllRapports")
	public List<Rapport> getAllRapports() {
		return rapportService.findAllRapport();
	}
}
