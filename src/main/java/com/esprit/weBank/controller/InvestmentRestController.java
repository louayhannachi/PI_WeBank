package com.esprit.weBank.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.weBank.entities.Investment;
import com.esprit.weBank.repository.IInvestmentRepository;
import com.esprit.weBank.services.InvestmentService;



@RestController
@RequestMapping(value = "/investment", method = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })
public class InvestmentRestController {

	@Autowired
	InvestmentService investmentService;
	@Autowired
	IInvestmentRepository investmentRepository;
	
	@PostMapping(value = "/addInvestment")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Investment> createReclamation(@RequestBody Investment investment) {

		return new ResponseEntity<>(investmentService.saveInvestment(investment), HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllInvestments", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Investment> findAllReclamation() {
		return investmentService.findAllInvestment();
	}
	
	@PutMapping(value = "updateInvestsment/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Investment> updateInvestsment(@PathVariable(value = "id") Integer id,
			@RequestBody Investment investment) {
		return new ResponseEntity<>(investmentService.updateInvestment(investment, id),HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteInvestment/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteInvestment(@PathVariable(value = "id") Integer id) {
		investmentService.deleteInvestmentById(id);
		return new ResponseEntity<>("Deleted investment with id : " + id, HttpStatus.OK);
	}

}

