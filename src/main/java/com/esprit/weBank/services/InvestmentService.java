package com.esprit.weBank.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.weBank.entities.Investment;
import com.esprit.weBank.entities.User;
import com.esprit.weBank.repository.IInvestmentRepository;
import com.esprit.weBank.util.InvestmentType;

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
	
	public List<Investment> findAccInvestment(int account_number) {
		List<Investment> li = findAllInvestment()
				.stream().filter(e -> account_number == e.getAccount().getAccNumber())
				.collect(Collectors.toList()); 
		return li;
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
	
	public String simulateInvestment(Investment investment) {
        String recommendation;

		switch (investment.getInvestType().toString()) {
        case "Bonds": 
        	if (investment.getInvestCost() > 1000) {
        		recommendation = "Investment in Bonds with (" + investment.getInvestCost() + ")$ is risky";
        	} else {
        		recommendation = "Investment in Bonds with (" + investment.getInvestCost() + ")$ is safe";
        	}
        break;
        case "Stocks": 
        	if (investment.getInvestCost() > 10000) {
        		recommendation = "Investment in Stocks with (" + investment.getInvestCost() + ")$ is risky";
        	} else {
        		recommendation = "Investment in Stocks with (" + investment.getInvestCost() + ")$ is safe";
        	}
        break;
        case "MutualFunds": 
        	if (investment.getInvestCost() > 999) {
        		recommendation = "Investment in Mutual Funds with (" + investment.getInvestCost() + ")$ is risky";
        	} else {
        		recommendation = "Investment in Mutual Funds with (" + investment.getInvestCost() + ")$ is safe";
        	}
        break;
        case "RealEstate": 
        	if (investment.getInvestCost() > 50000) {
        		recommendation = "Investment in Real estate with (" + investment.getInvestCost() + ")$ is risky";
        	} else {
        		recommendation = "Investment in Real estate with (" + investment.getInvestCost() + ")$ is safe";
        	}
        break;
        case "Savings": 
        case "LifeInsurance":
        case "CertificateOfDeposit": 
        case "Foreign": 
            recommendation = "Investment is not risky";
        break;
        default: recommendation = "Investment with Medium risk";
        break;		
        }
		return recommendation;
	}
	
	public void deleteInvestmentByName(String name) {
		Investment inv = findInvestmentByInvestName(name);
		if (inv != null) {
			investmentRepository.deleteById(inv.getId());
		}
	}
}
