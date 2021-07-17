package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.weBank.entities.Investment;
import com.esprit.weBank.entities.User;
import com.esprit.weBank.repository.IInvestmentRepository;

@Service
public class InvestmentService {
	@Autowired
	private IInvestmentRepository investmentRepository;

	public Investment saveInvestment(Investment investment) {
		return investmentRepository.save(investment);
	}

	public List<Investment> findAllInvestment() {
		return (List<Investment>) investmentRepository.findAll();
	}
	
	public Investment findInvestmentById(int id) {
		return investmentRepository.findById(id).get();
	}
	
	public Investment findInvestmentByInvestName(String investName) {
		return investmentRepository.findByInvestName(investName).orElse(null);
	}
	
	public Investment updateInvestment(Investment investment, int id) {
		Investment existingInvestment = findInvestmentById(id);
		if (existingInvestment != null) {
			existingInvestment.setInvestCost(investment.getInvestCost());
			existingInvestment.setRoi(investment.getRoi());
			return investmentRepository.save(existingInvestment);
		}		
		return null;
	}
	
	public void deleteInvestmentById(int id) {
		investmentRepository.deleteById(id);
	}
	
	public void deleteInvestmentByName(String name) {
		Investment inv = findInvestmentByInvestName(name);
		if (inv != null) {
			investmentRepository.deleteById(inv.getId());
		}
	}
}
