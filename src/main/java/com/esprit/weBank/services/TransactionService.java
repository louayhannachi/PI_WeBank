package com.esprit.weBank.services;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.weBank.entities.Transaction;
import com.esprit.weBank.entities.User;
import com.esprit.weBank.repository.ITransactionRepository;
import com.esprit.weBank.util.AccountType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

@EnableScheduling
@Service
public class TransactionService {
	@Autowired
	private ITransactionRepository transactionRepository;

	UserService userService;

	public List<Transaction> findAllTransaction() {
		return (List<Transaction>) transactionRepository.findAll();
	}

	public List<Transaction> findTransactionByRibE(int ribE) {

		return (List<Transaction>) transactionRepository.findByRibE(ribE).orElse(null);
	}

	public Transaction findTransactionById(int id) {
		return transactionRepository.findById(id).orElse(null);
	}

	public List<Transaction> getAllTransactionByIdUser(int id) {
		return (List<Transaction>) transactionRepository.getAllTransactionByIdUser(id);
	}

	public Date getLastTransaction(int id) {
		return transactionRepository.getLastTransaction(id);
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
			existingTransaction.setDevise(transaction.getDevise());
			return transactionRepository.save(existingTransaction);
		}
		return null;
	}

	public void deleteTransactionById(int id) {
		transactionRepository.deleteById(id);
	}

	@Transactional
	public void updateBalanceR(Transaction transaction) {

		transactionRepository.updateBalanceR(transaction.getRibR());

	}
	@Transactional
	public void addTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
		if ((transaction.getType()).equals(((AccountType.CURRENT).toString()))) {
			transactionRepository.updateBalanceCredit(transaction.getUser());
			transactionRepository.updateBalanceR(transaction.getRibR());
		} else {
			transactionRepository.updateBalanceSaving(transaction.getUser());
		}
	}

	@Transactional
	//@Scheduled(fixedRate = 30000)
	public CompletableFuture<Transaction> addbyth() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		User user = new User(1);
		Transaction transaction = new Transaction();
		transaction.setDateT(dtf.format(now));
		transaction.setDevise("dinars");
		transaction.setMontant(1.00);
		transaction.setRibE(89955862);
		transaction.setRibR(22);
		transaction.setNomC("aymen");
		transaction.setType("SAVINGS");
		transaction.setUser(user);
		Transaction transactions = transactionRepository.save(transaction);
		transactionRepository.updateBalanceSaving(transaction.getUser());
		return CompletableFuture.completedFuture(transactions);

	}

	public int getNbrTransactionsS(int id) {

		return transactionRepository.getNbrTransactionsS(id);
	}

	public int getNbrTransactionsC(int id) {

		return transactionRepository.getNbrTransactionsC(id);
	}

	public int getNbrTransactionsAll(int id) {

		return transactionRepository.getNbrTransactionsAll(id);
	}

}
