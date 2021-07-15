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
import com.esprit.weBank.entities.Credit;
import com.esprit.weBank.services.CreditService;

@RestController

public class CreditRestController {
	@Autowired
	private CreditService creditService;

	@PutMapping(value = "/createCredit")
	public double createCredit(@RequestBody Credit credit) {
		return creditService.addCredit(credit);
		 
	}

	@GetMapping(value = "/getAllCredits")
	public List<Credit> getAllCredits() {
		return creditService.findAllCredit();
	}

	@DeleteMapping(value = "/deleteCredit/{id}")
	public void deleteCredit(@PathVariable(value = "id") int id) {
		creditService.deleteCreditById(id);
	}

	@GetMapping(value = "/getCreditsById/{id}")
	public Credit getCreditById(@PathVariable(value = "id") int id) {
		return creditService.findCreditById(id);
	}

	@PostMapping(value = "/updateCredit/{id}")
	public Credit updateCredit(@PathVariable(value = "id") int id, @RequestBody Credit Credit) {
		return creditService.updateCredit(Credit, id);
	}

	@GetMapping(value = "/calculeSommeMontant/{id}")
	public int calculeSommeMontant(@PathVariable(value = "id") int id) {
		return creditService.calculeSommeMontant(id);
	}
	
	@GetMapping(value = "/getCreditByDuree/{date}")
	public List<Credit> getCreditByDuree(@PathVariable(value = "date") int date) {
		return creditService.getCreditByDuree(date);
	}
}
