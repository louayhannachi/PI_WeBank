package com.esprit.weBank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.weBank.entities.Account;
import com.esprit.weBank.entities.User;
import com.esprit.weBank.repository.IAccountRepository;
import com.esprit.weBank.repository.IUserRepository;

@Service
public class AccountService {
	
	@Autowired
	private IAccountRepository accountRepository;
	
	@Autowired
	private UserService userService;
	
	public Account saveAccount(String cin, Account account) {
		User tmp = userService.findUserByCin(cin);
		if (tmp != null) {
			account.setOwner(tmp);
			return accountRepository.save(account);
		}
		return null;
		
	}
	
	public List<Account> findAllAccount() {
		return (List<Account>) accountRepository.findAll();
	}
	
	public Account findAccountById(int id) {
		return accountRepository.findById(id).orElse(null);
	}
	
	public Account findAccountByAccNumber(long accNumber) {
		return accountRepository.findByAccNumber(accNumber).orElse(null);
	}
	
	public Account findAccountByRib(long rib) {
		return accountRepository.findByRib(rib).orElse(null);
	}
	
	public Account updateAccountBalanceByAccNumber(Account account, long accNumber) {
		Account existingAccount = findAccountByAccNumber(accNumber);
		if (existingAccount != null) {
			existingAccount.setBalance(account.getBalance());
			return accountRepository.save(existingAccount);
		}		
		return null;
	}
	
	public Account updateAccountBalanceByRib(Account account, long rib) {
		Account existingAccount = findAccountByRib(rib);
		if (existingAccount != null) {
			existingAccount.setBalance(account.getBalance());
			return accountRepository.save(existingAccount);
		}		
		return null;
	}
	
	public void deleteAccountById(int id) {
		accountRepository.deleteById(id);
	}
	
	public void deleteUserByAccNumber(long accNumber) {
		Account tmp = findAccountByAccNumber(accNumber);
		if (tmp != null) {
			accountRepository.deleteById(tmp.getId());
		}
	}

}
