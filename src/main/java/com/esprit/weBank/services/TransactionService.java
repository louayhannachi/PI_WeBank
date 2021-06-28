package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Account;
import com.esprit.weBank.entities.Transaction;
import com.esprit.weBank.entities.User;
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
	
	 public Transaction addTransaction(Transaction transaction) {
	        return transactionRepository.save(transaction);
	    }
	
	 public Transaction findTransactionById(int id) {
			return transactionRepository.findById(id).orElse(null);
		}
	 
	 
	 public Transaction updateTransaction(Transaction transaction, int id) {
		 Transaction existingTransaction = findTransactionById(id);
			if (existingTransaction != null) {
				existingTransaction.setNomC(transaction.getNomC());
				existingTransaction.setType(transaction.getType());
				existingTransaction.setRibE(transaction.getRibE());
				existingTransaction.setRibR(transaction.getRibR());
				existingTransaction.setDateT(transaction.getDateT());
				existingTransaction.setMontant(transaction.getMontant());
				existingTransaction.setdevise(transaction.getdevise());
				return transactionRepository.save(existingTransaction);
			}		
			return null;
		}
	 
	 public void deleteTransactionById(int id) {
		 transactionRepository.deleteById(id);
		}
	
}
