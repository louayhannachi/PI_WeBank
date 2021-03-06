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
	
	//@RolesAllowed({"ROLE_ADMIN","ROLE_CLIENT"})
	@PostMapping(value = "/addInvestment")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> createInvestment(@RequestBody Investment investment) {
		if (investment.getAccount().getBalance() < investment.getInvestCost()) {	
			return new ResponseEntity<>("Insufficient balance !", HttpStatus.BAD_REQUEST);
		}
			return new ResponseEntity<>(investmentService.saveInvestment(investment), HttpStatus.OK);
	}

	//@RolesAllowed({"ROLE_ADMIN","ROLE_EMPLOYEE"})
	@RequestMapping(value = "/getAllInvestments", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Investment> findAllInvestments() {
		return investmentService.findAllInvestment();
	}
	
	//@RolesAllowed({"ROLE_CLIENT", "ROLE_ADMIN","ROLE_EMPLOYEE"})
	@RequestMapping(value = "/getAccInvestments/{account_number}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Investment> findAllInvestments(@PathVariable(value = "account_number") int account_number) {
		return investmentService.findAccInvestment(account_number);
	}
	
	//@RolesAllowed({"ROLE_ADMIN","ROLE_EMPLOYEE"})
	@PutMapping(value = "updateInvestsment/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Investment> updateInvestsment(@PathVariable(value = "id") Integer id,
			@RequestBody Investment investment) {
		return new ResponseEntity<>(investmentService.updateInvestment(investment, id),HttpStatus.OK);
	}

	//@RolesAllowed({"ROLE_ADMIN","ROLE_EMPLOYEE"})
	@DeleteMapping(value = "deleteInvestment/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteInvestment(@PathVariable(value = "id") Integer id) {
		investmentService.deleteInvestmentById(id);
		return new ResponseEntity<>("Deleted investment with id : " + id, HttpStatus.OK);
	}
	
	//@RolesAllowed({"ROLE_CLIENT", "ROLE_ADMIN","ROLE_EMPLOYEE"})
	@PostMapping(value = "/simulateInvestment")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> simulateInvestment(@RequestBody Investment investment) {
		return new ResponseEntity<>(investmentService.simulateInvestment(investment), HttpStatus.OK);
	}

}

