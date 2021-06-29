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
import com.esprit.weBank.entities.Budget;
import com.esprit.weBank.services.BudgetService;

@RestController
public class BudgetRestController {
	
	@Autowired
	private BudgetService budgetService;	
	
	@PutMapping(value = "/createBudget")
    public Budget createBudget(@RequestBody Budget budget) {
		return budgetService.saveBudget(budget);
	}
	
	@PostMapping(value ="/updateBudget/{id}")
	public Budget updateBudget(@PathVariable(value = "id") int id, @RequestBody Budget budget) {
		return budgetService.updateBudget(budget, id);
	}
	
	@DeleteMapping(value ="/deleteBudget/{id}")
	public void deleteBudget(@PathVariable(value = "id") int id) {
		budgetService.deleteBudgetById(id);
	}
	
	
	@GetMapping(value ="/getBudgetById/{id}")
	public Budget getUserByCin(@PathVariable(value = "id") int id) {
		return budgetService.findBudgetById(id);
	}
	
	@GetMapping(value ="/getAllBudget")
	public List<Budget> getAllBudget() {
		return budgetService.findAllBudget();
	}

}
