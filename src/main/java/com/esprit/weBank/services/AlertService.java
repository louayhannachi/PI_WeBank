package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Account;
import com.esprit.weBank.entities.Alert;
import com.esprit.weBank.repository.IAlertRepository;
import com.esprit.weBank.entities.Budget;
import com.esprit.weBank.entities.Transaction;
@Service
public class AlertService {
	
	@Autowired
	private IAlertRepository alertRepository;

	@Autowired
	private BudgetService budgetService;
	@Autowired
	private AccountService accountService;
	
	
	public Alert saveAlert(Alert alert, int id) {
		Account ac = accountService.findAccountById(id);
		if(ac.getBudget().getPlafond()>ac.getSolde())
		 	{
		 		return alertRepository.save(alert);
		 	}else
		 		return null;
	
	}
	
	public List<Alert> findAllAlert() {
		return (List<Alert>) alertRepository.findAll();
	}
	
	public Alert findAlertById(int id) {
		return alertRepository.findById(id).get();
	}

	public Alert updateAlert(Alert alert, int id) {
		Alert existingAlert = findAlertById(id);
		if (existingAlert != null) {
			existingAlert.setAlertDate(alert.getAlertDate());
			existingAlert.setAlertMsg(alert.getAlertMsg());
			return alertRepository.save(existingAlert);
		}		
		return null;
	}
	
	public void deleteAlertById(int id) {
		alertRepository.deleteById(id);
	}
	
}
