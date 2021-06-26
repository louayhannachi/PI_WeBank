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

import com.esprit.weBank.entities.Account;
import com.esprit.weBank.services.AccountService;

@RestController
public class AccountRestController {
	
	@Autowired
	private AccountService accountService;	
	
	@PutMapping(value = "/createAccount/{cin}")
    public Account createAccount(@PathVariable(value = "cin") String cin, @RequestBody Account account) {
		return accountService.saveAccount(cin, account);
	}
	
	@PostMapping(value ="/updateAccount/{accNumber}")
	public Account updateAccount(@PathVariable(value = "accNumber") long accNumber, @RequestBody Account account) {
		return accountService.updateAccountBalanceByAccNumber(account, accNumber);
	}
	
	@PostMapping(value ="/updateAccountByRib/{rib}")
	public Account updateAccountByRib(@PathVariable(value = "rib") long rib, @RequestBody Account account) {
		return accountService.updateAccountBalanceByRib(account, rib);
	}
	
	@DeleteMapping(value ="/deleteAccount/{accNumber}")
	public void deleteAccount(@PathVariable(value = "accNumber") long accNumber) {
		accountService.deleteUserByAccNumber(accNumber);
	}
	
	@GetMapping(value ="/getAccountByAccNumber/{accNumber}")
	public Account getAccountByAccNumber(@PathVariable(value = "accNumber") long accNumber) {
		return accountService.findAccountByAccNumber(accNumber);
	}
	
	@GetMapping(value ="/getAccountByRib/{rib}")
	public Account getAccountByRib(@PathVariable(value = "rib") long rib) {
		return accountService.findAccountByRib(rib);
	}
	
	@GetMapping(value ="/getAllAccounts")
	public List<Account> getAllAccounts() {
		return accountService.findAllAccount();
	}
}

