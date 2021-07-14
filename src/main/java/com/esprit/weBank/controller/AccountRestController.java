package com.esprit.weBank.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.weBank.entities.Account;
import com.esprit.weBank.services.AccountService;
import com.esprit.weBank.util.AccountType;

@RestController
@EnableWebSecurity
public class AccountRestController {
	
	@Autowired
	private AccountService accountService;	
	
	@PutMapping(value = "/createAccount/{cin}")
    public ResponseEntity<Object> createAccount(@PathVariable(value = "cin") String cin, @RequestBody Account account) {
		Account accByNum = accountService.findAccountByAccNumber(account.getAccNumber());
		if (accByNum != null) {
			return new ResponseEntity<>("Account number already exist !", HttpStatus.BAD_REQUEST);
		} 
		Account accByRib = accountService.findAccountByRib(account.getRib());
		if (accByRib != null) {
			return new ResponseEntity<>("RIB already exist !", HttpStatus.BAD_REQUEST);
		} 
		List<Account> userAccounts = accountService.findAllAccountByUserCin(cin);
		if (userAccounts.size() >= 2 ) {
			return new ResponseEntity<>("User already have two account (Saving, Current)", HttpStatus.BAD_REQUEST);
		} else if (userAccounts.size() == 1) {
			AccountType accountType = userAccounts.get(0).getAccType();
			if (accountType.equals(account.getAccType())) {
				return new ResponseEntity<>("User already have a " + account.getAccType().toString() +" account", HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(accountService.saveAccount(cin, account), HttpStatus.OK); 
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
	
	//@RolesAllowed({"ROLE_ADMIN"})
	@GetMapping(value ="/getAllAccounts")
	public List<Account> getAllAccounts() {
		return accountService.findAllAccount();
	}
}

