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
import com.esprit.weBank.entities.Transaction;
import com.esprit.weBank.entities.User;
import com.esprit.weBank.services.TransactionService;

@RestController
public class TransactionRestController {
	@Autowired
	private TransactionService transactionService;	

	@PutMapping(value = "/createTransaction")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
		return transactionService.addTransaction(transaction);
	}
	@GetMapping(value ="/getAllTransactions")
	public List<Transaction> getAllTransactions() {
		return transactionService.findAllTransaction();
	}	
	

	@DeleteMapping(value ="/deleteTransaction/{id}")
	public void deleteTransaction(@PathVariable(value = "id") int id) {
		transactionService.deleteTransactionById(id);
	}
	@GetMapping(value ="/getTransactionByRibE/{ribE}")
	public List<Transaction> findTransactionByRibE(@PathVariable(value = "ribE") int ribE) {
		return transactionService.findTransactionByRibE(ribE);
	}
	
	@GetMapping(value ="/getTransactionsById/{id}")
	public Transaction getTransactionById(@PathVariable(value = "id") int id) {
		return transactionService.findTransactionById(id);
}
	
	@PostMapping(value ="/updateTransaction/{id}")
	public Transaction updateTransaction(@PathVariable(value = "id") int id, @RequestBody Transaction transaction) {
		return transactionService.updateTransaction(transaction, id);
	}
}