package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.weBank.entities.Account;
import com.esprit.weBank.entities.Budget;
import com.esprit.weBank.repository.IBudgetRepository;

@Service
public class BudgetService {
	@Autowired
	private IBudgetRepository budgetRepository;

	public Budget saveBudget(Budget budget) {
		return budgetRepository.save(budget);
	}
	
	public List<Budget> findAllBudget() {
		return (List<Budget>) budgetRepository.findAll();
	}
	
	public Budget findBudgetById(int id) {
		return budgetRepository.findById(id).get();
	}
	
	public Budget updateBudget(Budget budget, int id) {
		Budget existingBudget = findBudgetById(id);
		if (existingBudget != null) {
			existingBudget.setLimitDate(budget.getLimitDate());
			existingBudget.setPlafond(budget.getPlafond());
			return budgetRepository.save(existingBudget);
		}		
		return null;
	}
	
	public void deleteBudgetById(int id) {
		budgetRepository.deleteById(id);
	}

}
