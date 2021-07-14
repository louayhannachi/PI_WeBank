package com.esprit.weBank.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.esprit.weBank.entities.Transaction;
import com.esprit.weBank.services.TransactionService;

@EnableScheduling
@RestController
public class TransactionRestController {
	@Autowired
	private TransactionService transactionService;

	@PutMapping(value = "/createTransaction")
	public void createTransaction(@RequestBody Transaction transaction) {
		transactionService.addTransaction(transaction);
	}

	@GetMapping(value = "/getAllTransactions")
	public List<Transaction> getAllTransactions() {
		return transactionService.findAllTransaction();
	}

	@GetMapping(value = "/getLastTransaction/{id}")
	public Date getLastTransaction(@PathVariable(value = "id") int id) {
		return transactionService.getLastTransaction(id);
	}

	@GetMapping(value = "/getTransactionByRibE/{ribE}")
	public List<Transaction> findTransactionByRibE(@PathVariable(value = "ribE") int ribE) {
		return transactionService.findTransactionByRibE(ribE);
	}

	@GetMapping(value = "/getTransactionsById/{id}")
	public Transaction getTransactionById(@PathVariable(value = "id") int id) {
		return transactionService.findTransactionById(id);
	}

	@PostMapping(value = "/updateTransaction/{id}")
	public Transaction updateTransaction(@PathVariable(value = "id") int id, @RequestBody Transaction transaction) {
		return transactionService.updateTransaction(transaction, id);
	}

	@GetMapping(value = "getAllTransactionByIdUser/{id}")
	@ResponseBody
	public List<Transaction> getAllTransactionByIdUser(@PathVariable(value = "id") int id,
			@RequestBody Transaction transaction) {

		return transactionService.getAllTransactionByIdUser(id);
	}

	@DeleteMapping(value = "/deleteTransaction/{id}")
	public void deleteTransaction(@PathVariable(value = "id") int id) {
		transactionService.deleteTransactionById(id);
	}

	@GetMapping(value = "/getNbrTransactionsS/{id}")
	public int getNbrTransactions(@PathVariable(value = "id") int id) {
		return transactionService.getNbrTransactionsS(id);
	}

	@GetMapping(value = "/getNbrTransactionsC/{id}")
	public int getNbrTransactionsC(@PathVariable(value = "id") int id) {
		return transactionService.getNbrTransactionsC(id);
	}

	@GetMapping(value = "/getNbrTransactionsAll/{id}")
	public int getNbrTransactionsAll(@PathVariable(value = "id") int id) {
		return transactionService.getNbrTransactionsAll(id);
	}
}