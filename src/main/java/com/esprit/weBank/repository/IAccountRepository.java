package com.esprit.weBank.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.esprit.weBank.entities.Account;

public interface IAccountRepository extends CrudRepository<Account, Integer> {

	Optional<Account> findByAccNumber(long accNumber);

	Optional<Account> findByRib(long rib);

}
