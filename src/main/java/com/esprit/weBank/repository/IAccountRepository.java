package com.esprit.weBank.repository;

import org.springframework.data.repository.CrudRepository;

import com.esprit.weBank.entities.Account;

public interface IAccountRepository extends CrudRepository<Account, Integer> {

}
