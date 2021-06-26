package com.esprit.weBank.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.weBank.repository.IAccountRepository;

public class AccountService {
	
	@Autowired
	private IAccountRepository accountRepository;

}
