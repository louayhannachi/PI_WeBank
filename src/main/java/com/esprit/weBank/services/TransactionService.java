package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.weBank.entities.Transaction;
import com.esprit.weBank.repository.ITransactionRepository;

@Service
public class TransactionService {
	@Autowired
	private ITransactionRepository transactionRepository;
	
	public List<Transaction> findAllTransaction() {
		return (List<Transaction>) transactionRepository.findAll();
	}
	public List<Transaction> findTransactionByRibE(int ribE) {
		
		return (List<Transaction>) transactionRepository.findByRibE(ribE).orElse(null);
	}
	
}
