package com.esprit.weBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.esprit.weBank.entities.Transaction;
import com.esprit.weBank.services.TransactionService;

@RestController
public class TransactionRestController {
	@Autowired
	private TransactionService transactionService;	
	
	@GetMapping(value ="/getTransactionByRibE/{ribE}")
	public List<Transaction> findTransactionByRibE(@PathVariable(value = "ribE") int ribE) {
		return transactionService.findTransactionByRibE(ribE);
	}
	
	@GetMapping(value ="/getAllTransactions")
	public List<Transaction> getAllTransactions() {
		return transactionService.findAllTransaction();
	}
}
